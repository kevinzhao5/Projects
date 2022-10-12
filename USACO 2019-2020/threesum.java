import java.io.*;
import java.util.*;

class query implements Comparable<query> {
	
	int a, b, c;
	
	public query(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
	}

	@Override
	public int compareTo(query o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class threesum {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/threesum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/threesum.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		TreeMap<Integer, Integer> nums = new TreeMap<Integer, Integer>();
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st1.nextToken());
			if (nums.containsKey(A[i])) nums.put(A[i], nums.get(A[i]) + 1);
			else nums.put(A[i], 1);
		}
		query[] queries = new query[Q];
		for (int i = 0; i < Q; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1;
			queries[i] = new query(a, b, i);
		}
		Arrays.sort(queries);
		long[] ans = new long[Q];
		
		
		int counter = 0;
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		long[] dp = new long[N];
		for (int j = 0; j < N; j++) {
			if (j > 0) dp[j] = dp[j - 1];
			if (map.containsKey(-A[j])) {
				dp[j] += map.get(-A[j]);
			}
			while (counter < Q && queries[counter].b == j) {
				ans[queries[counter].c] = dp[j];
				counter++;
			}
			if (counter >= Q) break;
			for (int k = 0; k < j; k++) {
				int sum = A[k] + A[j];
				if (map.containsKey(sum)) map.put(sum, map.get(sum) + 1);
				else map.put(sum, 1);
			}
		}
		System.out.println(Arrays.toString(dp));
		int subtract = 0;
		for (int i = 0; i < N; i++) {
			int sub = 0;
			if (nums.containsKey(-2 * A[i])) sub = nums.get(-2 * A[i]);
			subtract += map.get(-A[i]) - sub;
			for (int j = i + 1; j < N; j++) {
				while (counter < Q && queries[counter].b == j && queries[counter].a == i + 1) {
					ans[queries[counter].c] = dp[j] - subtract;
					counter++;
				}
			}
		}
		
		
		for (int i = 0; i < Q; i++) {
			out.println(ans[i]);
		}
		in.close();
		out.close();
	}
	
}