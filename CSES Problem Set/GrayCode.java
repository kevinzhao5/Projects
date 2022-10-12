import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class GrayCode {
	
	public static String[] rec(int n) {
		if (n == 1) {
			return new String[]{ "0", "1" };
		}
		String[] prev = rec(n - 1);
		String[] curr = new String[prev.length * 2];
		for (int i = 0; i < prev.length; i++) {
			curr[i] = prev[i] + "0";
		}
		for (int i = prev.length; i < 2 * prev.length; i++) {
			curr[i] = prev[2 * prev.length - i - 1] + "1";
		}
		return curr;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		String[] ans = rec(n);
		for (String s : ans) {
			System.out.println(s);
		}
		in.close();
		out.close();
	}
	
}
