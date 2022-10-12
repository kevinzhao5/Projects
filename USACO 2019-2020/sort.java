import java.io.*;
import java.util.*;

public class sort {
	
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
		
	}

	
	static class two implements Comparable<two> {
		
		int a, b;
		
		public two(int aa, int bb) {
			a = aa;
			b = bb;
		}

		@Override
		public int compareTo(two o) {
			if (this.a == o.a) return this.b - o.b;
			return this.a - o.a;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int N = Integer.parseInt(in.readLine());
		two[] arr = new two[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new two(Integer.parseInt(in.readLine()), i + 1);
		}
		Arrays.sort(arr);
		int ans = 1;
		BinaryIndexedTree bit = new BinaryIndexedTree(N);
		for (int i = 0; i < N; i++) {
			bit.update(arr[i].b, 1);
			ans = Math.max(ans, i + 1 - bit.rsq(i + 1));
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}