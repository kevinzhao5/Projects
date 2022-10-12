import java.io.*;
import java.util.*;

public class cowdance {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(s1.nextToken()), T = Integer.parseInt(s1.nextToken());
		int[] d = new int[N];
		for (int i = 0; i < N; i++) {
			d[i] = Integer.parseInt(in.readLine());
		}
		int h = N, l = 1, m = 0, ph = 0, pl = 0;
		while (h != ph || l != pl) {
			ph = h;
			pl = l;
			m = (h + l) / 2;
			TreeMap<Integer, Integer> s = new TreeMap<Integer, Integer>();
			int t = 0;
			for (int i = 0; i < m; i++) {
				if (s.containsKey(d[i])) s.put(d[i], s.get(d[i]) + 1);
				else s.put(d[i], 1);
			}
			for (int i = m; i < N; i++) {
				int q = s.firstKey();
				s.put(q, s.get(q) - 1);
				if (s.get(q) == 0) s.remove(q);
				int ind = d[i] + q;
				if (s.containsKey(ind)) s.put(ind, s.get(ind) + 1);
				else s.put(ind, 1);
			}
			for (int i:s.keySet()) {
				t = Math.max(t, i);
			}
			if (t > T) l = m;
			else h = m;
		}
		out.println(h);
		in.close();
		out.close();
	}
	
}