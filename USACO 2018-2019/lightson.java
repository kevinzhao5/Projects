import java.io.*;
import java.util.*;

class p1 {
	
	int a, b;
	
	public p1(int c, int d) {
		a = c;
		b = d;
	}
	
}

class sw {
	
	ArrayList<p1> a;
	
	public sw() {
		a = new ArrayList<p1>();
	}
	
}

public class lightson {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		StringTokenizer s = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(s.nextToken()), M = Integer.parseInt(s.nextToken());
		sw[][] switches = new sw[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				switches[i][j] = new sw();
				switches[i][j].a = new ArrayList<p1>();
			}
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
			switches[a][b].a.add(new p1(c, d));
		}
		boolean[][] on = new boolean[N + 2][N + 2];
		boolean[][] v = new boolean[N + 2][N + 2];
		boolean[][] reach = new boolean[N + 2][N + 2];
		int[] rr = {-1, 1, 0, 0};
		int[] rc = {0, 0, -1, 1};
		Queue<p1> q = new LinkedList<p1>();
		q.add(new p1(1, 1));
		on[1][1] = true;
		reach[1][1] = true;
		int ans = 0;
		while (!q.isEmpty()) {
			p1 p = q.poll();
			int a = p.a, b = p.b;
			if (v[a][b]) continue;
			v[a][b] = true;
			for (p1 p2:switches[a][b].a) {
				int x = p2.a, y = p2.b;
				on[x][y] = true;
			}
			for (int i = 0; i < 4; i++) {
				if (on[a + rr[i]][b + rc[i]]) {
					reach[a + rr[i]][b + rc[i]] = true;
					if (!v[a + rr[i]][b + rc[i]]) {
						q.offer(new p1(a + rr[i], b + rc[i]));
					}
				}
			}
			for (p1 p2:switches[a][b].a) {
				int x = p2.a, y = p2.b;
				if (!v[x][y]) {
					for (int i = 0; i < 4; i++) {
						if (reach[x + rr[i]][y + rc[i]]) {
							reach[x][y] = true;
							q.offer(new p1(x, y));
							break;
						}
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (on[i][j]) ans++;
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}