import java.io.*;
import java.util.*;

class t implements Comparable<t> {
	
	int a, b;
	
	public t(int c, int d) {
		a = c;
		b = d;
	}

	@Override
	public int compareTo(t o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class irrigation {
	
	public static int sqrEu(t c, t d) {
		return (int)Math.pow((c.a - d.a), 2) + (int)Math.pow((c.b - d.b), 2);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("irrigation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		t[] f = new t[N];
		int[][] edge = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			f[i] = new t(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		for (int i = 0; i < N; i++) {
			t temp = f[i];
			for (int j = i + 1; j < N; j++) {
				int x = sqrEu(temp, f[j]);
				if (x < C) continue;
				edge[i][j] = x;
				edge[j][i] = x;
			}
		}
		int cost = 0;
		boolean[] v = new boolean[N];
		PriorityQueue<t> pq = new PriorityQueue<t>();
		for (int i = 0; i < N; i++) {
			if (edge[0][i] > 0) pq.offer(new t(edge[0][i], i));
		}
		v[0] = true;
		while (!pq.isEmpty()) {
			t temp = pq.poll();
			if (!v[temp.b]) {
				int a = temp.a, b = temp.b;
				cost += a;
				v[b] = true;
				for (int i = 0; i < N; i++) {
					if (!v[i] && edge[i][b] > 0) pq.offer(new t(edge[i][b], i));
				}
			}
		}
		boolean b = false;
		for (int i = 0; i < N; i++) {
			if (!v[i]) {
				out.println("-1");
				b = true;
				break;
			}
		}
		if (!b) out.println(cost);
		in.close();
		out.close();
	}
	
}