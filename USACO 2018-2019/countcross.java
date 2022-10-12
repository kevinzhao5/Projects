import java.io.*;
import java.util.*;

class p implements Comparable<p> {
	
	int a, b;
	
	public p(int x, int y) {
		a = x;
		b = y;
	}

	@Override
	public int compareTo(p o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

class f {
	
	TreeSet<p> r;
	
	public f() {
		r = new TreeSet<p>();
	}
	
}

public class countcross {
	
	static f[][] fields;
	static int N, K, R;
	static boolean[][] hasCow;
	static boolean[][] v;
	static int[] rr = {1, 0, 0, -1};
	static int[] rc = {0, 1, -1, 0};
	
	public static int fill(int a, int b, int pa, int pb) {
		if (a < 0 || b < 0 || a >= N || b >= N || v[a][b] || fields[a][b].r.contains(new p(pa, pb))) return 0;
		int res = 0;
		v[a][b] = true;
		if (hasCow[a][b]) res = 1;
		for (int i = 0; i < 4; i++) {
			res += fill(a + rr[i], b + rc[i], a, b);
		}
		return res;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("countcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		StringTokenizer s = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s.nextToken());
		K = Integer.parseInt(s.nextToken());
		R = Integer.parseInt(s.nextToken());
		fields = new f[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fields[i][j] = new f();
			}
		}
		for (int i = 0; i < R; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken()) - 1, d = Integer.parseInt(st.nextToken()) - 1;
			fields[a][b].r.add(new p(c, d));
			fields[c][d].r.add(new p(a, b));
		}
		hasCow = new boolean[N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			hasCow[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		v = new boolean[N][N];
		ArrayList<Integer> comp = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!v[i][j]) {
					comp.add(fill(i, j, i, j));
				}
			}
		}
		int res = 0;
		for (int i = 0; i < comp.size(); i++) {
			for (int j = i + 1; j < comp.size(); j++) {
				res += comp.get(i) * comp.get(j);
			}
		}
		out.println(res);
		in.close();
		out.close();
	}
	
}