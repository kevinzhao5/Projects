import java.io.*;
import java.util.*;

public class ccski {
	
	static int M, N;
	static int[][] f;
	static boolean[][] c;
	static boolean[][] v;
	static int[] rr = {0, 0, 1, -1};
	static int[] rc = {1, -1, 0, 0};
	
	public static int calc(int a, int b, int m) {
		int res = 0;
		v[a][b] = true;
		if (c[a][b]) res++;
		for (int i = 0; i < 4; i++) {
			int na = a + rr[i], nb = b + rc[i];
			if (na < 0 || na >= M || nb < 0 || nb >= N || v[na][nb]) continue;
			if (Math.abs(f[a][b] - f[na][nb]) > m) continue;
			res += calc(na, nb, m);
		}
		return res;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("ccski.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int tc = 0;
		f = new int[M][N];
		c = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				f[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(s.nextToken()) == 1) {
					c[i][j] = true;
					tc++;
				}
			}
		}
		int hi = 1000000000, lo = 0, mid = 0;
		while (hi > lo) {
			v = new boolean[M][N];
			if (lo + 1 == hi) {
				if (calc(0, 0, lo) == tc) mid = lo;
				else mid = hi;
				break;
			} else {
				mid = (lo + hi) / 2;
				if (calc(0, 0, mid) == tc) hi = mid;
				else lo = mid + 1;
				if (lo == hi) mid = lo;
			}
		}
		out.println(mid);
		in.close();
		out.close();
	}
	
}