import java.io.*;
import java.util.*;

public class help {
	
	static class two implements Comparable<two> {
		
		int a, b;
		
		public two (int a1, int b1) {
			a = a1;
			b = b1;
		}

		@Override
		public int compareTo(two o) {
			return this.a - o.a;
		}
		
	}
	
	static final long mod = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("help.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));
		int N = Integer.parseInt(in.readLine());
		two[] intvs = new two[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			intvs[i] = new two(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(intvs);
		long[] pow2 = new long[N];
		pow2[0] = 1;
		for (int i = 1; i < N; i++) {
			pow2[i] = (pow2[i - 1] * 2) % mod;
		}
		TreeSet<Integer> r = new TreeSet<Integer>();
		long[] dp = new long[N];
		dp[0] = 1;
		r.add(intvs[0].b);
		for (int i = 1; i < N; i++) {
			int l = intvs[i].a;
			while (r.size() > 0) {
				if (r.first() < l) {
					r.remove(r.first());
				} else {
					break;
				}
			}
			dp[i] = (2 * dp[i - 1] + pow2[i - r.size()]) % mod;
			r.add(intvs[i].b);
		}
		out.println(dp[N - 1]);
		in.close();
		out.close();
	}
	
}