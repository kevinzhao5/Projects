import java.io.*;

public class div7 {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("div7.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		int N = Integer.parseInt(in.readLine());
		int[] id = new int[N];
		for (int i = 0; i < N; i++) id[i] = Integer.parseInt(in.readLine());
		int[][] dp = new int[N][7];
		dp[0][id[0] % 7] = 1;
		for (int i = 1; i < N; i++) {
			int m = id[i] % 7;
			dp[i][m] = 1;
			for (int j = 0; j < 7; j++) {
				int t = dp[i - 1][j];
				if (t > 0) dp[i][(j + m) % 7] = Math.max(dp[i][(j + m) % 7], t + 1);
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(dp[i][0], max);
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}