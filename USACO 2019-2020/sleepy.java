import java.io.*;
import java.util.*;

public class sleepy {
	
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
		BufferedReader in = new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		int N = Integer.parseInt(in.readLine());
		int[] cows = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
		}
		int size = 1;
		for (int j = N - 2; j >= 0; j--) {
			if (cows[j] > cows[j + 1]) break;
			size++;
		}
		int K = N - size;
		BinaryIndexedTree bit = new BinaryIndexedTree(N + 1);
		int[] moves = new int[K];
		for (int i = 0; i < K; i++) {
			int index = Arrays.binarySearch(cows, K, N, cows[i]);
			if (index < 0) index = -index - 1;
			index--;
			moves[i] = index - bit.rsq(cows[i], N);
			bit.update(cows[i], 1);
		}
		out.println(K);
		for (int i = 0; i < K - 1; i++) {
			out.print(moves[i] + " ");
		}
		out.println(moves[K - 1]);
		in.close();
		out.close();
	}
	
}