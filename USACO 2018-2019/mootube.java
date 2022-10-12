import java.io.*;
import java.util.*;

class list {
	
	ArrayList<Integer> wt;
	ArrayList<Integer> pts;
	
	public list() {
		wt = new ArrayList<Integer>();
		pts = new ArrayList<Integer>();
	}
	
}

public class mootube {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(s1.nextToken()), Q = Integer.parseInt(s1.nextToken());
		list[] el = new list[N + 1];
		for (int i = 1; i < N + 1; i++) {
			el[i] = new list();
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()), b = Integer.parseInt(s.nextToken()), c = Integer.parseInt(s.nextToken());
			el[a].wt.add(c);
			el[a].pts.add(b);
			el[b].wt.add(c);
			el[b].pts.add(a);
		}
		for (int w = 0; w < Q; w++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int k = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken()), c = -1;
			int[] min = new int[N + 1];
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(r);
			Arrays.fill(min, -1);
			min[r] = Integer.MAX_VALUE;
			while (!q.isEmpty()) {
				int v = q.poll();
				if (min[v] >= k) c++;
				else continue;
				for (int i = 0; i < el[v].pts.size(); i++) {
					int u = el[v].pts.get(i);
					if (min[u] == -1) {
						min[u] = Math.min(min[v], el[v].wt.get(i));
						q.offer(u);
					}
				}
			}
			out.println(c);
		}
		in.close();
		out.close();
	}
	
}