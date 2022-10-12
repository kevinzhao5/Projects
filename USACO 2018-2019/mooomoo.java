import java.io.*;
import java.util.*;

public class mooomoo {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mooomoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooomoo.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
		int[] v = new int[B];
		for (int i = 0; i < B; i++) {
			v[i] = Integer.parseInt(in.readLine());
		}
		int[] n = new int[N];
		for (int i = 0; i < N; i++) {
			n[i] = Integer.parseInt(in.readLine());
		}
		int[] dp = new int[100001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < 100001; i++) {
			if (dp[i] == Integer.MAX_VALUE) continue;
			for (int j = 0; j < B; j++) {
				if (v[j] + i > 100000) continue;
				dp[v[j] + i] = Math.min(dp[v[j] + i], dp[i] + 1);
			}
		}
		int c = 0;
		boolean b = false;
		for (int i = 0; i < N; i++) {
			int curr = n[i];
			if (i > 0) curr -= Math.max(n[i - 1] - 1, 0);
			if (dp[curr] == Integer.MAX_VALUE) {
				b = true;
				break;
			}
			c += dp[curr];
		}
		if (b) out.println(-1);
		else out.println(c);
		in.close();
		out.close();
	}
	
}