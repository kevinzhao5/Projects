import java.util.*;
import java.io.*;

public class PebbleSolitaire {
	
	static int[] dp = new int[(1 << 12) + 10];
	static int b = (1 << 12) - 1;
	
	public static int rec(int bitmask, int sc) {
		if (sc == 1 || bitmask + (bitmask & ~bitmask) == b) return dp[bitmask] = 1;
		if (dp[bitmask] != -1) return dp[bitmask];
		int pos = bitmask;
		int min = sc;
		while (pos > 0) {
			int p = pos & -pos;
			pos -= p;
			if (p >= (1 << 2) && (bitmask & (p >> 1)) == 0 && (bitmask & (p >> 2)) == 0) {
				//System.out.println("first " + Integer.toBinaryString((((bitmask | (p >> 1)) | (p >> 2)) & ~(p))));
				min = Math.min(rec((((bitmask | (p >> 1)) | (p >> 2)) & ~(p)), sc - 1), min);
			}
			if (p <= (1 << 9) && (bitmask & (p << 1)) == 0 && (bitmask & (p << 2)) == 0) {
				//System.out.println("second " + Integer.toBinaryString((((bitmask | (p << 1)) | (p << 2)) & ~(p))));
				min = Math.min(rec((((bitmask | (p << 1)) | (p << 2)) & ~(p)), sc - 1), min);
			}
		}
		//if (min > sc) System.out.println("mask " + Integer.toBinaryString(bitmask) + " " + sc + " " + min);
		return dp[bitmask] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int tests = Integer.parseInt(in.readLine());
		Arrays.fill(dp, -1);
		for (int t = 0; t < tests; t++) {
			String str = in.readLine();
			int bitmask = 0, score = 12;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '-') {
					bitmask |= 1 << (11 - i);
					score--;
				}
			}
			out.println(rec(bitmask, score));
		}
		in.close();
		out.close();
	}

}
