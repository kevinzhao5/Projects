import java.io.*;
import java.util.*;

class o implements Comparable<o> {
	
	int a, b;
	
	public o(int c, int d) {
		a = c;
		b = d;
	}

	@Override
	public int compareTo(o o) {
		return this.a - o.a;
	}
	
}

public class fairphoto {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/fairphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/fairphoto.out")));
		int N = Integer.parseInt(in.readLine());
		o[] cows = new o[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()), b = 0;
			if (st.nextToken().equals("W")) b = 1;
			else b = -1;
			cows[i] = new o(a, b);
		}
		Arrays.sort(cows);
		int max = 0;
		int[] dp = new int[N];
		TreeMap<Integer, Integer> to = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> tn = new TreeMap<Integer, Integer>();
		TreeSet<Integer> oa = new TreeSet<Integer>();
		TreeSet<Integer> na = new TreeSet<Integer>();
		for (int i = 0; i < N; i++) {
			if (i > 0) dp[i] = dp[i - 1];
			dp[i] += cows[i].b;
			if (dp[i] < 0) {
				if (tn.size() == 0 || !na.contains(dp[i])) {
					tn.put(i, dp[i]);
					na.add(dp[i]);
				}
			}
			if (dp[i] % 2 == 1 || dp[i] % 2 == -1) {
				if (to.size() == 0 || !oa.contains(dp[i])) {
					to.put(i, dp[i]);
					oa.add(dp[i]);
				}
			}
		}
		for (int i = N - 1; i > 0; i--) {
			if (dp[i] < 0) {
				for (int j:tn.keySet()) {
					if (j >= i) break;
					if ((dp[i] - tn.get(j)) >= 0 && (dp[i] - tn.get(j)) % 2 == 0) {
						max = Math.max(max, cows[i].a - cows[j + 1].a);
						break;
					}
				}
			} else if (dp[i] % 2 == 0) max = Math.max(max, cows[i].a - cows[0].a);
			else if (dp[i] % 2 == 1){
				for (int j:to.keySet()) {
					if (j >= i) break;
					if ((dp[i] - to.get(j)) >= 0 && (dp[i] - to.get(j)) % 2 == 0) {
						max = Math.max(max, cows[i].a - cows[j + 1].a);
						break;
					}
				}
			}
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}