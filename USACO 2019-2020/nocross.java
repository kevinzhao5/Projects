import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class nocross {
	
	static int[] left;
	static int[] right;
	
	public static boolean isFriendly(int a, int b) {
		return (int) (Math.abs(left[a] - right[b])) <= 4;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
		int N = Integer.parseInt(in.readLine());
		left = new int[N];
		right = new int[N];
		for (int i = 0; i < N; i++) {
			left[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 0; i < N; i++) {
			right[i] = Integer.parseInt(in.readLine());
		}
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int t = 0;
				if (isFriendly(i, j)) t++;
				dp[i][j] = t;
				if (i > 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				if (j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
				if (i > 0 && j > 0) dp[i][j] = Math.max(dp[i][j], t + dp[i - 1][j - 1]);
			}
		}
		out.println(dp[N - 1][N - 1]);
		in.close();
		out.close();
	}
	
}