import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class p248 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("248.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		int N = Integer.parseInt(in.readLine());
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][i] = Integer.parseInt(in.readLine());
		}
		int ans = 0;
		for (int l = 1; l <= N; l++) {
			for (int i = 1; i <= N; i++) {
				int j = Math.min(i + l - 1, N);
				for (int k = i; k < N; k++) {
					if (dp[i][k] == dp[k + 1][j]) dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
				}
				ans = Math.max(ans, dp[i][j]);
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}