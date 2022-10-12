import java.io.*;
import java.util.*;

class u implements Comparable<u> {
	
	int a, b;
	
	public u(int c, int d) {
		a = c;
		b = d;
	}

	@Override
	public int compareTo(u o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

class y {
	
	ArrayList<u> a;
	
}

public class gpsduel {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/gpsduel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/gpsduel.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		y[] p = new y[N];
		y[] q = new y[N];
		y[] f = new y[N];
		for (int i = 0; i < N; i++) {
			p[i] = new y();
			q[i] = new y();
			f[i] = new y();
			p[i].a = new ArrayList<u>();
			q[i].a = new ArrayList<u>();
			f[i].a = new ArrayList<u>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(s.nextToken()) - 1, B = Integer.parseInt(s.nextToken()) - 1, P = Integer.parseInt(s.nextToken()), Q = Integer.parseInt(s.nextToken());
			p[B].a.add(new u(A, P));
			q[B].a.add(new u(A, Q));
		}
		int[] pare = new int[N];
		int[] dist = new int[N];
		Arrays.fill(dist, 1000000000);
		dist[N - 1] = 0;
		pare[N - 1] = N - 1;
		PriorityQueue<u> pq = new PriorityQueue<u>();
		pq.offer(new u(0, N - 1));
		while (!pq.isEmpty()) {
			u uu = pq.poll();
			int a = uu.a, b = uu.b;
			if (a > dist[b]) continue;
			for (u x:p[b].a) {
				int c = x.a, d = x.b;
				if (dist[b] + d < dist[c]) {
					pare[c] = b;
					dist[c] = d + dist[b];
					pq.offer(new u(dist[c], c));
				}
			}
		}
		int[] dist1 = new int[N];
		int[] pare1 = new int[N];
		Arrays.fill(dist1, 1000000000);
		dist1[N - 1] = 0;
		pare1[N - 1] = N - 1;
		pq = new PriorityQueue<u>();
		pq.offer(new u(0, N - 1));
		while (!pq.isEmpty()) {
			u uu = pq.poll();
			int a = uu.a, b = uu.b;
			if (a > dist1[b]) continue;
			for (u x:q[b].a) {
				int c = x.a, d = x.b;
				if (dist1[b] + d < dist1[c]) {
					pare1[c] = b;
					dist1[c] = d + dist1[b];
					pq.offer(new u(dist1[c], c));
				}
			}
		}
		TreeMap<u, Integer> t = new TreeMap<u, Integer>();
		for (int i = 0; i < N; i++) {
			int counter = i;
			while (counter != N - 1) {
				u key = new u(pare[counter], counter);
				if (t.containsKey(key)) t.put(key, 2);
				else t.put(key, 1);
				counter = pare[counter];
			}
			counter = i;
			while (counter != N - 1) {
				u key = new u(pare1[counter], counter);
				if (t.containsKey(key)) t.put(key, 2);
				else t.put(key, 1);
				counter = pare1[counter];
			}
		}
		for (int i = 0; i < N; i++) {
			for (u uu:p[i].a) {
				int a = uu.a, w = 2;
				u key = new u(a, i);
				if (t.containsKey(key)) w -= t.get(key);
				f[a].a.add(new u(i, w));
			}
		}
		Arrays.fill(dist, 1000000000);
		dist[0] = 0;
		pq = new PriorityQueue<u>();
		pq.offer(new u(0, 0));
		while (!pq.isEmpty()) {
			u uu = pq.poll();
			int a = uu.a, b = uu.b;
			if (a > dist[b]) continue;
			for (u x:f[b].a) {
				int c = x.a, d = x.b;
				if (dist[b] + d < dist[c]) {
					dist[c] = d + dist[b];
					pq.offer(new u(dist[c], c));
				}
			}
		}
		out.println(dist[N - 1]);
		in.close();
		out.close();
	}
	
}