import java.io.*;
import java.util.*;

public class poetry {
	
	static final long mod = 1000000007l;
	
	public static long pow(long a, long b) {
		if (b == 0) return 1;
		if (b == 1) return a;
		long ret = pow(a, b / 2);
		ret = (ret * ret) % mod;
		if (b % 2 == 1) ret = (ret * a) % mod;
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("poetry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] s = new int[N];
		int[] c = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			s[i] = Integer.parseInt(st1.nextToken());
			c[i] = Integer.parseInt(st1.nextToken()) - 1;
		}
		int[] num = new int[26];
		for (int i = 0; i < M; i++) {
			num[(int) (in.readLine().charAt(0)) - 65]++;
		}
		long[] dp = new long[K + 1];
		dp[0] = 1;
		long[] amt = new long[N];
		for (int i = 0; i < K; i++) {
			if (dp[i] == 0) continue;
			for (int j = 0; j < N; j++) {
				if (s[j] + i > K) continue;
				if (s[j] + i == K) amt[c[j]] = (amt[c[j]] + dp[i]) % mod;
				dp[i + s[j]] = (dp[i + s[j]] + dp[i]) % mod;
			}
		}
		long ans = 1l;
		for (int i = 0; i < 26; i++) {
			if (num[i] == 0) continue;
			long sum = 0l;
			for (int j = 0; j < N; j++) {
				sum = (sum + pow(amt[j], num[i])) % mod;
			}
			ans = (ans * sum) % mod;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}