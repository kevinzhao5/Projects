import java.io.*;

class x {
	
	int a, b;
	
	public x(int aa, int bb) {
		a = aa;
		b = bb;
	}
	
}

public class perimeter {
	
	static boolean[][] ice;
	static int N;
	static boolean[][] v;
	static int[] rr = {0, 0, 1, -1};
	static int[] rc = {1, -1, 0, 0};
	
	public static x fill(int a, int b) {
		int area = 1, peri = 0;
		if (a + 1 == N) peri++;
		else if (!ice[a + 1][b]) peri++;
		if (a - 1 == -1) peri++;
		else if (!ice[a - 1][b]) peri++;
		if (b + 1 == N) peri++;
		else if (!ice[a][b + 1]) peri++;
		if (b - 1 == -1) peri++;
		else if (!ice[a][b - 1]) peri++;
		v[a][b] = true;
		for (int i = 0; i < 4; i++) {
			int na = a + rr[i], nb = b + rc[i];
			if (na >= 0 && na < N && nb >= 0 && nb < N && !v[na][nb] && ice[na][nb]) {
				x t = fill(na, nb);
				area += t.a;
				peri += t.b;
			}
		}
		return new x(area, peri);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		N = Integer.parseInt(in.readLine());
		ice = new boolean[N][N];
		v = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++) {
				if (str.charAt(j) == '#') ice[i][j] = true;
				else ice[i][j] = false;
			}
		}
		int maxArea = 0, peri = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (ice[i][j] && !v[i][j]) {
					x t = fill(i, j);
					if (t.a > maxArea) {
						maxArea = t.a;
						peri = t.b;
					} else if (t.a == maxArea) {
						peri = Math.min(peri, t.b);
					}
				}
			}
		}
		out.println(maxArea + " " + peri);
		in.close();
		out.close();
	}
	
}