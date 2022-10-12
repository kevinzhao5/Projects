import java.io.*;
import java.util.*;

class j implements Comparable<j> {
	
	int a, b, c;
	
	public j(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
	}

	@Override
	public int compareTo(j o) {
		return this.a - o.a;
	}
	
}

public class lasers {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lasers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] X = new int[N + 2];
		int[] Y = new int[N + 2];
		X[0] = Integer.parseInt(st.nextToken());
		Y[0] = Integer.parseInt(st.nextToken());
		X[N + 1] = Integer.parseInt(st.nextToken());
		Y[N + 1] = Integer.parseInt(st.nextToken());
		TreeMap<Integer, TreeSet<Integer>> h = new TreeMap<Integer, TreeSet<Integer>>();
		TreeMap<Integer, TreeSet<Integer>> v = new TreeMap<Integer, TreeSet<Integer>>();
		for (int i = 1; i <= N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(s.nextToken()), y = Integer.parseInt(s.nextToken());
			X[i] = x;
			Y[i] = y;
		}
		for (int i = 0; i <= N + 1; i++) {
			int x = X[i], y = Y[i];
			if (!h.isEmpty() && h.containsKey(y)) {
				TreeSet<Integer> t = h.get(y);
				t.add(i);
				h.put(y, t);
			} else {
				TreeSet<Integer> t = new TreeSet<Integer>();
				t.add(i);
				h.put(y, t);
			}
			if (!v.isEmpty() && v.containsKey(x)) {
				TreeSet<Integer> t = v.get(x);
				t.add(i);
				v.put(x, t);
			} else {
				TreeSet<Integer> t = new TreeSet<Integer>();
				t.add(i);
				v.put(x, t);
			}
		}
		int[][] dist = new int[N + 2][2];
		int ans = -1;
		PriorityQueue<j> pq = new PriorityQueue<j>();
		pq.offer(new j(0, 0, 0));
		pq.offer(new j(0, 0, 1));
		for (int i = 0; i <= N + 1; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[0][0] = 0;
		dist[0][1] = 0;
		while (!pq.isEmpty()) {
			j jj = pq.poll();
			int a = jj.a, b = jj.b, c = jj.c;
			if (a > dist[b][c]) continue;
			if (b == N + 1) {
				ans = a;
				break;
			}
			if (c == 0) {
				int y = Y[b];
				for (int i : h.get(y)) {
					if (i == b) continue;
					int na = a + 1;
					if (na > dist[i][1]) continue;
					dist[i][1] = na;
					pq.offer(new j(na, i, 1));
				}
			} else {
				int x = X[b];
				for (int i : v.get(x)) {
					if (i == b) continue;
					int na = a + 1;
					if (na > dist[i][0]) continue;
					dist[i][0] = na;
					pq.offer(new j(na, i, 0));
				}
			}
		}
		out.println(ans - 1);
		in.close();
		out.close();
	}
	
}