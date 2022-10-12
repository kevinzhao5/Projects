import java.io.*;
import java.util.*;

class fence implements Comparable<fence> {
	
	int a, b;
	
	public fence(int c, int d) {
		a = c;
		b = d;
	}

	@Override
	public int compareTo(fence o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

class w {
	
	TreeSet<fence> t = new TreeSet<fence>();
	
}

public class gates {
	
	static w[][] fences;
	static boolean[][] v;
	static int[] rr = {0, 0, 1, -1};
	static int[] rc = {1, -1, 0, 0};
	static int N, len;
	
	public static void floodfill(int a, int b) {
		v[a][b] = true;
		Queue<fence> q = new LinkedList<fence>();
		q.offer(new fence(a, b));
		while (!q.isEmpty()) {
			fence f = q.poll();
			a = f.a;
			b = f.b;
			for (int i = 0; i < 4; i++) {
				if (a + rr[i] >= 0 && b + rc[i] >= 0 && a + rr[i] < len && b + rc[i] < len && !v[a + rr[i]][b + rc[i]] && !fences[a][b].t.contains(new fence(a + rr[i], b + rc[i]))) {
					q.offer(new fence(a + rr[i], b + rc[i]));
					v[a + rr[i]][b + rc[i]] = true;
				}
			}
		}

	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("gates.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		N = Integer.parseInt(in.readLine());
		String str = in.readLine();
		char[] chars = str.toCharArray();
		len = 2 * N + 1;
		fences = new w[len][len];
		v = new boolean[len][len];
		int a = N, b = N;
		for (int i = 0; i < fences.length; i++) {
			for (int j = 0; j < fences.length; j++) {
				fences[i][j] = new w();
			}
		}
		for (char c:chars) {
			if (c == 'N') {
				fences[a][b].t.add(new fence(a, b + 1));
				fences[a][b + 1].t.add(new fence(a, b));
				a--;
			} else if (c == 'E') {
				fences[a][b + 1].t.add(new fence(a + 1, b + 1));
				fences[a + 1][b + 1].t.add(new fence(a, b + 1));
				b++;
			} else if (c == 'S') {
				fences[a + 1][b].t.add(new fence(a + 1, b + 1));
				fences[a + 1][b + 1].t.add(new fence(a + 1, b));
				a++;
			} else if (c == 'W') {
				fences[a][b].t.add(new fence(a + 1, b));
				fences[a + 1][b].t.add(new fence(a, b));
				b--;
			}
		}
		int ans = -1;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (!v[i][j]) {
					floodfill(i, j);
					ans++;
				}
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}