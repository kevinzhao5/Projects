import java.io.*;
import java.util.*;

public class teleport {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("teleport.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		int N = Integer.parseInt(in.readLine());
		int[] a = new int[N];
		int[] b = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
		long lo = -(long)Math.pow(10, 8), hi = (long)Math.pow(10, 8), cost = 0l, pl = 0, ph = 0l;
		while (hi != ph || lo != pl) {
			long c1 = 0l, c2 = 0l, m1 = (3 * lo + hi) / 4, m2 = (lo + 3 * hi) / 4;
			if (hi == lo + 1) {
				m1 = lo;
				m2 = hi;
			}
			for (int i = 0; i < N; i++) {
				c1 += Math.min(Math.abs(a[i] - b[i]), Math.abs(a[i]) + Math.abs(m1 - b[i]));
				c2 += Math.min(Math.abs(a[i] - b[i]), Math.abs(a[i]) + Math.abs(m2 - b[i]));
			}
			pl = lo;
			ph = hi;
			if (c1 > c2) lo = (lo + hi) / 2;
			else if (c2 > c1) hi = (lo + hi) / 2;
			else {
				lo = m1;
				hi = m2;
			}
			cost = Math.min(c1, c2);
		}
		out.println(cost);
		in.close();
		out.close();
	}
	
}