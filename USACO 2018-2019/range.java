/*
ID: awesome25
LANG: JAVA
TASK: range
*/
import java.io.*;

class range {
	
	static int length;
	static int[][] dp;
	static boolean[][] map;
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("range.in"));
		length = Integer.parseInt(in.readLine());
		dp = new int[length ][length];
		map = new boolean[length][length];
		for (int i = 0; i < length; i++) {
			String str = in.readLine();
			for (int x = 0; x < length; x++) {
				if (str.charAt(x) == '0') map[i][x] = false;
				else map[i][x] = true;
			}
		}
		in.close();
		int[] ans = new int[length + 1];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (!map[i][j]) continue;
				if (i > 0 && j > 0) dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
				dp[i][j]++;
				ans[dp[i][j]]++;
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		for (int i = 2; i < ans.length; i++) {
			int sum = 0;
			for (int j = i; j < ans.length; j++) {
				sum += ans[j];
			}
			if (sum > 0) out.println(i + " " + sum);
		}
		out.close();
	}
	
}