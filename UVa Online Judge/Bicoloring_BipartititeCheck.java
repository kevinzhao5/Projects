import java.util.*;
import java.io.*;

class Bicoloring_BipartititeCheck {
	
	static list[] adjlist;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (true) {
			int n = Integer.parseInt(in.readLine());
			if (n == 0) break;
			int l = Integer.parseInt(in.readLine());
			adjlist = new list[n];
			for (int i = 0; i < n; i++) {
				adjlist[i] = new list();
			}
			for (int i = 0; i < l; i++) {
				StringTokenizer s = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(s.nextToken()), y = Integer.parseInt(s.nextToken());
				adjlist[x].v.add(y);
				adjlist[y].v.add(x);
			}
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(0);
			boolean b = true;
			int[] color = new int[n];
			color[0] = 0;
			Arrays.fill(color, -1);
			while (!q.isEmpty() && b) {
				int p = q.poll();
				for (int i = 0; i < adjlist[p].v.size(); i++) {
					int t = adjlist[p].v.get(i);
					if (color[t] == -1) {
						color[t] = 1 - color[p];
						q.offer(t);
					} else if (color[t] == color[p]) {
						b = false;
						break;
					}
				}
			}
			if (b) System.out.println("BICOLORABLE.");
			else System.out.println("NOT BICOLORABLE.");
		}
		in.close();
		out.close();
	}

}
