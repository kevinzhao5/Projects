import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class TwoKnights {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		long[] count = new long[100001];
		count[1] = 0;
		count[2] = 6;
		count[3] = 28;
		count[4] = 96;
		count[5] = 252;
		count[6] = 550;
		count[7] = 1056;
		for (long i = 8; i <= n; i++) {
			count[(int)i] = count[(int)i - 2] + 4 * (i - 1) * ( (i - 2) * (i - 2) - 4) + 2 * (i - 1) * (4 * (i - 1) - 1) + 24;
		}
		for (int i = 1; i <= n; i++) {
			System.out.println(count[i]);
		}
		in.close();
		out.close();
	}
	
}
