/*
ID: awesome25
LANG: JAVA
TASK: fracdec
*/
import java.io.*;
import java.util.*;

class fracdec {
	
	static int N, D;
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fracdec.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		in.close();
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		StringBuffer d = new StringBuffer(".");
		StringBuffer g = new StringBuffer("");
		if (N >= D) {
			g.append(N / D);
			N %= D;
		} else g.append("0");
		if (N == 0) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
			out.println(g + ".0");
			out.close();
		}
		else {
			int count = 0;
			int[] v = new int[100001];
			Arrays.fill(v, -1);
			while (v[N] == -1) {
				v[N] = count++;
				N *= 10;
				d.append(N / D);
				N %= D;
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
			if (N == 0) out.println(g + d.substring(0, d.length() - 1));
			else {
				String output = g + d.substring(0, v[N] + 1) + "(" + d.substring(v[N] + 1, d.length()) + ")";
				for (int i = 0; i < output.length() / 76 + 1; i++) {
					out.print(output.substring(i * 76, Math.min((i + 1) * 76, output.length())) + "\n");
				}
			}
			out.close();
		}
	}
	
}