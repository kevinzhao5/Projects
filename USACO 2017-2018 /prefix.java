/*
ID: awesome25
LANG: JAVA
TASK: prefix
*/
import java.io.*;
import java.util.*;

class prefix {
	
	static StringBuilder K = new StringBuilder(200011);
	static String[] prims;
	static boolean[] visited;
	
	public static void solve() {
		visited[0] = true;
		for (int i = 0; i < K.length(); i++) {
			if (!visited[i]) continue;
			for (int x = 0; x < prims.length; x++) {
				String p = prims[x];
				if (p.length() + i > K.length() || visited[p.length() + i]) continue;
				boolean check = true;
				for (int s = 0; s < p.length(); s++) {
					if (K.charAt(s + i) != p.charAt(s)) {
						check = false;
						break;
					}
				}
				if (check) visited[p.length() + i] = true;
			}
		}
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		String ln = "";
		ArrayList<String> pre = new ArrayList<String>();
		while (!ln.equals(".")) {
			ln = in.readLine();
			StringTokenizer st = new StringTokenizer(ln);
			while (st.hasMoreTokens()) pre.add(st.nextToken());
		}
		while(in.ready()) {
			K.append(in.readLine());
		}
		visited = new boolean[K.length() + 1];
		prims = pre.toArray(new String[1]);
		solve();
		int max = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) max = i;
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}