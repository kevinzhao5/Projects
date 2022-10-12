/*
ID: awesome25
LANG: JAVA
TASK: kimbits
*/
import java.io.*;
import java.util.*;

class kimbits {
	
	static long index;
	static int len, num;
	static String str;
	static long[][] pos = new long[32][32];

	public static void solve(int max, int left, long ind) {
		if (left == 0) return;
		if (max == len - 1) {
			if (ind == 2) str = str.substring(0, max) + "1";
		} else {
			for (int i = len - 1; i >= max; i--) {
				if (pos[len - i][left] >= ind) {
					if (ind != 1) str = str.substring(0, i) + "1" + str.substring(i + 1, str.length());
					solve(i, left - 1, ind - (long) (pos[len - i - 1][left]));
					return;
				}
			}
		}
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		len = Integer.parseInt(st.nextToken());
		num = Integer.parseInt(st.nextToken());
		index = Long.parseLong(st.nextToken());
		str = ("0000000000000000000000000000000".substring(0, len));
		for (int i = 0; i < 32; i++) {
			pos[0][i] = 1;
			pos[i][0] = 1;
		}
		for (int i = 1; i <= 31; i++) {
			for (int x = 1; x <= 31; x++) {
				pos[i][x] = pos[i - 1][x - 1] + pos[i - 1][x];
			}
		}
		solve(0, num, index);
		out.println(str);
		in.close();
		out.close();
	}
	
}