import java.io.*;
import java.util.*;

public class CoinPiles {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int t = Integer.parseInt(in.readLine());
		for (int e = 0; e < t; e++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			if (a < b) {
				int temp = a;
				a = b;
				b = temp;
			}
			if (a > 2 * b) {
				System.out.println("NO");
				continue;
			}
			int diff = a - b;
			b += 2 * diff;
			a += diff;
			if (a % 3 == 0) System.out.println("YES");
			else System.out.println("NO");
		}
		in.close();
		out.close();
	}
	
}
