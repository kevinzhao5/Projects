import java.io.*;
import java.util.*;

public class vacation {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("vacation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vacation.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
		long[][] d = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(d[i], 1000000000000l);
			d[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			d[Integer.parseInt(s.nextToken())][Integer.parseInt(s.nextToken())] = Integer.parseInt(s.nextToken());
		}
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
		int count = 0;
		long sum = 0l;
		for (int q = 0; q < Q; q++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()), b = Integer.parseInt(s.nextToken());
			long min = 1000000000000l;
			for (int i = 1; i <= K; i++) {
				if (d[a][i] != 1000000000000l && d[i][b] != 1000000000000l && d[a][i] + d[i][b] < min) {
					min = d[a][i] + d[i][b];
				}
			}
			if (min < 1000000000000l) {
				count++;
				sum += min;
			}
		}
		out.println(count);
		out.println(sum);
		in.close();
		out.close();
	}
	
}