import java.io.*;
import java.util.*;

class e implements Comparable<e> {
	
	long a;
	int b, c, d;
	
	public e(long aa, int bb, int cc, int dd) {
		a = aa;
		b = bb;
		c = cc;
		d = dd;
	}

	@Override
	public int compareTo(e o) {
		if (this.a > o.a) return 1;
		else if (this.a < o.a) return -1;
		return 0;
	}
	
}

public class visitfj {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
		int[][] grass = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				grass[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		long[][][] dist = new long[N][N][3];
		grass[0][0] = 0;
		PriorityQueue<e> pq = new PriorityQueue<e>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dist[i][j], Long.MAX_VALUE);
			}
		}
		dist[0][0][2] = 0;
		pq.offer(new e(0, 0, 0, 2));
		int[] rr = {0, 0, 1, -1};
		int[] rc = {1, -1, 0, 0};
		while (!pq.isEmpty()) {
			e ee = pq.poll();
			long a = ee.a;
			int b = ee.b, c = ee.c, d = ee.d;
			if (ee.a > dist[b][c][d]) continue;
			for (int i = 0; i < 4; i++) {
				int nb = b + rr[i], nc = c + rc[i], nd = (d + 1) % 3;
				if (nb < 0 || nc < 0 || nb >= N || nc >= N) continue;
				long na = a + T;
				if (nd == 2) na += grass[nb][nc];
				if (na >= dist[nb][nc][nd]) continue;
				dist[nb][nc][nd] = na;
				pq.offer(new e(na, nb, nc, nd));
			}
		}
		out.println(Math.min(Math.min(dist[N - 1][N - 1][0], dist[N - 1][N - 1][1]), dist[N - 1][N - 1][2]));
		in.close();
		out.close();
	}
	
}