import java.io.*;
import java.util.*;

public class taming {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("taming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[][][] dp = new int[110][110][110];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				Arrays.fill(dp[i][j], 1000000);
			}
		}
		if (arr[0] == 0) dp[1][1][1] = 0;
		else dp[1][1][1] = 1;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				for (int k = 1; k < N; k++) {
					if (dp[i][j][k] == 1000000) continue;
					boolean equal = i - k + 1 == arr[i];
					if (equal) dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], dp[i][j][k]);
					else dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], dp[i][j][k] + 1);
					if (arr[i] == 0) {
						dp[i + 1][j + 1][i + 1] = Math.min(dp[i + 1][j + 1][i + 1], dp[i][j][k]);
					} else {
						dp[i + 1][j + 1][i + 1] = Math.min(dp[i + 1][j + 1][i + 1], dp[i][j][k] + 1);
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			int ans = 1000000;
			for (int j = 1; j <= N; j++) {
				ans = Math.min(ans, dp[N][i][j]);
			}
			out.println(ans);
		}
		in.close();
		out.close();
	}
	
}