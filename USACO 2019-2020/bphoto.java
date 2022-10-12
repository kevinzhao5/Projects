import java.io.*;
import java.util.*;

public class bphoto {
	
	static class BinaryIndexedTree {
		
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


	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		int N = Integer.parseInt(in.readLine());
		int[] heights = new int[N];
		int[] normalize = new int[N];
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(in.readLine());
			normalize[i] = heights[i];
		}
		Arrays.sort(normalize);
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
		int counter = 1;
		for (int i = 0; i < N; i++) {
			if (i > 0 && normalize[i] == normalize[i - 1]) continue;
			m.put(normalize[i], counter);
			counter++;
		}
		for (int i = 0; i < N; i++) {
			heights[i] = m.get(heights[i]);
		}
		BinaryIndexedTree t = new BinaryIndexedTree(N);
		int[] L = new int[N];
		for (int i = 0; i < N; i++) {
			L[i] = t.rsq(heights[i] + 1, N);
			t.update(heights[i], 1);
		}
		BinaryIndexedTree t1 = new BinaryIndexedTree(N);
		int[] R = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			R[i] = t1.rsq(heights[i] + 1, N);
			t1.update(heights[i], 1);
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (Math.max(R[i], L[i]) > 2 * Math.min(L[i], R[i])) count++;
		}
		out.println(count);
		in.close();
		out.close();
	}
	
}