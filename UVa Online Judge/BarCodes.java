import java.io.*;
import java.util.*;

public class BarCodes {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (in.ready()) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			long[][] dp = new long[n + 1][k + 1];
			dp[0][0] = 1;
			for (int i = 1; i < n + 1; i++) {
				for (int j = 1; j < k + 1; j++) {
					for (int e = 1; e < m + 1; e++) {
						if (i - e >= 0) dp[i][j] += dp[i - e][j - 1];
					}
				}
			}
			System.out.println(dp[n][k]);
		}
		in.close();
	}

}
