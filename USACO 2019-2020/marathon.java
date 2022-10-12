import java.io.*;
import java.util.*;

class BinaryIndexedTree {
	
	int[] f;
	
	public BinaryIndexedTree(int n) {
		f = new int[n + 1];
	}

	public void update(int index, int val) {
		while (index < f.length) {
			f[index] += val;
			index += (index & (-index));
		}
	}

	public int rsq(int n) {
		int sum = 0;
		while (n > 0) {
			sum += f[n];
			n -= (n & (-n));
		}
		return sum;
	}
	
	public int rsq(int a, int b) {
		int ans = rsq(b);
		if (a == 1) return ans;
		else return ans - rsq(a - 1);
	}
	
}

class SegmentTree {

	int len;
	int[] s, arr;
	
	public SegmentTree(int[] n) {
		len = n.length;
		arr = n;
		s = new int[(int) (2 * (Math.pow(2, Math.ceil(Math.log(len) / Math.log(2)))) + 1)];
		build(1, 0, len - 1);
	}
	
	public int build(int index, int l, int r) {
		if (l == r) {
			return s[index] = arr[l];
		}
		int min = Math.min(build(index * 2, l, (l + r) / 2), build(index * 2 + 1, (l + r) / 2 + 1, r));
		return s[index] = min;
	}
	
	public int rmq(int i, int j) {
		return rmq(1, 0, len - 1, i, j);
	}
	
	public int rmq(int index, int l, int r, int a, int b) {
		if (a > r || b < l) return Integer.MAX_VALUE;
		if (r <= b && l >= a) return s[index];
		int left = rmq(index * 2, l, (l + r) / 2, a, b), right = rmq(index * 2 + 1, (l + r) / 2 + 1, r, a, b);
		return Math.min(left, right);
	}
	
	public void change(int index, int val) {
		change(1, 0, len - 1, index, val);
	}
	
	public int change(int index, int l, int r, int pos, int val) {
	    if (pos > r || pos < l) return s[index];
	    if (l == pos && r == pos) {
	    	arr[pos] = val;
	    	return s[index] = val;
	    }
	    int left = change(index * 2, l, (l + r) / 2, pos, val), right = change(index * 2 + 1, (l + r) / 2 + 1, r, pos, val);
	    return s[index] = Math.min(left, right);
	}

}

public class marathon {
	
	static int[] X;
	static int[] Y;
	static int N, Q;
	
	public static int dist(int a, int b) {
		return (int) (Math.abs(X[a] - X[b])) + (int) (Math.abs(Y[a] - Y[b]));
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		X = new int[N];
		Y = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			X[i] = Integer.parseInt(s.nextToken());
			Y[i] = Integer.parseInt(s.nextToken());
		}
		BinaryIndexedTree bit = new BinaryIndexedTree(N - 1);
		for (int i = 0; i < N - 1; i++) {
			bit.update(i + 1, dist(i, i + 1));
		}
		int[] saved = new int[N];
		saved[0] = 0;
		saved[N - 1] = 0;
		for (int i = 1; i < N - 1; i++) {
			saved[i] = dist(i - 1, i + 1) - dist(i - 1, i) - dist(i, i + 1);
		}
		SegmentTree segt = new SegmentTree(saved);
		for (int q = 0; q < Q; q++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			if (s.nextToken().charAt(0) == 'U') {
				int index = Integer.parseInt(s.nextToken()) - 1, x = Integer.parseInt(s.nextToken()), y = Integer.parseInt(s.nextToken());
				if (index > 0) bit.update(index, (int) (Math.abs(X[index - 1] - x)) + (int) (Math.abs(Y[index - 1] - y)) - dist(index, index - 1));
				if (index < N - 1) bit.update(index + 1, (int) (Math.abs(X[index + 1] - x)) + (int) (Math.abs(Y[index + 1] - y)) - dist(index, index + 1));
				X[index] = x;
				Y[index] = y;
				if (index > 0 && index < N - 1) segt.change(index, dist(index - 1, index + 1) - dist(index - 1, index) - dist(index, index + 1));
				if (index > 1) segt.change(index - 1, dist(index - 2, index) - dist(index - 1, index) - dist(index - 2, index - 1));
				if (index < N - 2) segt.change(index + 1, dist(index + 2, index) - dist(index + 1, index) - dist(index + 2, index + 1));
			} else {
				int i = Integer.parseInt(s.nextToken()) - 1, j = Integer.parseInt(s.nextToken()) - 1;
				if (j <= i + 1) out.println(bit.rsq(i + 1, j));
				else out.println(bit.rsq(i + 1, j) + segt.rmq(i + 1, j - 1));
			}
		}
		in.close();
		out.close();
	}
	
}