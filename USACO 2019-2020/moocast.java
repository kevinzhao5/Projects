import java.io.*;
import java.util.*;

class h implements Comparable<h> {

	int a, b;
	
	public h(int aa, int bb) {
		a = aa;
		b = bb;
	}
	
	@Override
	public int compareTo(h o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class moocast {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int N = Integer.parseInt(in.readLine());
		int[] x = new int[N];
		int[] y = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] v = new boolean[N];
		PriorityQueue<h> pq = new PriorityQueue<h>();
		pq.offer(new h(0, 0));
		int ans = 0;
		while (!pq.isEmpty()) {
			h hh = pq.poll();
			int a = hh.a, b = hh.b;
			if (!v[b]) {
				v[b] = true;
				ans = Math.max(ans, a);
				for (int i = 0; i < N; i++) {
					if (i == b) continue;
					pq.offer(new h((x[i] - x[b]) * (x[i] - x[b]) + (y[i] - y[b]) * (y[i] - y[b]), i));
				}
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}