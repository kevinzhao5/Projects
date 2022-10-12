import java.io.*;

public class sqrtlogsin {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[1000001];
		dp[0] = 1;
		for (int i = 1; i <= 1000000; i++) {
			dp[i] = dp[(int)Math.floor(i - Math.sqrt(i))] + dp[(int)Math.floor(Math.log(i))] + dp[(int)Math.floor(i * Math.sin(i) * Math.sin(i))] % 1000000;
		}
		int n = 0;
		while (n != -1) {
			n = Integer.parseInt(in.readLine());
			if (n == -1) break;
			System.out.println(dp[n] % 1000000);
		}
		in.close();
	}

}
