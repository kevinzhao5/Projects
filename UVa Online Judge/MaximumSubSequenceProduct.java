import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class MaximumSubSequenceProduct {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (in.ready()) {
			long n = 0l;
			int counter = 0;
			BigInteger[][] dp = new BigInteger[101][101];
			BigInteger max = BigInteger.valueOf((long)Integer.MIN_VALUE);
			while (n != -999999l) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				n = Long.parseLong(st.nextToken());
				while (n != -999999l) {
					dp[counter][0] = BigInteger.valueOf(n);
					counter++;
					if (BigInteger.valueOf(n).compareTo(max) > 0) max = BigInteger.valueOf(n);
					n = Long.parseLong(st.nextToken());
				}
			}
			for (int i = 0; i < counter; i++) {
				for (int j = 1; j < counter - i; j++) {
					if (i + j > counter) break;
					dp[i][j] = dp[i][j - 1].multiply(dp[i + j][0]);
					if (dp[i][j].compareTo(max) > 0) max = dp[i][j];
				}
			}
			System.out.println(max);
		}
		in.close();
	}

}
