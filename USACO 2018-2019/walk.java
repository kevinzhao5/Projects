import java.io.*;
import java.util.*;

class edge implements Comparable<edge> {
	
	int a, b;
	long c;
	
	public edge(int aa, int bb) {
		a = aa;
		b = bb;
		c = (2019201913l * a + 2019201949l * b) % 2019201997l;
	}

	@Override
	public int compareTo(edge o) {
		if (this.c == o.c) return 0;
		if (this.c > o.c) return 1;
		return -1;
	}
	
}

public class walk {
	
	static int[] p, r;
	
	public static int find(int a) {
		if (p[a] == a) return a;
		return p[a] = find(p[a]);
	}
	
	public static boolean sameParent(int a, int b) {
		return find(a) == find(b);
	}
	
	public static void union(int a, int b) {
		int c = find(a), d = find(b);
		if (c == d) return;
		if (r[c] > r[d]) {
			p[d] = c;
		} else {
			p[c] = d;
		}
		if (r[c] == r[d]) r[c]++;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("walk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
		StringTokenizer s = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(s.nextToken()), K = Integer.parseInt(s.nextToken());
		p = new int[N + 1];
		r = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			r[i] = 0;
		}
		PriorityQueue<edge> edges = new PriorityQueue<edge>();
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				edges.offer(new edge(i, j));
			}
		}
		if (K == 1) out.println(0);
		else {
			int count = N;
			long ans = 0l;
			while (!edges.isEmpty()) {
				edge e = edges.poll();
				int a = e.a, b = e.b;
				if (sameParent(a, b)) continue;
				if (count == K) {
					ans = e.c;
					break;
				}
				union(a, b);
				count--;
			}
			out.println(ans);
		}
		in.close();
		out.close();
	}
	
}