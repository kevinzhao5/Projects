import java.util.*;
import java.io.*;

public class LowestPriceInTown {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int s = 1;
		while (in.ready()) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			double p = Double.parseDouble(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			double[] prices = new double[n + 1];
			int[] items = new int[n + 1];
			items[0] = 1;
			prices[0] = p;
			for (int i = 1; i <= n; i++) {
				StringTokenizer st1 = new StringTokenizer(in.readLine());
				items[i] = Integer.parseInt(st1.nextToken());
				prices[i] = Double.parseDouble(st1.nextToken());
			}
			StringTokenizer ln = new StringTokenizer(in.readLine());
			System.out.println("Case " + s + ":");
			while (ln.hasMoreTokens()) {
				int e = Integer.parseInt(ln.nextToken());
				double[] dp = new double[e + 1];
				Arrays.fill(dp, Integer.MAX_VALUE);
				dp[0] = 0;
				for (int i = 1; i <= e; i++) {
					for (int j = 0; j < n + 1; j++) {
						dp[i] = Math.min(dp[i], dp[Math.max(0, i - items[j])] + prices[j]);
					}
				}
				System.out.printf("Buy %d for $%.2f\n", e, dp[e]);
			}
			s++;
		}
		in.close();
	}

}
