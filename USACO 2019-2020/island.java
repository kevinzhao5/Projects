import java.io.*;
import java.util.*;

public class island {
	
	static class p {
		
		int a, b;
		
		public p(int aa, int bb) {
			a = aa;
			b = bb;
		}
		
	}
	
	static int R, C, N;
	static char[][] grid;
	static int[][] islandId;
	static int[] rr = { 0, 0, 1, -1 };
	static int[] rc = { 1, -1, 0, 0 };
	static int[][] dist;
	static int[][] dp;
	
	public static void findIsland(int a, int b, int c) {
		if (a < 0 || a >= R || b < 0 || b >= C || grid[a][b] != 'X' || islandId[a][b] != -1) return;
		islandId[a][b] = c;
		for (int i = 0; i < 4; i++) {
			findIsland(a + rr[i], b + rc[i], c);
		}
	}
	
	public static void bfs(int a, int b) {
		int[][] distance = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(distance[i], 1000000);
		}
		int id = islandId[a][b];
		distance[a][b] = 0;
		Queue<p> q = new LinkedList<p>();
		q.offer(new p(a, b));
		while (!q.isEmpty()) {
			p pt = q.poll();
			int c = pt.a, d = pt.b;
			if (grid[c][d] == 'X' && (c != a || d != b)) {
				int id2 = islandId[c][d];
				if (id2 != id) {
					dist[id][id2] = Math.min(dist[id][id2], distance[c][d]);
					dist[id2][id] = Math.min(dist[id2][id], distance[c][d]);
				} else continue;
			} else {
				for (int i = 0; i < 4; i++) {
					int nc = c + rr[i], nd = d + rc[i];
					if (nc < 0 || nc >= R || nd < 0 || nd >= C || grid[nc][nd] == '.' || distance[nc][nd] != 1000000) continue;
					if (grid[nc][nd] == 'S') distance[nc][nd] = distance[c][d] + 1;
					else distance[nc][nd] = distance[c][d];
					q.offer(new p(nc, nd));
				}
			}
		}
	}
	
	public static int rec(int a, int bitmask) {
		if (dp[a][bitmask] != 1000000) return dp[a][bitmask];
		int ans = 1000000;
		for (int i = 0; i < N; i++) {
			if ((bitmask & (1 << i)) != 0) {
				int newmask = bitmask & ~(1 << i);
				ans = Math.min(ans, dist[a][i] + rec(i, newmask));
			}
		}
		return dp[a][bitmask] = ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("island.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("island.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = in.readLine();
			for (int j = 0; j < C; j++) {
				grid[i][j] = s.charAt(j);
			}
		}
		islandId = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(islandId[i], -1);
		}
		int currIsland = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == 'X' && islandId[i][j] == -1) {
					findIsland(i, j, currIsland);
					currIsland++;
				}
			}
		}
		N = currIsland;
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], 1000000);
			dist[i][i] = 0;
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == 'X') {
					bfs(i, j);
				}
			}
		}
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		dp = new int[N][(1 << N)];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 1000000);
			dp[i][0] = 0;
		}
		int ans = 1000000;
		for (int i = 0; i < N; i++) {
			if (dp[i][(1 << N) - 1 - (1 << i)] == 1000000) {
				ans = Math.min(ans, rec(i, (1 << N) - 1 - (1 << i)));
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}