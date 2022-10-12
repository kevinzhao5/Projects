import java.io.*;
import java.util.*;

class tt {
	
	TreeMap<Integer, Integer> t;
	
}

public class poetry {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("poetry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		final int mod = 1000000007;
		long[] dp = new long[K + 1];
		TreeMap<Integer, Integer> numWord = new TreeMap<Integer, Integer>();
		tt[] nWC = new tt[N];
		dp[0] = 1;
		for (int i = 0; i < N; i++) {
			nWC[i] = new tt();
			nWC[i].t = new TreeMap<Integer, Integer>();
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()), b = Integer.parseInt(s.nextToken()) - 1;
			if (numWord.size() == 0 || !numWord.containsKey(a)) numWord.put(a, 1);
			else numWord.put(a, numWord.get(a) + 1);
			if (nWC[b].t.size() == 0 || !nWC[b].t.containsKey(a)) nWC[b].t.put(a, 1);
			else nWC[b].t.put(a, nWC[b].t.get(a) + 1);
		}
		for (int i = 0; i <= K; i++) {
			if (dp[i] == 0) continue;
			for (int j : numWord.keySet()) {
				if (j + i > K) continue;
				dp[i + j] = (dp[i + j] + dp[i] * numWord.get(j)) % mod;
			}
		}
		long[] count = new long[N];
		for (int i = 0; i < N; i++) {
			for (int j : nWC[i].t.keySet()) {
				count[i] = (count[i] + dp[K - j] * nWC[i].t.get(j)) % mod;
			}
		}
		int[] rcount = new int[26];
		for (int i = 0; i < M; i++) {
			rcount[(int)in.readLine().charAt(0) - 65]++;
		}
		long[] dp2 = new long[26];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < N; j++) {
				long mult = 1;
				for (int k = 0; k < rcount[i]; k++) {
					mult = (mult * count[j]) % mod;
				}
				if (mult == 1) continue;
				dp2[i] = (dp2[i] + mult) % mod;
			}
		}
		long res = 0l;
		for (int i = 0; i < 26; i++) {
			if (dp2[i] > 0) {
				if (res == 0) res = dp2[i];
				else res = (res * dp2[i]) % mod;
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	
}