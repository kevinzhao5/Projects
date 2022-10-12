import java.io.*;
import java.util.*;

class l implements Comparable<l> {
	
	int a, b, c, d;
	
	public l(int aa, int bb, int cc, int dd) {
		a = aa;
		b = bb;
		c = cc;
		d = dd;
	}

	@Override
	public int compareTo(l o) {
		return this.a - o.a;
	}
	
}

public class dream {
	
	static int N, M;
	static int[][] maze;
	
	public static boolean passable(int i, int j, int k) {
		if (i < 0 || i >= N || j < 0 || j >= M) return false;
		if (maze[i][j] == 0) return false;
		if (maze[i][j] == 3 && k == 0) return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("dream.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				maze[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		int[][][] dist = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(dist[i][j], 1000000000);
			}
		}
		dist[0][0][0] = 0;
		PriorityQueue<l> pq = new PriorityQueue<l>();
		pq.offer(new l(0, 0, 0, 0));
		int[] rr = {0, 0, -1, 1};
		int[] rc = {1, -1, 0, 0};
		while (!pq.isEmpty()) {
			l ll = pq.poll();
			int a = ll.a, b = ll.b, c = ll.c, d = ll.d;
			if (a > dist[b][c][d]) continue;
			for (int i = 0; i < 4; i++) {
				int na = a + 1, nb = b + rr[i], nc = c + rc[i], nd = d;
				if (!passable(nb, nc, d)) continue;
				if (maze[nb][nc] == 2) nd = 1;
				if (maze[nb][nc] == 4) {
					nd = 0;
					while (passable(nb + rr[i], nc + rc[i], 0) && maze[nb + rr[i]][nc + rc[i]] == 4) {
						na++;
						nb += rr[i];
						nc += rc[i];
					}
					if (passable(nb + rr[i], nc + rc[i], 0)) {
						na++;
						nb += rr[i];
						nc += rc[i];
					}
					if (maze[nb][nc] == 2) nd = 1;
				}
				if (na < dist[nb][nc][nd]) {
					dist[nb][nc][nd] = na;
					pq.offer(new l(na, nb, nc, nd));
				}
			}
		}
		int ans = Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
		if (ans == 1000000000) out.println(-1);
		else out.println(ans);
		in.close();
		out.close();
	}
	
}