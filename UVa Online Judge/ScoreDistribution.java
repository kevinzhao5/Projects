import java.io.*;
import java.util.*;

public class ScoreDistribution {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int g = Integer.parseInt(in.readLine());
		for (int w = 0; w < g; w++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N + 1][T + 1];
			for (int i = 1; i <= N; i++) {
				dp[i][i * P] = 1;
			}
			for (int i = 1; i <= N; i++) {
				for (int j = i * P + 1; j <= T; j++) {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j - P];
				}
			}
			System.out.println(dp[N][T]);
		}
		in.close();
	}

}
