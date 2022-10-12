import java.util.*;
import java.io.*;

class FenwickTree1 {
	
	long[] f;
	
	public FenwickTree1(int n) {
		f = new long[n + 1];
	}

	public void change(int index, int val) {
		while (index < f.length) {
			f[index] += val;
			index += lsb(index);
		}
	}
	
	public int lsb(int n) {
		return (n & (-n));
	}
	
	public int rsq(int n) {
		int sum = 0;
		while (n > 0) {
			sum += f[n];
			n -= lsb(n);
		}
		return sum;
	}
	
	public int rsq(int a, int b) {
		int ans = rsq(b);
		if (a == 1) return ans;
		else return ans - rsq(a - 1);
	}
	
}

class IntervalProduct {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (in.ready()) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
			int[] nums = new int[N + 1];
			FenwickTree1 ft = new FenwickTree1(N);
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			for (int i = 1; i <= N; i++) {
				int n = Integer.parseInt(st1.nextToken());
				if (n == 0) nums[i] = -1000000;
				else if (n < 0) nums[i] = 1;
				else nums[i] = 0;
				ft.change(i, nums[i]);
			}
			StringBuilder ans = new StringBuilder("");
			for (int i = 0; i < K; i++) {
				StringTokenizer s = new StringTokenizer(in.readLine());
				if (s.nextToken().charAt(0) == 'C') {
					int index = Integer.parseInt(s.nextToken()), newVal = Integer.parseInt(s.nextToken());
					if (newVal == 0) newVal = -1000000;
					else if (newVal < 0) newVal = 1;
					else newVal = 0;
					int diff = newVal - nums[index];
					nums[index] = newVal;
					ft.change(index, diff);
				} else {
					int a = Integer.parseInt(s.nextToken()), b = Integer.parseInt(s.nextToken());
					long p = ft.rsq(a, b);
					if (p < 0) ans.append("0");
					else if (p % 2 == 0) ans.append("+");
					else ans.append("-");
				}
			}
			out.println(ans);
		}
		in.close();
		out.close();
	}

}
