import java.io.*;
import java.util.*;

public class NumberSpiral {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int t = Integer.parseInt(in.readLine());
		for (int e = 0; e < t; e++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			long y = Long.parseLong(st.nextToken());
			long x = Long.parseLong(st.nextToken());
			long large = Math.max(x, y);
			if (large % 2 == 0) {
				long temp = y;
				y = x;
				x = temp;
			}
			long value = (large - 1) * (large - 1);
			if (x >= y) {
				value += x;
				value += x - y;
			} else {
				value += x;
			}
			System.out.println(value);
		}
		in.close();
		out.close();
	}
	
}
