import java.io.*;
import java.util.*;

class tri implements Comparable<tri> {
	
	int a, b, c;
	
	public tri(int x, int y, int z) {
		a = x;
		b = y;
		c = z;
	}

	@Override
	public int compareTo(tri o) {
		if (o.a == this.a) return this.c - o.c;
		else return this.a - o.a;
	}
	
}

public class helpcross {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		int C = Integer.parseInt(s1.nextToken()), N = Integer.parseInt(s1.nextToken());
		tri[] eve = new tri[C + 2 * N];
		int[] tleft = new int[N];
		for (int i = 0; i < C; i++) eve[i] = new tri(Integer.parseInt(in.readLine()), Integer.MAX_VALUE, 1);
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			eve[i + C] = new tri(Integer.parseInt(s.nextToken()), i, 0);
			tleft[i] = Integer.parseInt(s.nextToken());
			eve[i + N + C] = new tri(tleft[i], i, 2);
		}
		Arrays.sort(eve);
		boolean[] left = new boolean[N];
		int n = 0;
		PriorityQueue<tri> pq = new PriorityQueue<tri>();
		for (int i = 0; i < eve.length; i++) {
			tri t = eve[i];
			if (t.c == 0) pq.offer(new tri(tleft[t.b], t.b, 0));
			else if (t.c == 1 && !pq.isEmpty()) {
				tri c = pq.poll();
				left[c.b] = true;
				n++;
			} else if (t.c == 2) {
				if (!left[t.b]) {
					pq.poll();
					left[t.b] = true;
				}
			}
		}
		out.println(n);
		in.close();
		out.close();
	}
	
}