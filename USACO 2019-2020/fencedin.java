import java.io.*;
import java.util.*;

class n implements Comparable<n> {
	
	int a, b, c;
	
	public n(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
	}

	@Override
	public int compareTo(n o) {
		return this.a - o.a;
	}
	
}

public class fencedin {
	
	static int A, B, N, M;
	static int[] v;
	static int[] h;
	static int[] rr = {0, 0, -1, 1};
	static int[] rc = {1, -1, 0, 0};
	
	public static int dist(int a, int b, int c, int d) {
		if (a == c) {
			return h[a + 1] - h[a];
		}
		return v[b + 1] - v[b];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = new int[N + 2];
		h = new int[M + 2];
		for (int i = 1; i <= N; i++) {
			v[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 1; i <= M; i++) {
			h[i] = Integer.parseInt(in.readLine());
		}
		v[0] = 0;
		h[0] = 0;
		v[N + 1] = A;
		h[M + 1] = B;
		Arrays.sort(v);
		Arrays.sort(h);
		boolean[][] vi = new boolean[M + 1][N + 1];
		PriorityQueue<n> pq = new PriorityQueue<n>();
		long len = 0l;
		pq.offer(new n(0, 0, 0));
		while (!pq.isEmpty()) {
			n nn = pq.poll();
			if (vi[nn.b][nn.c]) continue;
			int a = nn.a, b = nn.b, c = nn.c;
			vi[b][c] = true;
			len += a;
			for (int i = 0; i < 4; i++) {
				int nb = b + rr[i], nc = c + rc[i];
				if (nb < 0 || nc < 0 || nb > M || nc > N || vi[nb][nc]) continue;
				pq.offer(new n(dist(b, c, nb, nc), nb, nc));
			}
		}
		out.println(len);
		in.close();
		out.close();
	}
	
}