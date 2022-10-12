import java.util.*;
import java.io.*;

class Network_ArticulationPoints {
	
	static list[] adjlist;
	static int[] num;
	static int[] low;
	static int[] parent;
	static boolean[] pt;
	static int rootChildren = 0, counter = 0;
	
	public static void dfs(int u) {
		num[u] = low[u] = counter++;
		for (int i = 0; i < adjlist[u].v.size(); i++) {
			int t = adjlist[u].v.get(i);
			if (num[t] == -1) {
				if (u == 0) rootChildren++;
				parent[t] = u;
				dfs(t);
				if (low[t] >= num[u]) pt[u] = true;
				low[u] = Math.min(low[u], low[t]);
			} else if (t != parent[u]) {
				low[u] = Math.min(low[u], num[t]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (true) {
			rootChildren = 0;
			counter = 0;
			int n = Integer.parseInt(in.readLine());
			if (n == 0) break;
			adjlist = new list[n];
			num = new int[n];
			low = new int[n];
			parent = new int[n];
			pt = new boolean[n];
			Arrays.fill(low, -1);
			Arrays.fill(num, -1);
			Arrays.fill(parent, -1);
			Arrays.fill(pt, false);
			for (int i = 0; i < n; i++) {
				adjlist[i] = new list();
			}
			int counter = 0;
			while (true && counter < n) {
				StringTokenizer s = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(s.nextToken()) - 1;
				if (x == -1) break;
				while (s.hasMoreTokens()) {
					int y = Integer.parseInt(s.nextToken()) - 1;
					adjlist[x].v.add(y);
					adjlist[y].v.add(x);
				}
				counter++;
			}
			if (counter == n) in.readLine();
			dfs(0);
			int count = 0;
			pt[0] = rootChildren > 1;
			for (int i = 0; i < n; i++) {
				if (pt[i]) count++;
			}
			System.out.println(count);
		}
		in.close();
		out.close();
	}

}
