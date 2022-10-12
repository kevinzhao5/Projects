import java.io.*;
import java.util.*;

class d implements Comparable<d> {
	
	int a;
	s b;
	
	public d(int aa, s bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(d o) {
		return this.a - o.a;
	}
	
}

class s {
	
	int a, b, c;
	
	public s(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
	}
	
}

public class gravity {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/gravity.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/gravity.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		boolean[][] f = new boolean[N][M];
		int[][][] dist = new int[N][M][2];
		int da = 0, db = 0, ca = 0, cb = 0;
		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < M; j++) {
				Arrays.fill(dist[i][j], 1000000000);
				char c = s.charAt(j);
				if (c == 'C') {
					f[i][j] = true;
					ca = i;
					cb = j;
				} else if (c == 'D') {
					f[i][j] = true;
					da = i;
					db = j;
				} else if (c == '.') {
					f[i][j] = true;
				} else {
					f[i][j] = false;
				}
			}
		}
		dist[ca][cb][0] = 0;
		PriorityQueue<d> pq = new PriorityQueue<d>();
		pq.offer(new d(0, new s(ca, cb, 0)));
		while (!pq.isEmpty()) {
			d dd = pq.poll();
			s ss = dd.b;
			int a = dd.a, b = ss.a, c = ss.b, d = ss.c, w = dist[b][c][d];
			if (b == da && c == db) break;
			if (a > w) continue;
			if (d == 0) {
				if (b == N - 1) continue;
				if (f[b + 1][c]) {
					if (w < dist[b + 1][c][0]) {
						dist[b + 1][c][0] = w;
						pq.offer(new d(w, new s(b + 1, c, 0)));
						continue;
					}
				}
				if (w + 1 < dist[b][c][1]) {
					pq.offer(new d(w + 1, new s(b, c, 1)));
					dist[b][c][1] = w + 1;
				}
			} else {
				if (b == 0) continue;
				if (f[b - 1][c]) {
					if (w < dist[b - 1][c][1]) {
						dist[b - 1][c][1] = w;
						pq.offer(new d(w, new s(b - 1, c, 1)));
						continue;
					}
				}
				if (w + 1 < dist[b][c][0]) {
					pq.offer(new d(w + 1, new s(b, c, 0)));
					dist[b][c][0] = w + 1;
				}
			}
			if (c < M - 1) {
				if (f[b][c + 1]) {
					if (w < dist[b][c + 1][d]) {
						dist[b][c + 1][d] = w;
						pq.offer(new d(w, new s(b, c + 1, d)));
					}
				}
			}
			if (c > 0) {
				if (f[b][c - 1]) {
					if (w < dist[b][c - 1][d]) {
						dist[b][c - 1][d] = w;
						pq.offer(new d(w, new s(b, c - 1, d)));
					}
				}
			}
		}
		int ans = Math.min(dist[da][db][0], dist[da][db][1]);
		if (ans == 1000000000) out.println(-1);
		else out.println(ans);
		in.close();
		out.close();
	}
	
}