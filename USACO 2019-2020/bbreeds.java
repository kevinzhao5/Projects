import java.io.*;
import java.util.*;

public class bbreeds {
	
	static final int mod = 2012;
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bbreeds.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bbreeds.out")));
		String paren = in.readLine();
		int N = paren.length();
		int[] prefix = new int[N + 1];
		prefix[0] = 0;
		for (int i = 1; i <= N; i++) {
			if (paren.charAt(i - 1) == '(') prefix[i] = prefix[i - 1] + 1;
			else prefix[i] = prefix[i - 1] - 1;
		}
		int[][] dp = new int[N + 1][N];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], 1000000);
		}
		dp[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (dp[i][j] == 1000000) continue;
				if (paren.charAt(i) == '(') {
					if (dp[i + 1][j + 1] == 1000000) dp[i + 1][j + 1] = dp[i][j];
					else dp[i + 1][j + 1] = (dp[i + 1][j + 1] + dp[i][j]) % mod;
					if (dp[i + 1][j] == 1000000) dp[i + 1][j] = dp[i][j];
					else dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % mod;
				} else {
					if (j > 0) {
						if (dp[i + 1][j - 1] == 1000000) dp[i + 1][j - 1] = dp[i][j];
						else dp[i + 1][j - 1] = (dp[i + 1][j - 1] + dp[i][j]) % mod;
					}
					if (prefix[i] - j > 0) {
						if (dp[i + 1][j] == 1000000) dp[i + 1][j] = dp[i][j];
						else dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % mod;
					}
				}
			}
		}
		out.println(dp[N][0]);
		in.close();
		out.close();
	}
	
}