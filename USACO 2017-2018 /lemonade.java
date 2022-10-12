//ID: awesome25

import java.io.*;
import java.util.*;

public class lemonade {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lemonade.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
		int len = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] cows = new int[len];
		for (int i = 0; i < len; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cows);
		boolean check = false;
		for (int i = len - 1; i >= 0; i--) {
			if (cows[i] < len - 1 - i) {
				out.println(len - i - 1);
				check = true;
				break;
			}
		}
		if (!check) out.println(len);
		in.close();
		out.close();
	}
}