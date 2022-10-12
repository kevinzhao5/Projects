import java.io.*;
import java.util.*;

public class cowcode {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowcode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		String str = st.nextToken();
		long n = Long.parseLong(st.nextToken()), len = 0l, l = str.length();
		int off = 0;
		while (n > l) {
			int p = 0;
			while (l * Math.pow(2, p) < n) p++;
			len = l * (long)Math.pow(2, p);
			if (n == (len / 2) + 1) {
				n -= 2;
				off++;
			}
			n %= (len / 2) + 1;
		}
		out.println("" + str.charAt((int) ((n - 1 + off) % l)));
		in.close();
		out.close();
	}
	
}