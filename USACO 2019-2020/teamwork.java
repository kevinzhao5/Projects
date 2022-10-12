import java.io.*;
import java.util.*;

public class teamwork {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("teamwork.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] skill = new int[N];
		for (int i = 0; i < N; i++) {
			skill[i] = Integer.parseInt(in.readLine());
		}
		int[] dp = new int[N + 1];
		for (int i = 0; i < N; i++) {
			int max = skill[i];
			for (int j = 0; j < K; j++) {
				if (i + j >= N) break;
				max = Math.max(max, skill[i + j]);
				dp[i + j + 1] = Math.max(dp[i + j + 1], dp[i] + (j + 1) * max);
			}
		}
		out.println(dp[N]);
		in.close();
		out.close();
	}
	
}