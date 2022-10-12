import java.io.*;
import java.util.*;

class Equidivisions {
	
	static int[][] parts;
	static boolean[][] v;
	static int N;
	static int[] rr = {0, 0, 1, -1};
	static int[] rc = {1, -1, 0, 0};
 	
	public static void floodFill(int a, int b, int c) {
		if (a < 0 || b < 0 || a >= N || b >= N || v[a][b] || parts[a][b] != c) return;
		v[a][b] = true;
		for (int i = 0; i < 4; i++) {
			floodFill(a + rr[i], b + rc[i], c);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		N = -1;
		while (N != 0) {
			N = Integer.parseInt(in.readLine());
			if (N == 0) break;
			parts = new int[N][N];
			boolean[][] used = new boolean[N][N];
			v = new boolean[N][N];
			for (int i = 0; i < N - 1; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
					used[a][b] = true;
					parts[a][b] = i;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!used[i][j]) parts[i][j] = N - 1;
				}
			}
			int num = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!v[i][j]) {
						num++;
						floodFill(i, j, parts[i][j]);
					}
				}
			}
			if (num == N) out.println("good");
			else out.println("wrong");
		}
		in.close();
		out.close();
	}

}
