/*
ID: awesome25
LANG: JAVA
TASK: game1
*/
import java.io.*;
import java.util.*;

class game1 {
	
	static int[] board = new int[100];
	static int[][] dp = new int[101][101];
	
	public static int choose(int a, int b) {
		if (a == b) return dp[a][b] = board[a];
		if (a > b) return 0;
		if (dp[a][b] != -1) return dp[a][b];
		return dp[a][b] = Math.max(Math.min(choose(a + 2, b), choose(a + 1, b - 1)) + board[a], Math.min(choose(a + 1, b - 1), choose(a, b - 2)) + board[b]);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		int n = Integer.parseInt(in.readLine());
		int sum = 0, counter = 0;
		while (in.ready()) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens()) {
				int temp = Integer.parseInt(st.nextToken());
				board[counter] = temp;
				counter++;
				sum += temp;
			}
		}
		for (int i = 0; i < 101; i++) {
			Arrays.fill(dp[i], -1);
		}
		int ans = choose(0, n - 1);
		out.println(ans + " " + (sum - ans));
		in.close();
		out.close();
	}
	
}