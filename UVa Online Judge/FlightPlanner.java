import java.util.*;
import java.io.*;

public class FlightPlanner {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(in.readLine());
		for (int e = 0; e < test; e++) {
			in.readLine();
			int n = Integer.parseInt(in.readLine()) / 100;
			int[][] w = new int[10][n];
			for (int i = 0; i < 10; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int x = 0; x < n; x++) {
					w[i][x] = Integer.parseInt(st.nextToken());
				}
			}
			int[][] dp = new int[10][n + 1];
			for (int i = 0; i < 9; i++) {
				Arrays.fill(dp[i], 100000000);
			}
			dp[9][0] = 0;
			for (int i = 1; i < n + 1; i++) {
				for (int j = 9; j >= Math.max(9 - i, 0); j--) {
					dp[j][i] = 30 - w[j][i - 1] + dp[j][i - 1];
					if (j < 9) dp[j][i] = Math.min(dp[j][i], dp[j + 1][i - 1]  + 60 - w[j + 1][i - 1]);
					if (j > 0) dp[j][i] = Math.min(dp[j][i], dp[j - 1][i - 1] + 20 - w[j - 1][i - 1]);
				}
			}
			System.out.println(dp[9][n]);
			System.out.println();
		}
		in.close();
	}

}
