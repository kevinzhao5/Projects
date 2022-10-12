import java.io.*;
import java.util.*;

public class convention {
	
	static int[] at;
	static int N, M, C, inc;
	
	public static boolean pos(int m) {
		int curr = 0, total = 0, first = -1;
		for (int i = 0; i < N; i++) {
			if (curr == C || (at[i] - first > m && first != -1)) {
				total++;
				curr = 0;
				first = -1;
			}
			if (first == -1) {
				first = at[i];
				curr++;
			} else if (curr > 0) {
				curr++;
			}
			if (total == M) return false;
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("convention.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s1.nextToken());
		M = Integer.parseInt(s1.nextToken());
		C = Integer.parseInt(s1.nextToken());
		at = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			at[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(at);
		int lo = 0, hi = 1000000000, mid = 0, ans = 0;
		while (lo != hi) {
			if (hi == lo + 1) {
				if (pos(lo)) ans = lo;
				else ans = hi;
				break;
			}
			mid = (lo + hi) / 2;
			if (pos(mid)) hi = mid;
			else lo = mid + 1;
			if (lo == hi) ans = lo;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}