import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class BitStrings {
	
	public static final long mod = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		long n = Integer.parseInt(in.readLine());
		long ans = 1;
		for (int i = 0; i < n; i++) {
			ans = (ans * 2) % mod;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}
