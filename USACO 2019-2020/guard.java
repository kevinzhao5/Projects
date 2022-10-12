import java.io.*;
import java.util.*;

class f implements Comparable<f> {
	
	int a, b, c;
	
	public f(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
	}

	@Override
	public int compareTo(f o) {
		if(this.a == o.a) return o.b - this.b;
		return o.a - this.a;
	}
	
}

public class guard {
	
	static int N, H;
	static int[] height;
	static int[] weight;
	static int[] strength;
	static int[] dp;
	static int[] weights;
	static int[] heights;
	
	public static void init(int bitmask, int counter) {
		if (counter == N) {
			int h = 0, w = 0;
			for (int i = 0; i < N; i++) {
				if ((bitmask & (1 << i)) != 0) {
					h += height[i];
					w += weight[i];
				}
			}
			weights[bitmask] = w;
			heights[bitmask] = h;
		} else {
			init(bitmask, counter + 1);
			init(bitmask | (1 << counter), counter + 1);
		}
	}
	
	public static int rec(int bitmask) {
		if (dp[bitmask] != -1) return dp[bitmask];
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if ((bitmask & (1 << i)) != 0) {
				int newmask = bitmask ^ (1 << i);
				int nans = rec(newmask), nw = weights[newmask], s = strength[i];
				if (nans == Integer.MIN_VALUE) continue;
				if (s < nw) continue;
				ans = Math.max(ans, Math.min(nans, s - nw));
			}
		}
		return dp[bitmask] = ans;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("guard.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("guard.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		height = new int[N];
		weight = new int[N];
		strength = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			height[i] = Integer.parseInt(s.nextToken());
			weight[i] = Integer.parseInt(s.nextToken());
			strength[i] = Integer.parseInt(s.nextToken());
		}
		dp = new int[(int) (Math.pow(2, N))];
		Arrays.fill(dp, -1);
		dp[0] = Integer.MAX_VALUE;
		weights = new int[(int) (Math.pow(2, N))];
		heights = new int[(int) (Math.pow(2, N))];
		init(0, 0);
		rec((1 << N) - 1);
		long ans = -1;
		for (int i = 0; i < dp.length; i++) {
			if (heights[i] >= H) ans = Math.max(ans, dp[i]);
		}
		if (ans >= 0) out.println(ans);
		else out.println("Mark is too tall");
		in.close();
		out.close();
	}
	
}