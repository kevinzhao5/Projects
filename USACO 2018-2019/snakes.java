import java.io.*;
import java.util.*;

public class snakes {
	
	static int[] sums;
	
	public static int sum(int a, int b) {
		if (a == 0) return sums[b];
		return sums[b] - sums[a - 1];
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("snakes.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] size = new int[N];
		sums = new int[N];
		StringTokenizer s = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			size[i] = Integer.parseInt(s.nextToken());
			if (i == 0) sums[i] = size[i];
			else sums[i] = size[i] + sums[i - 1];
		}
		int[][] dp = new int[N + 1][K + 2];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], 1000000000);
		}
		Arrays.fill(dp[0], 0);
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				if (j == 0 && i > 0) continue;
				if (j > i) break;
				int max = 0;
				for (int k = i + 1; k <= N; k++) {
					max = Math.max(max, size[k - 1]);
					//System.out.println(i + " " + j + " " + k + " " + max + " " + (max * (k - i)) + " " + sum(i, k - 1) + " " + dp[i][j]);
					dp[k][j + 1] = Math.min(dp[k][j + 1], dp[i][j] + max * (k - i) - sum(i, k - 1));
				}
			}
		}
		out.println(dp[N][K + 1]);
		in.close();
		out.close();
	}
	
}