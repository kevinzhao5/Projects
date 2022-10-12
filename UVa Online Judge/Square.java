import java.util.*;
import java.io.*;

class Square {
	
	static int[] len;
	static byte[] dp;
	static int sum = 0, m = 0, side = 0;
	
	public static int rec(int bitmask, int curr, int s) {
		if (curr == side) {
			curr = 0;
			s++;
		}
		if (bitmask == 0) {
			return dp[bitmask] = 4;
		}
		if (dp[bitmask] != -1) {
			return dp[bitmask] = (byte) Math.max(dp[bitmask], s);
		}
		int max = s;
		int pos = bitmask;
		while (pos > 0) {
			int p = pos & -pos;
			pos -= p;
			int sd = curr + len[(int) (Math.log(p) / Math.log(2))];
			if (sd > side) continue;
			max = Math.max(rec(bitmask & ~(p), sd, s), max);
			if (max == 4) return 4;
		}
		return dp[bitmask] = (byte) max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int t = Integer.parseInt(in.readLine());
		for (int e = 0; e < t; e++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			m = Integer.parseInt(st.nextToken());
			sum = 0;
			len = new int[m];
			side = 0;
			for (int i = 0; i < m; i++) {
				len[i] = Integer.parseInt(st.nextToken());
				sum += len[i];
			}
			if (sum % 4 != 0) {
				out.println("no");
				continue;
			}
			side = sum / 4;
			boolean b = false;
			for (int i = 0; i < m; i++) {
				if (len[i] > side) {
					out.println("no");
					b = true;
					break;
				}
			}
			if (b) continue;
			int bitmask = (1 << m) - 2;
			dp = new byte[(1 << m) + 1];
			Arrays.fill(dp, (byte) (-1));
			if (rec(bitmask, len[0], 0) == 4) out.println("yes");
			else out.println("no");
		}
		in.close();
		out.close();
	}

}
