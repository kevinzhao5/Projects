import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Permutations {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		if (n == 1) out.println("1");
		else if (n <= 3) out.println("NO SOLUTION");
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= n; i += 2) {
				sb.append(i + " ");
			}
			System.out.print(sb.toString());
			sb = null;
			StringBuilder sb1 = new StringBuilder();
			for (int i = 1; i < n - 1; i += 2) {
				sb1.append(i + " ");
			}
			System.out.print(sb1.toString());
			if (n % 2 == 0) System.out.println(n - 1);
			else System.out.println(n);
		}
		in.close();
		out.close();
	}
	
}
