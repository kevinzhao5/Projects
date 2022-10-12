import java.io.*;
import java.util.*;

public class snowboots {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(s1.nextToken()), B = Integer.parseInt(s1.nextToken());
		int[] d = new int[N];
		int[] sd = new int[B];
		int[] ss = new int[B];
		StringTokenizer s = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			d[i] = Integer.parseInt(s.nextToken());
		}
		for (int i = 0; i < B; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			sd[i] = Integer.parseInt(st.nextToken());
			ss[i] = Integer.parseInt(st.nextToken());
		}
		int[] lo = new int[N];
		Arrays.fill(lo, Integer.MAX_VALUE);
		lo[0] = 0;
		for (int i = 0; i < N; i++) {
			if (lo[i] == Integer.MAX_VALUE) continue;
			for (int j = lo[i]; j < B; j++) {
				if (d[i] > sd[j]) continue;
				for (int k = 1; k <= ss[j]; k++) {
					if (i + k >= N) break;
					if (d[i + k] > sd[j]) continue;
					lo[i + k] = Math.min(lo[i + k], j);
				}
			}
		}
		out.println(lo[N - 1]);
		in.close();
		out.close();
	}
	
}