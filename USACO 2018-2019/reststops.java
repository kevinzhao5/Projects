import java.io.*;
import java.util.*;

class stop implements Comparable<stop> {
	
	int d, t;
	
	public stop(int a, int b) {
		d = a;
		t = b;
	}

	@Override
	public int compareTo(stop o) {
		return o.t - this.t;
	}
	
}

@SuppressWarnings("unused")
public class reststops {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		int L = Integer.parseInt(s1.nextToken()), N = Integer.parseInt(s1.nextToken()), f = Integer.parseInt(s1.nextToken()), r = Integer.parseInt(s1.nextToken());
		stop[] s = new stop[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			s[i] = new stop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(s);
		long d = 0l, p = 0l;
		for (int i = 0; i < N; i++) {
			if (s[i].d <= d) continue;
			p += ((s[i].d - d) * f - (s[i].d - d) * r) * s[i].t;
			d = s[i].d;
		}
		out.println(p);
		in.close();
		out.close();
	}
	
}