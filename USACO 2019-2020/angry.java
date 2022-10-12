import java.io.*;
import java.util.*;

public class angry {
	
	static int N;
	static int[] pos;
	static int[] dpLeft;
	static int[] dpRight;
	
	public static double power(int i, int j) {
		return Math.max(dpLeft[i] + 1, Math.max(dpRight[j] + 1, (pos[j] - pos[i] * 1.0) / 2));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		N = Integer.parseInt(in.readLine());
		pos = new int[N];
		for (int i = 0; i < N; i++) {
			pos[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(pos);
		dpLeft = new int[N];
		dpRight = new int[N];
		Arrays.fill(dpLeft, 1000000000);
		Arrays.fill(dpRight, 1000000000);
		dpLeft[0] = 0;
		int counter = 0;
		for (int i = 1; i < N; i++) {
			while (counter + 1 < i && pos[i] - pos[counter + 1] > dpLeft[counter + 1] + 1) counter++;
			dpLeft[i] = Math.min(dpLeft[counter + 1] + 1, pos[i] - pos[counter]);
		}
		dpRight[N - 1] = 0;
		counter = N - 1;
		for (int i = N - 2; i >= 0; i--) {
			while (counter - 1 > i && pos[counter - 1] - pos[i] > dpRight[counter - 1] + 1) counter--;
			dpRight[i] = Math.min(dpRight[counter - 1] + 1, pos[counter] - pos[i]);
		}
		double ans = Integer.MAX_VALUE;
		int a = 0, b = N - 1;
		while (a < b) {
			ans = Math.min(ans, power(a, b));
			if (dpLeft[a + 1] < dpRight[b - 1]) a++;
			else b--;
		}
		out.printf("%.1f", ans, "\n");
		in.close();
		out.close();
	}
	
}