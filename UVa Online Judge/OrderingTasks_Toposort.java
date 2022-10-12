import java.util.*;
import java.io.*;

class OrderingTasks_Toposort {
	
	static list[] adjlist;
	static ArrayList<Integer> ans;
	static boolean[] v;
	
	public static void toposort(int u) {
		v[u] = true;
		for (int i = 0; i < adjlist[u].v.size(); i++) {
			if (!v[adjlist[u].v.get(i)]) toposort(adjlist[u].v.get(i));
		}
		ans.add(u);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (true) {
			ans = new ArrayList<Integer>();
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0) break;
			adjlist = new list[n + 1];
			v = new boolean[n + 1];
			for (int i = 0; i < n + 1; i++) {
				adjlist[i] = new list();
			}
			for (int i = 0; i < m; i++) {
				StringTokenizer s = new StringTokenizer(in.readLine());
				adjlist[Integer.parseInt(s.nextToken())].v.add(Integer.parseInt(s.nextToken()));
			}
			for (int i = 1; i <= n; i++) {
				if (!v[i]) toposort(i);
			}
			for (int i = ans.size() - 1; i > 0; i--) {
				System.out.print(ans.get(i) + " ");
			}
			System.out.println(ans.get(0));
		}
		in.close();
		out.close();
	}
	
}
