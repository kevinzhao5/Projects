import java.io.*;
import java.util.*;

class g implements Comparable<g> {
	
	int t, i;
	
	public g (int a, int b) {
		t = a;
		i = b;
	}

	@Override
	public int compareTo(g o) {
		return this.t - o.t;
	}
	
}

public class lifeguards {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		int N = Integer.parseInt(in.readLine());
		g[] lg = new g[N * 2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			lg[i * 2] = new g(Integer.parseInt(st.nextToken()), i);
			lg[i * 2 + 1]  = new g(Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(lg);
		TreeSet<Integer> s = new TreeSet<Integer>();
		int pt = 0, t = 0;
		int[] a = new int[N];
		s.add(lg[0].i);
		pt = lg[0].t;
		for (int i = 1; i < lg.length; i++) {
			g c = lg[i];
			if (s.size() == 1) {
				//System.out.println(s.first() + " " + c.t + " " + c.i + " " + pt);
				a[s.first()] += c.t - pt;
			}
			if (s.size() > 0) {
				t += c.t - pt;
			}
			if (s.contains(c.i)) s.remove(c.i);
			else s.add(c.i);
			pt = c.t;
		}
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			max = Math.max(max, t - a[i]);
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}