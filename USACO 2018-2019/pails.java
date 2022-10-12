import java.io.*;
import java.util.*;

public class pails {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pails.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[X + 1][Y + 1];
		for (int i = 0; i < X + 1; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0][0] = 0;
		boolean[] m = new boolean[X + Y + 2];
		for (int k = 0; k < K; k++) {
			for (int i = 0; i <= X; i++) {
				for (int j = 0; j <= Y; j++) {
					if (dp[i][j] != Integer.MAX_VALUE) {
						m[i + j] = true;
					}
					if (dp[i][j] >= K) continue;
					dp[X][j] = Math.min(dp[i][j] + 1, dp[X][j]);
					dp[i][Y] = Math.min(dp[i][j] + 1, dp[i][Y]);
					dp[0][j] = Math.min(dp[i][j] + 1, dp[0][j]);
					dp[i][0] = Math.min(dp[i][j] + 1, dp[i][0]);
					dp[i + Math.min(j, X - i)][j - Math.min(j, X - i)] = Math.min(dp[i][j] + 1, dp[i + Math.min(j, X - i)][j - Math.min(j, X - i)]);
					dp[i - Math.min(i, Y - j)][j + Math.min(i, Y - j)] = Math.min(dp[i][j] + 1, dp[i - Math.min(i, Y - j)][j + Math.min(i, Y - j)]);
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < m.length; i++) {
			if (!m[i]) continue;
			int t = Math.abs(i - M);
			if (t < min) min = t;
		}
		out.println(min);
		in.close();
		out.close();
	}
	
}