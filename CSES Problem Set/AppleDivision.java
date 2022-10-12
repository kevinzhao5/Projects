import java.io.*;
import java.util.*;

public class AppleDivision {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		long[] weights = new long[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		long sum = 0;
		for (int i = 0; i < n; i++) {
			weights[i] = Long.parseLong(st.nextToken());
			sum += weights[i];
		}
		long minDiff = Long.MAX_VALUE;
		for (int i = 0; i < (1 << n); i++) {
			long groupSum = 0;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					groupSum += weights[j];
				}
			}
			long groupSum2 = sum - groupSum;
			minDiff = Math.min(minDiff, Math.abs(groupSum - groupSum2));
		}
		System.out.println(minDiff);
		in.close();
		out.close();
	}
	
}
