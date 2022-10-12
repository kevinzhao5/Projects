import java.io.*;
import java.util.*;

class o {
	
	TreeSet<p> t;
	
	public o() {
		t = new TreeSet<p>();
	}
	
}

class p implements Comparable<p> {
	
	int a, b;
	
	public p(int c, int d) {
		a = c;
		b = d;
	}

	@Override
	public int compareTo(p o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class rblock {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		o[] edges = new o[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new o();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1, c = Integer.parseInt(s.nextToken());
			edges[a].t.add(new p(b, c));
			edges[b].t.add(new p(a, c));
		}
		int[] dist = new int[N];
		int[] p = new int[N];
		Arrays.fill(dist, 1000000000);
		p[0] = -1;
		dist[0] = 0;
		PriorityQueue<p> pq = new PriorityQueue<p>();
		pq.offer(new p(0, 0));
		while (!pq.isEmpty()) {
			p p1 = pq.poll();
			int a = p1.a, b = p1.b;
			if (a > dist[b]) continue;
			for (p p2:edges[b].t) {
				int c = p2.a, d = p2.b;
				if (dist[c] <= a + d) continue;
				pq.offer(new p(a + d, c));
				dist[c] = a + d;
				p[c] = b;
			}
		}
		int pdist = dist[N - 1], c = N - 1, max = 0;
		TreeSet<p> pos = new TreeSet<p>();
		while (p[c] != -1) {
			pos.add(new p(c, p[c]));
			c = p[c];
		}
		for (p curr:pos) {
			int a = curr.a, b = curr.b;
			for (p q:edges[a].t) {
				if (q.a == b) {
					edges[a].t.add(new p(b, q.b * 2));
					edges[a].t.remove(q);
					break;
				}
			}
			for (p q:edges[b].t) {
				if (q.a == a) {
					edges[b].t.add(new p(a, q.b * 2));
					edges[b].t.remove(q);
					break;
				}
			}
			dist = new int[N];
			Arrays.fill(dist, 1000000000);
			dist[0] = 0;
			pq = new PriorityQueue<p>();
			pq.offer(new p(0, 0));
			while (!pq.isEmpty()) {
				p q = pq.poll();
				int aa = q.a, bb = q.b;
				if (aa > dist[bb]) continue;
				for (p qq:edges[bb].t) {
					int cc = qq.a, dd = qq.b;
					if (dist[cc] <= aa + dd) continue;
					pq.offer(new p(aa + dd, cc));
					dist[cc] = aa + dd;
				}
			}
			max = Math.max(max, dist[N - 1] - pdist);
			for (p q:edges[a].t) {
				if (q.a == b) {
					edges[a].t.add(new p(b, q.b / 2));
					edges[a].t.remove(q);
					break;
				}
			}
			for (p q:edges[b].t) {
				if (q.a == a) {
					edges[b].t.add(new p(a, q.b / 2));
					edges[b].t.remove(q);
					break;
				}
			}
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}