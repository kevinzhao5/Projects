import java.io.*;
import java.util.*;

public class dining {
	
	static class two {
		
		int a, b;
		
		public two(int aa, int bb) {
			a = aa;
			b = bb;
		}
		
	}
	
	static class three implements Comparable<three> {
		
		int a, b, c;
		
		public three(int aa, int bb, int cc) {
			a = aa;
			b = bb;
			c = cc;
		}

		@Override
		public int compareTo(three o) {
			return this.a - o.a;
		}
		
	}
	
	static class list {
		
		ArrayList<two> a;
		
		public list() {
			a = new ArrayList<two>();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("dining.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		list[] edges = new list[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new list();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1, c = Integer.parseInt(s.nextToken());
			edges[a].a.add(new two(b, c));
			edges[b].a.add(new two(a, c));
		}
		int[] haybales = new int[N];
		for (int i = 0; i < K; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken());
			haybales[a] = b;
		}
		int[][] dist = new int[2][N];
		for (int i = 0; i < 2; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[0][N - 1] = 0;
		PriorityQueue<three> pq = new PriorityQueue<three>();
		pq.offer(new three(0, 0, N - 1));
		while (!pq.isEmpty()) {
			three thr = pq.poll();
			int a = thr.a, b = thr.b, c = thr.c;
			if (dist[b][c] < a) continue;
			int size = edges[c].a.size();
			for (int i = 0; i < size; i++) {
				two t = edges[c].a.get(i);
				int d = t.a, e = t.b, newDist = e + a;
				if (b == 1) {
					if (newDist < dist[1][d]) {
						dist[1][d] = newDist;
						pq.offer(new three(newDist, 1, d));
					}
				} else {
					if (newDist < dist[0][d]) {
						dist[0][d] = newDist;
						pq.offer(new three(newDist, 0, d));
					}
					if (haybales[d] > 0) {
						newDist -= haybales[d];
						if (newDist < dist[1][d]) {
							dist[1][d] = newDist;
							pq.offer(new three(newDist, 1, d));
						}
					}
				}
			}
		}
		for (int i = 0; i < N - 1; i++) {
			if (dist[1][i] <= dist[0][i]) out.println(1);
			else out.println(0);
		}
		in.close();
		out.close();
	}
	
}