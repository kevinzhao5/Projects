import java.io.*;
import java.util.*;

class qw implements Comparable<qw> {
	
	int a, b;
	
	public qw(int aa, int bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(qw o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

class qedge {
	
	ArrayList<qw> a;
	
}

public class shortcut {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/shortcut.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/shortcut.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
		qedge[] edges = new qedge[N];
		int[] cows = new int[N];
		int[] tc = new int[N];
		StringTokenizer s = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(s.nextToken());
			edges[i] = new qedge();
			edges[i].a = new ArrayList<qw>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s1 = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s1.nextToken()) - 1, b = Integer.parseInt(s1.nextToken()) - 1, c = Integer.parseInt(s1.nextToken());
			edges[a].a.add(new qw(b, c));
			edges[b].a.add(new qw(a, c));
		}
		int[] p = new int[N];
		int[] dist = new int[N];
		Arrays.fill(dist, 1000000000);
		Arrays.fill(p, 1000000000);
		dist[0] = 0;
		p[0] = 0;
		PriorityQueue<qw> pq = new PriorityQueue<qw>();
		pq.offer(new qw(0, 0));
		while (!pq.isEmpty()) {
			qw q = pq.poll();
			int a = q.a, b = q.b;
			if (a > dist[b]) continue;
			for (qw i : edges[b].a) {
				int c = i.a, d = i.b;
				if (dist[b] + d < dist[c]) {
					dist[c] = dist[b] + d;
					pq.offer(new qw(dist[c], c));
					p[c] = b;
				} else if (dist[b] + d == dist[c] && b < p[c]) {
					pq.offer(new qw(dist[c], c));
					p[c] = b;
				}
			}
		}
		for (int i = 1; i < N; i++) {
			if (cows[i] > 0) {
				int c = i;
				while (c != 0) {
					tc[c] += cows[i];
					c = p[c];
				}
			}
		}
		int max = 0;
		for (int i = 1; i < N; i++) {
			max = Math.max(max, dist[i] * tc[i] - T * tc[i]);
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}