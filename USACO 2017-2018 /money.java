/*
ID: awesome25
LANG: JAVA
TASK: money
*/
import java.io.*;
import java.util.*;

class money {
	
	static int[] coins;
	static int len;
	static long[][] nums = new long[10001][26];
	
	public static long solve(int curr, int index) {
		if (curr < 0 || index < 0) return 0;
		if (curr == 0) return nums[curr][index] = 1;
		if (nums[curr][index] != 0) return nums[curr][index];
		return nums[curr][index] = solve(curr, index - 1) + solve(curr - coins[index], index);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int numCoin = Integer.parseInt(st.nextToken()), amt = Integer.parseInt(st.nextToken()), counter = 0;
		String str = in.readLine();
		coins = new int[numCoin];
		while (str != null) {
			StringTokenizer st1 = new StringTokenizer(str);
			while (st1.hasMoreTokens()) {
				int n = Integer.parseInt(st1.nextToken());
				coins[counter] = n;
				counter++;
			}
			str = in.readLine();
		}
		Arrays.sort(coins);
		out.println(solve(amt, numCoin - 1));
		in.close();
		out.close();
	}
}