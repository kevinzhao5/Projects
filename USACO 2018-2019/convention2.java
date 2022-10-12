import java.io.*;
import java.util.*;

class c implements Comparable<c> {
	
	int a, b, c;

	public c(int x, int y, int z) {
		a = x;
		b = y;
		c = z;
	}
	
	@Override
	public int compareTo(c o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class convention2 {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("convention2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		int N = Integer.parseInt(in.readLine());
		TreeSet<c> cows = new TreeSet<c>();
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			cows.add(new c(Integer.parseInt(s.nextToken()), i, Integer.parseInt(s.nextToken())));
		}
		TreeSet<c> wait = new TreeSet<c>();
		boolean isEat = false;
		int max = 0, et = 0;
		while (!cows.isEmpty()) {
			//for (c i:cows) System.out.println(i.a + " " + i.b + " " + i.c);
			//System.out.println();
			//for (c i:wait) System.out.println(i.a + " " + i.b + " " + i.c);
			//System.out.println(et);
			//System.out.println();
			c cow = cows.first();
			cows.remove(cow);
			if (cow.b == Integer.MAX_VALUE) isEat = false;
			else wait.add(new c(cow.b, cow.a, cow.c));
			if (!isEat && !wait.isEmpty()) {
				isEat = true;
				c t = wait.first();
				wait.remove(t);
				max = Math.max(max, et - t.b);
				if (et == 0) {
					cows.add(new c(t.b + t.c, Integer.MAX_VALUE, t.c));
					et = t.b + t.c;
				} else {
					cows.add(new c(et + t.c, Integer.MAX_VALUE, t.c));
					et += t.c;
				}
			} else if (!isEat && wait.isEmpty()) et = 0;
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}