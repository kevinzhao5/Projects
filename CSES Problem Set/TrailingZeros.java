import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class TrailingZeros {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		long n = Integer.parseInt(in.readLine());
		long div = 5;
		long ans = 0;
		while (n / div > 0) {
			ans += n / div;
			div *= 5;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}
