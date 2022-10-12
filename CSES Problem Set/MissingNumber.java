import java.io.*;
import java.util.*;

public class MissingNumber {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		boolean[] b = new boolean[n + 1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n - 1; i++) {
			b[Integer.parseInt(st.nextToken())] = true;
		}
		for (int i = 1; i <= n; i++) {
			if (!b[i]) out.println(i);
		}
		in.close();
		out.close();
	}
	
}
