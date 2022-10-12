import java.io.*;
import java.util.*;

public class cowmbat {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowmbat.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		String str = in.readLine();
		int[] moves = new int[N];
		for (int i = 0; i < N; i++) {
			moves[i] = (int) (str.charAt(i)) - 97;
		}
		int[][] a = new int[M][M];
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				a[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		for (int k = 0; k < M; k++) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
				}
			}
		}
		int[][] dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			Arrays.fill(dp[i], 1000000000);
		}
		int[] costs = new int[M];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < M; j++) {
				costs[j] += a[moves[i]][j];
				if (i == K - 1) {
					dp[j][i] = costs[j];
				}
			}
		}
		for (int i = K; i < N; i++) {
			for (int j = 0; j < M; j++) {
				costs[j] += a[moves[i]][j];
			}
			for (int j = 0; j < M; j++) {
				costs[j] -= a[moves[i - K]][j];
			}
			int minVal = 1000000000;
			for (int j = 0; j < M; j++) {
				minVal = Math.min(minVal, dp[j][i - K]);
			}
			for (int j = 0; j < M; j++) {
				dp[j][i] = Math.min(dp[j][i - 1] + a[moves[i]][j], minVal + costs[j]);
			}
		}
		int ans = 1000000000;
		for (int i = 0; i < M; i++) {
			ans = Math.min(ans, dp[i][N - 1]);
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}