import java.io.*;
import java.util.*;

class board {
	
	int a, b, c, d;
	
	public board(int aa, int bb, int cc, int dd) {
		a = aa;
		b = bb;
		c = cc;
		d = dd;
	}
	
}

class point implements Comparable<point> {
	
	int a, b;
	
	public point(int aa, int bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(point o) {
		return this.a - o.a;
	}
	
}

public class boards {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("boards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());
		board[] b = new board[P + 2];
		for (int i = 1; i <= P; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			b[i] = new board(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		b[0] = new board(0, 0, 0, 0);
		b[P + 1] = new board(N, N, N, N);
		int[] dist = new int[P + 2];
		Arrays.fill(dist, 2000000001);
		dist[0] = 0;
		PriorityQueue<point> pq = new PriorityQueue<point>();
		pq.offer(new point(0, 0));
		while (!pq.isEmpty()) {
			point pt = pq.poll();
			int d = pt.a, i = pt.b;
			if (dist[i] < d) continue;
			for (int j = 1; j <= P + 1; j++) {
				if (b[i].c <= b[j].a && b[i].d <= b[j].b) {
					int distance = b[j].a - b[i].c + b[j].b - b[i].d;
					if (dist[i] + distance < dist[j]) {
						dist[j] = dist[i] + distance;
						pq.offer(new point(dist[j], j));
					}
				}
			}
		}
		out.println(dist[P + 1]);
		in.close();
		out.close();
	}
	
}