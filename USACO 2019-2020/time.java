import java.io.*;
import java.util.*;

class city {
	
	TreeSet<Integer> t;
	
	public city() {
		t = new TreeSet<Integer>();
	}
	
}

class state {
	
	int a, b, c;
	
	public state(int aa, int bb, int cc) {
		a = aa;
		b = bb;
		c = cc;
	}
	
}

public class time {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("time.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		int[] m = new int[N];
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			m[i] = Integer.parseInt(st1.nextToken());
		}
		city[] cities = new city[N];
		for (int i = 0; i < N; i++) {
			cities[i] = new city();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1;
			cities[a].t.add(b);
		}
		int[][] max = new int[N][1001];
		Queue<state> q = new LinkedList<state>();
		q.offer(new state(0, 0, 0));
		while (!q.isEmpty()) {
			state s = q.poll();
			int a = s.a, b = s.b, c = s.c;
			if (b < max[c][a]) continue;
			if (a >= 1000) break;
			for (int d : cities[c].t) {
				if (max[d][a + 1] < b + m[d]) {
					max[d][a + 1] = b + m[d];
					q.offer(new state(a + 1, b + m[d], d));
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < 1000; i++) {
			ans = Math.max(ans, max[0][i] - C * i * i);
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}