import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class hopscotch {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[][] b = new int[R][C];
		for (int i = 0; i < R; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < C; j++) {
				b[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		long[][] dp = new long[R][C];
		dp[0][0] = 1l;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int k = i + 1; k < R; k++) {
					for (int l = j + 1; l < C; l++) {
						if (b[i][j] != b[k][l]) dp[k][l] = (dp[k][l] + dp[i][j]) % 1000000007;
					}
				}
			}
		}
		out.println((dp[R - 1][C - 1] % 1000000007));
		in.close();
		out.close();
	}
	
}