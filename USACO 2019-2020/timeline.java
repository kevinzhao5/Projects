import java.io.*;
import java.util.*;

public class timeline {
	
	static class two implements Comparable<two> {
		
		int a, b;
		
		public two(int a1, int b1) {
			a = a1;
			b = b1;
		}

		@Override
		public int compareTo(two o) {
			if (this.a == o.a) return this.b - o.b;
			return o.a - this.a;
		}
		
	}
	
	static class list {
		
		ArrayList<two> a;
		
		public list() {
			a = new ArrayList<two>();
		}
		
	}
	
	static int N, M, C;
	static int[] S;
	static list[] edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("timeline.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		S = new int[N + 1];
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		for (int i = 1; i <= N; i++) {
			S[i] = Integer.parseInt(st1.nextToken());
		}
		edges = new list[N + 1];
		for (int i = 0; i <= N; i++) {
			edges[i] = new list();
		}
		for (int i = 1; i <= N; i++) {
			edges[0].a.add(new two(i, S[i]));
		}
		for (int i = 0; i < C; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()), b = Integer.parseInt(s.nextToken()), c = Integer.parseInt(s.nextToken());
			edges[a].a.add(new two(b, c));
		}
		int[] dist = new int[N + 1];
		Arrays.fill(dist, 0);
		dist[0] = 0;
		PriorityQueue<two> pq = new PriorityQueue<two>();
		pq.add(new two(0, 0));
		while (!pq.isEmpty()) {
			two t = pq.poll();
			int a = t.a, b = t.b;
			if (a < dist[b]) continue;
			for (two t1:edges[b].a) {
				int c = t1.a, d = t1.b;
				if (a + d > dist[c]) {
					dist[c] = a + d;
					pq.offer(new two(dist[c], c));
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			out.println(dist[i]);
		}
		in.close();
		out.close();
	}
	
}