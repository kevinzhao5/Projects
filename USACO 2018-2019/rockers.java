/*
ID: awesome25
LANG: JAVA
TASK: rockers
*/
import java.io.*;
import java.util.*;

class rockers {
	
	static int N, T, M;
	static int[] songs;
	static int[][][][] dp;
	
	public static int dp(int nsongs, int total, int ndisk, int left) {
		if (left < 0) return 0;
		if (left == 0) {
			ndisk++;
			left = T;
		}
		if (nsongs >= N || ndisk >= M) {
			return dp[nsongs][total][ndisk][left] = total;
		}
		if (dp[nsongs][total][ndisk][left] != -1) return dp[nsongs][total][ndisk][left];
		return dp[nsongs][total][ndisk][left] = Math.max(dp(nsongs + 1, total + 1, ndisk, left - songs[nsongs]), Math.max(dp(nsongs + 1, total, ndisk, left), dp(nsongs, total, ndisk + 1, T)));
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		songs = new int[N];
		dp = new int[N + 1][N + 1][M + 1][T + 1];
		for (int a = 0; a < N; a++) {
			for (int b = 0; b < N; b++) {
				for (int c = 0; c < M; c++) {
					Arrays.fill(dp[a][b][c], -1);
				}
			}
		}
		StringTokenizer s = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			songs[i] = Integer.parseInt(s.nextToken());
		}
		out.println(dp(0, 0, 0, T));
		in.close();
		out.close();
	}
	
}