import java.io.*;
import java.util.*;

public class lazy {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken()), start = (N + 1) % 2, ans = 0;
		int[][] f = new int[801][801];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				f[i + j][N - i + j - 1] = Integer.parseInt(s.nextToken());
			}
		}
		N = N * 2 - 1;
		for (int i = 0; i < N; i++) {
			int total = 0;
			for (int j = Math.max(i - K, 0); j < N; j++) {
				if (j > i + K) break;
				for (int k = Math.max(start - K, 0); k < N; k++) {
					if (k > start + K) break;
					total += f[j][k];
				}
			}
			ans = Math.max(ans, total);
			for (int j = start + 1; j + K < N; j++) {
				for (int k = Math.max(i - K, 0); k < N; k++) {
					if (k > i + K) break;
					if (j - K > 0) total -= f[k][j - K - 1];
					total += f[k][j + K];
				}
				if (j % 2 == start) ans = Math.max(ans, total);
			}
			start = 1 - start;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}