import java.io.*;
import java.util.*;

public class hps {
	
	public static int point(int a, int b) {
		if (a == 0 && b == 2) return 1;
		if (a == 1 && b == 0) return 1;
		if (a == 2 && b == 1) return 1;
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] gest = new int[N];
		for (int i = 0; i < N; i++) {
			char c = in.readLine().charAt(0);
			if (c == 'H') {
				gest[i] = 0;
			} else if (c == 'P') {
				gest[i] = 1;
			} else {
				gest[i] = 2;
			}
		}
		int[][][] dp = new int[N + 1][K + 1][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= K; j++) {
				for (int k = 0; k < 3; k++) {
					int p = point(k, gest[i]) + dp[i][j][k];
					dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], p);
					if (j < K) {
						dp[i + 1][j + 1][(k + 1) % 3] = Math.max(dp[i + 1][j + 1][(k + 1) % 3], p);
						dp[i + 1][j + 1][(k + 2) % 3] = Math.max(dp[i + 1][j + 1][(k + 2) % 3], p);
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i <= K; i++) {
			for (int j = 0; j < 3; j++) {
				ans = Math.max(ans, dp[N][i][j]);
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}