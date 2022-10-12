/*
ID: awesome25
LANG: JAVA
TASK: nuggets
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class nuggets {
	
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
		int N = Integer.parseInt(in.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(in.readLine());
		}
		boolean b = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (gcd(nums[i], nums[j]) == 1) b = true;
			}
		}
		if (!b) out.println(0);
		else {
			boolean[] dp = new boolean[70000];
			dp[0] = true;
			int ans = 0;
			for (int i = 0; i < 65000; i++) {
				if (dp[i]) {
					for (int j = 0; j < N; j++) {
						dp[i + nums[j]] = true;
					}
				} else ans = i;
			}
			out.println(ans);
		}
		in.close();
		out.close();
	}
	
}