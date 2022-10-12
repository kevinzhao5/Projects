import java.io.*;
import java.util.*;

public class spainting {
	
	static long mod = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		long[] dp = new long[N + 1];
		long[] sums = new long[N + 1];
		for (int i = 0; i < K; i++) {
			if (i > 0) dp[i] = (dp[i - 1] * M) % mod;
			else dp[i] = 1;
			sums[i] = dp[i];
			if (i > 0) sums[i] = (sums[i] + sums[i - 1]) % mod;
		}
		long pow = dp[K - 1];
		for (int i = K; i <= N; i++) {
			pow = (pow * M) % mod;
			long diff = ((sums[i - 1] - sums[i - K]) % mod);
			if (diff < 0) diff += mod;
			dp[i] = (diff * (M - 1)) % mod;
			sums[i] = (dp[i] + sums[i - 1]) % mod;
		}
		long ans = (pow - dp[N]) % mod;
		ans = ans < 0 ? ans + mod : ans;
		out.println(ans);
		in.close();
		out.close();
	}
	
}