import java.io.*;
import java.util.*;

class three implements Comparable<three> {
	
	int a, b, c;
	
	public three(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
	}

	@Override
	public int compareTo(three o) {
		if (this.a == o.a) {
			if (this.b == o.b) {
				return this.c - o.c;
			}
			return this.b - o.b;
		}
		return this.a - o.a;
	}
	
}

class two implements Comparable<two> {
	
	int a, b;
	
	public two(int aa, int bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(two o) {
		return this.b - o.b;
	}
	
}

class junction {
	
	TreeSet<three> t;
	
	public junction() {
		t = new TreeSet<three>();
	}
	
}

public class pump {
	
	static int N, M;
	static junction[] j;
	
	public static int calc(int flow) {
		int[] dist = new int[N];
		Arrays.fill(dist, 1000000000);
		dist[0] = 0;
		PriorityQueue<two> pq = new PriorityQueue<two>();
		pq.offer(new two(0, 0));
		while (!pq.isEmpty()) {
			two t = pq.poll();
			int a = t.a, b = t.b;
			if (b > dist[a]) continue;
			for (three th : j[a].t) {
				int c = th.a, d = th.b, e = th.c;
				if (e < flow) continue;
				if (dist[c] > b + d) {
					dist[c] = b + d;
					pq.offer(new two(c, dist[c]));
				}
			}
		}
		return dist[N - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pump.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		j = new junction[N];
		for (int i = 0; i < N; i++) {
			j[i] = new junction();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1, c = Integer.parseInt(s.nextToken()), d = Integer.parseInt(s.nextToken());
			j[a].t.add(new three(b, c, d));
			j[b].t.add(new three(a, c, d));
		}
		/*int lo = 1, hi = 1000, mid = 0;
		while (lo < hi) {
			mid = (int) (Math.ceil((lo + hi) / 2.0));
			if (calc(mid) < 1000000000) {
				lo = mid;
			} else {
				hi = mid - 1;
			}
		}
		int ans = (int) (((lo * 1.0) / calc(lo)) * 1000000);
		out.println(ans);*/
		double ans = 0;
		for (int i = 1; i < 1000; i++) {
			ans = Math.max(ans, (i * 1.0) / calc(i));
		}
		out.println((int) (ans * 1000000));
		in.close();
		out.close();
	}
	
}