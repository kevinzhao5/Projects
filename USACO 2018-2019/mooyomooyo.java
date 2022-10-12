import java.io.*;
import java.util.*;

public class mooyomooyo {
	
	static int[][] bo;
	static boolean[][] v;
	static boolean[][] v2;
	static int N;
	static int[] rr = {1, 0, 0, -1};
	static int[] rc = {0, 1, -1, 0};
	
	public static int fill(int a, int b, int c) {
		if (b >= 10 || b < 0 || a < 0 || a >= N || v[a][b] || bo[a][b] != c) return 0;
		int res = 1;
		v[a][b] = true;
		for (int i = 0; i < 4; i++) {
			res += fill(a + rr[i], b + rc[i], c);
		}
		return res;
	}
	
	public static void erase(int a, int b, int c) {
		if (b >= 10 || b < 0 || a < 0 || a >= N || v2[a][b] || bo[a][b] != c) return;
		v2[a][b] = true;
		for (int i = 0; i < 4; i++) {
			erase(a + rr[i], b + rc[i], c);
		}
		bo[a][b] = 0;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mooyomooyo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s1.nextToken());
		int K = Integer.parseInt(s1.nextToken());
		bo = new int[N][10];
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < 10; j++) {
				bo[i][j] = (int) (str.charAt(j)) - 48;
			}
		}
		boolean filled = true;
		while (filled) {
			filled = false;
			v = new boolean[N][10];
			v2 = new boolean[N][10];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 10; j++) {
					if (bo[i][j] != 0 && !v[i][j]) {
						int t = fill(i, j, bo[i][j]);
						if (t >= K) {
							erase(i, j, bo[i][j]);
							filled = true;
						}
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				int[] na = new int[N];
				int counter = N - 1;
				for (int j = N - 1; j >= 0; j--) {
					if (bo[j][i] != 0) {
						na[counter] = bo[j][i];
						counter--;
					}
				}
				for (int j = 0; j < N; j++) {
					bo[j][i] = na[j];
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				out.print(bo[i][j]);
			}
			if (i < N - 1) out.println();
		}
		in.close();
		out.close();
	}
	
}