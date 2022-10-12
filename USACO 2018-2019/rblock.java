import java.io.*;
import java.util.*;

class ph implements Comparable<ph> {
	
	int a, b;
	
	public ph(int c, int d) {
		a = c;
		b = d;
	}

	@Override
	public int compareTo(ph o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

class h {
	
	ArrayList<ph> v;
	
}

public class rblock {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		h[] edges = new h[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new h();
			edges[i].v = new ArrayList<ph>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1, c = Integer.parseInt(s.nextToken());
			edges[a].v.add(new ph(b, c));
			edges[b].v.add(new ph(a, c));
		}
		int[] dist = new int[N];
		int[] p = new int[N];
		Arrays.fill(dist, 1000000000);
		dist[0] = 0;
		p[0] = -1;
		PriorityQueue<ph> pq = new PriorityQueue<ph>();
		pq.offer(new ph(0, 0));
		while (!pq.isEmpty()) {
			ph t = pq.poll();
			int w = t.a, v = t.b;
			if (w > dist[v]) continue;
			for (int i = 0; i < edges[v].v.size(); i++) {
				ph t1 = edges[v].v.get(i);
				if (dist[v] + t1.b < dist[t1.a]) {
					dist[t1.a] = dist[v] + t1.b;
					p[t1.a] = v;
					pq.offer(new ph(dist[t1.a], t1.a));
				}
			}
		}
		int pdist = dist[N - 1], c = N - 1, max = 0;
		TreeSet<ph> pos = new TreeSet<ph>();
		while (p[c] != -1) {
			pos.add(new ph(c, p[c]));
			c = p[c];
		}
		for (ph curr:pos) {
			for (int i = 0; i < edges[curr.a].v.size(); i++) {
				ph temp = edges[curr.a].v.get(i);
				if (temp.a == curr.b) {
					edges[curr.a].v.set(i, new ph(temp.a, 2 * temp.b));
					break;
				}
			}
			for (int i = 0; i < edges[curr.b].v.size(); i++) {
				ph temp = edges[curr.b].v.get(i);
				if (temp.a == curr.a) {
					edges[curr.b].v.set(i, new ph(temp.a, 2 * temp.b));
					break;
				}
			}
			dist = new int[N];
			Arrays.fill(dist, 1000000000);
			dist[0] = 0;
			pq = new PriorityQueue<ph>();
			pq.offer(new ph(0, 0));
			while (!pq.isEmpty()) {
				ph t = pq.poll();
				int w = t.a, v = t.b;
				if (w > dist[v]) continue;
				for (int i = 0; i < edges[v].v.size(); i++) {
					ph t1 = edges[v].v.get(i);
					if (dist[v] + t1.b < dist[t1.a]) {
						dist[t1.a] = dist[v] + t1.b;
						pq.offer(new ph(dist[t1.a], t1.a));
					}
				}
			}
			max = Math.max(max, dist[N - 1] - pdist);
			for (int i = 0; i < edges[curr.a].v.size(); i++) {
				ph temp = edges[curr.a].v.get(i);
				if (temp.a == curr.b) {
					edges[curr.a].v.set(i, new ph(temp.a, temp.b / 2));
					break;
				}
			}
			for (int i = 0; i < edges[curr.b].v.size(); i++) {
				ph temp = edges[curr.b].v.get(i);
				if (temp.a == curr.a) {
					edges[curr.b].v.set(i, new ph(temp.a, temp.b / 2));
					break;
				}
			}
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}