import java.util.*;
import java.io.*;

public class homework {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("homework.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		int n = Integer.parseInt(in.readLine());
		int[] scores = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		int[] min = new int[n];
		min[n - 1] = scores[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			min[i] = Math.min(min[i + 1], scores[i]);
		}
		int[] sum = new int[n];
		sum[0] = scores[0];
		for (int i = 1; i < n; i++) {
			sum[i] = scores[i] + sum[i - 1];
		}
		double max = 0;
		int total = sum[n - 1];
		for (int i = 0; i < n - 2; i++) {
			int s = total - sum[i] - min[i + 1];
			double avg = s / (n - i - 2.0);
			if (avg > max) max = avg;
		}
		for (int i = 0; i < n - 2; i++) {
			int s = total - sum[i] - min[i + 1];
			double avg = s / (n - i - 2.0);
			if (Math.abs(avg - max) < 0.0000000000001) out.println((i + 1));
		}
		in.close();
		out.close();
	}

}
