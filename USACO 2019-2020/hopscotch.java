import java.io.*;
import java.util.*;

class k {
	
	TreeMap<Integer, Integer> t;
	
	public k() {
		t = new TreeMap<Integer, Integer>();
	}
	
}

class BinaryIndexedTree1 {
	
	long[] f;
	static final long mod = 1000000007;
	
	public BinaryIndexedTree1(int n) {
		f = new long[n + 1];
	}

	public void update(int index, long val) {
		while (index < f.length) {
			f[index] = (f[index] + val) % mod;
			index += (index & (-index));
		}
	}

	public long rsq(int n) {
		long sum = 0;
		while (n > 0) {
			sum = (sum + f[n]) % mod;
			n -= (n & (-n));
		}
		return sum;
	}
	
	public long rsq(int a, int b) {
		long ans = rsq(b);
		if (a == 1) return ans;
		else {
			ans = ans - rsq(a - 1);
			if (ans < 0) ans += mod;
			return ans;
		}
	}
	
}

public class hopscotch {
	
	static final long mod = 1000000007;
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[][] b = new int[R][C];
		BinaryIndexedTree1[] bit = new BinaryIndexedTree1[K];
		int[] lengths = new int[K];
		k[] colMap = new k[K];
		for (int i = 0; i < K; i++) {
			colMap[i] = new k();
		}
		for (int i = 0; i < R; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < C; j++) {
				int n = Integer.parseInt(s.nextToken()) - 1;
				b[i][j] = n;
				lengths[n]++;
			}
		}
		for (int i = 0; i < K; i++) {
			bit[i] = new BinaryIndexedTree1(lengths[i]);
		}
		Arrays.fill(lengths, 0);
		for (int j = 0; j < C; j++) {
			for (int i = 0; i < R; i++) {
				int n = b[i][j];
				if (colMap[n].t.isEmpty() || !colMap[n].t.containsKey(j)) {
					colMap[n].t.put(j, lengths[n]);
					lengths[n]++;
				}
			}
		}
		long[][] dp = new long[R][C];
		dp[0][0] = 1l;
		long[][] prefix = new long[R][C];
		prefix[0][0] = 1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (i == 0 && j == 0) continue;
				long value = 0l, sub = 0l;
				int n = b[i][j];
				if (i > 0 && j > 0) {
					value = prefix[i - 1][j - 1];
					sub = bit[n].rsq(1, colMap[n].t.get(j));
					value -= sub;
					if (value < 0) value += mod;
				}
				dp[i][j] = value;
				if (i > 0) {
					n = b[i - 1][j];
					bit[n].update(colMap[n].t.get(j) + 1, dp[i - 1][j]);
				}
				if (i > 0 && j > 0) prefix[i][j] = ((prefix[i - 1][j] + prefix[i][j - 1]) % mod - prefix[i - 1][j - 1] + dp[i][j]) % mod;
				else if (i > 0) prefix[i][j] = (prefix[i - 1][j] + dp[i][j]) % mod;
				else if (j > 0) prefix[i][j] = (prefix[i][j - 1] + dp[i][j]) % mod;
				if (prefix[i][j] < 0) prefix[i][j] += mod;
			}
		}
		out.println(dp[R - 1][C - 1]);
		in.close();
		out.close();
	}
	
}