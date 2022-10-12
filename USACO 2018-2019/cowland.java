import java.io.*;
import java.util.*;

class mq {
	
	TreeSet<Integer> t;
	
	public mq() {
		t = new TreeSet<Integer>();
	}
	
}

class mw {
	
	int a, b;
	
	public mw(int aa, int bb) {
		a = aa;
		b = bb;
	}
	
}

public class cowland {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowland.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
		int[] e = new int[N];
		StringTokenizer s = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			e[i] = Integer.parseInt(s.nextToken());
		}
		mq[] edges = new mq[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new mq();
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st1.nextToken()) - 1, b = Integer.parseInt(st1.nextToken()) - 1;
			edges[a].t.add(b);
			edges[b].t.add(a);
		}
		for (int w = 0; w < Q; w++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st1.nextToken()), b = Integer.parseInt(st1.nextToken()) - 1, c = Integer.parseInt(st1.nextToken());
			if (a == 1) {
				e[b] = c;
			} else {
				c--;
				int ans = e[b];
				Queue<mw> q = new LinkedList<mw>();
				q.offer(new mw(b, ans));
				boolean[] vis = new boolean[N];
				while (!q.isEmpty()) {
					mw v = q.poll();
					int first = v.a, second = v.b;
					if (vis[first]) continue;
					vis[first] = true;
					boolean bb = false;
					for (int x:edges[first].t) {
						if (x == c) {
							ans = second ^ e[c];
							bb = true;
							break;
						}
						q.offer(new mw(x, second ^ e[x]));
					}
					if (bb) break;
				}
				out.println(ans);
			}
		}
		in.close();
		out.close();
	}
	
}