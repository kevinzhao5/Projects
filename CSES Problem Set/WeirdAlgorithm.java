import java.io.*;

public class WeirdAlgorithm {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		long n = Integer.parseInt(in.readLine());
		out.print(n);
		while (n != 1) {
			if (n % 2 == 0) n /= 2;
			else n = n * 3 + 1;
			out.print(" ");
			out.print(n);
			out.flush();
		}
		out.println();
		in.close();
		out.close();
	}
	
}
