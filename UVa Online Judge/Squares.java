import java.util.*;
import java.io.*;

public class Squares {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int e = Integer.parseInt(in.readLine());
		int[] dp = new int[10001];
		Arrays.fill(dp, 1000000000);
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= 10000; i++) {
			for (int j = 0; j <= (int)Math.floor(Math.sqrt(i)); j++) {
				dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
			}
		}
		for (int i = 0; i < e; i++) {
			System.out.println(dp[Integer.parseInt(in.readLine())]);
		}
		in.close();
	}

}
