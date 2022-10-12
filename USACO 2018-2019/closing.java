import java.io.*;
import java.util.*;

class edges {
	
	TreeSet<Integer> c = new TreeSet<Integer>();
	
}

public class closing {
	
	static edges[] el;
	static boolean[] removed;
	static boolean[] v;
	
	public static int floodFill(int a) {
		if (removed[a] || v[a]) return 0;
		int ans = 1;
		v[a] = true;
		for (int i : el[a].c) ans += floodFill(i);
		return ans;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		el = new edges[N + 1];
		removed = new boolean[N + 1];
		v = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			el[i] = new edges();
			el[i].c = new TreeSet<Integer>();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()), b = Integer.parseInt(s.nextToken());
			el[a].c.add(b);
			el[b].c.add(a);
		}
		if (floodFill(1) != N) out.println("NO");
		else out.println("YES");
		for (int i = 1; i < N; i++) {
			v = new boolean[N + 1];
			int x = Integer.parseInt(in.readLine());
			removed[x] = true;
			for (int j = N; j > 0; j--) {
				if (!removed[j]) {
					if (floodFill(j) != N - i) out.println("NO");
					else out.println("YES");
					break;
				}
			}
		}
		in.close();
		out.close();
	}
	
}