import java.io.*;
import java.util.*;

public class cownav {
	
	static int N;
	static boolean[][] barn;
	static int[] rr = {1, 0, -1, 0};
	static int[] rc = {0, 1, 0, -1};
	
	static class a implements Comparable<a> {
		
		int a, b, c, d, e, f, g;
		
		public a(int aa, int bb, int cc, int dd, int ee, int ff, int gg) {
			a = aa;
			b = bb;
			c = cc;
			d = dd;
			e = ee;
			f = ff;
			g = gg;
		}

		@Override
		public int compareTo(a o) {
			return this.a - o.a;
		}
		
	}
	
	public static boolean pos(int a, int b) {
		if (a < 0 || a >= N || b < 0 || b >= N || !barn[a][b]) return false;
		return true;
	}
	
	public static int change(int a, int b) {
		a += b;
		a %= 4;
		if (a < 0) a += 4;
		return a;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
		N = Integer.parseInt(in.readLine());
		barn = new boolean[N][N];
		for (int i = N - 1; i >= 0; i--) {
			String s = in.readLine();
			for (int j = 0; j < N; j++) {
				if (s.charAt(j) == 'E') barn[i][j] = true;
			}
		}
		PriorityQueue<a> pq = new PriorityQueue<a>();
		int[][][][][][] dist = new int[N][N][4][N][N][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < N; l++) {
						for (int m = 0; m < N; m++) {
							Arrays.fill(dist[i][j][k][l][m], 1000000);
						}
					}
				}
			}
		}
		pq.offer(new a(0, 0, 0, 0, 0, 0, 1));
		dist[0][0][0][0][0][1] = 0;
		while (!pq.isEmpty()) {
			a aa = pq.poll();
			int a = aa.a, b = aa.b, c = aa.c, d = aa.d, e = aa.e, f = aa.f, g = aa.g;
			if (a > dist[b][c][d][e][f][g]) continue;
			int na = a + 1, nd = d, ng = g;
			if (b != N - 1 || c != N - 1) nd = change(d, 1);
			if (e != N - 1 || f != N - 1) ng = change(g, 1);
			if (na < dist[b][c][nd][e][f][ng]) {
				dist[b][c][nd][e][f][ng] = na;
				pq.offer(new a(na, b, c, nd, e, f, ng));
			}
			nd = d;
			ng = g;
			if (b != N - 1 || c != N - 1) nd = change(d, -1);
			if (e != N - 1 || f != N - 1) ng = change(g, -1);
			if (na < dist[b][c][nd][e][f][ng]) {
				dist[b][c][nd][e][f][ng] = na;
				pq.offer(new a(na, b, c, nd, e, f, ng));
			}
			int nb = b, nc = c, ne = e, nf = f;
			if (pos(b + rr[d], c + rc[d]) && (b != N - 1 || c != N - 1)) {
				nb += rr[d];
				nc += rc[d];
			}
			if (pos(e + rr[g], f + rc[g]) && (e != N - 1 || f != N - 1)) {
				ne += rr[g];
				nf += rc[g];
			}
			if (na < dist[nb][nc][d][ne][nf][g]) {
				dist[nb][nc][d][ne][nf][g] = na;
				pq.offer(new a(na, nb, nc, d, ne, nf, g));
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ans = Math.min(ans, dist[N - 1][N - 1][i][N - 1][N - 1][j]);
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}