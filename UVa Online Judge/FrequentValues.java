import java.util.*;
import java.io.*;

class SegmentTree {

	int[] s, nums;
	int len;
	
	public SegmentTree(int[] n) {
		nums = n;
		len = n.length;
		s = new int[(int) Math.round(2 * (Math.pow(2, Math.floor(Math.log(len) / Math.log(2)) + 1)))];
		buildTree(1, 0, len - 1);
	}
	
	public void buildTree(int index, int l, int r) {
		if (l == r) s[index] = l;
		else {
			int left = index * 2, right = index * 2 + 1;
			buildTree(left, l, (l + r) / 2);
			buildTree(right, (l + r) / 2 + 1, r);
			int a = s[left], b = s[right];
			if (nums[a] > nums[b]) s[index] = a;
			else s[index] = b;
		}
	}
	
	public int rmq(int index, int l, int r, int a, int b) {
		if (a > r || b < l) return -1;
		if (r <= b && l >= a) return s[index];
		int left = rmq(index * 2, l, (l + r) / 2, a, b), right = rmq(index * 2 + 1, (l + r) / 2 + 1, r, a, b);
		if (left == -1) return right;
		if (right == -1) return left;
		if (nums[left] > nums[right]) return left;
		return right;
	}
	
	public int updatePoint(int index, int l, int r, int pos, int newValue) {
	    if (pos > r || pos < l) return s[index];
	    if (l == pos && r == pos) {
	      nums[pos] = newValue;
	      return s[index] = l;
	    }
	    int val1 = updatePoint(index * 2, l, (l + r) / 2, pos, newValue), val2 = updatePoint(index * 2 + 1, (l + r) / 2 + 1, r, pos, newValue);
	    if (nums[val1] < nums[val2]) return s[index] = val1;
	    return s[index] = val2;
	  }
	
}

class FrequentValues {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int N = -1, Q = -1;
		while (N != 0) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) break;
			Q = Integer.parseInt(st.nextToken());
			int[] vals = new int[200001];
			int[] counts = new int[N];
			int[] numbers = new int[N];
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				int a = Integer.parseInt(st1.nextToken());
				vals[a + 100000]++;
				numbers[i] = a;
			}
			for (int i = 0; i < N; i++) {
				counts[i] = vals[numbers[i] + 100000];
			}
			SegmentTree sgt = new SegmentTree(counts);
			for (int i = 0; i < Q; i++) {
				StringTokenizer s = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(s.nextToken()), b = Integer.parseInt(s.nextToken());
				out.println(sgt.rmq(1, 0, N - 1, a, b));
			}
		}
		in.close();
		out.close();
	}

}
