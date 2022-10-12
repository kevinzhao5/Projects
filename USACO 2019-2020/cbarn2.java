import java.io.*;
import java.util.*;

public class cbarn2 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbarn2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] r = new int[N + 1];
		for (int i = 0; i < N; i++) {
			r[i] = Integer.parseInt(in.readLine());
		}
		r[N] = 0;
		long ans = Long.MAX_VALUE;
		long[][] dp = new long[K + 2][N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= K + 1; j++) {
				Arrays.fill(dp[j], 1000000000);
			}
			dp[1][0] = 0;
			for (int j = 1; j <= K; j++) {
				for (int k = 0; k < N; k++) {
					long cost = 0;
					for (int l = k + 1; l <= N; l++) {
						dp[j + 1][l] = Math.min(dp[j + 1][l], dp[j][k] + cost);
						cost += (l - k) * r[l];
					}
				}
			}
			ans = Math.min(ans, dp[K + 1][N]);
			int temp = r[0];
			for (int j = 1; j < N; j++) {
				r[j - 1] = r[j];
			}
			r[N - 1] = temp;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}