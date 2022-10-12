import java.io.*;
import java.util.*;

class co2 implements Comparator<co> {

	@Override
	public int compare(co o1, co o2) {
		if (o1.b == o2.b) return o1.a - o2.a;
		return o1.b - o2.b;
	}
	
}

class co implements Comparable<co> {
	
	int a, b;
	
	public co(int x, int y) {
		a = x;
		b = y;
	}

	@Override
	public int compareTo(co o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class reduce {
	
	static co[] cows;
	static co[] rem = new co[3];
	
	public static int area() {
		int maxx = 0, maxy = 0, minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
		for (int i = 0; i < cows.length; i++) {
			boolean skip = false;
			for (int j = 0; j < 3; j++) {
				if (rem[j].a == cows[i].a && rem[j].b == cows[i].b) skip = true;
			}
			if (skip) continue;
			maxx = Math.max(maxx, cows[i].a);
			maxy = Math.max(maxy, cows[i].b);
			minx = Math.min(minx, cows[i].a);
			miny = Math.min(miny, cows[i].b);
		}
		return ((maxx - minx) * (maxy - miny));
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		int N = Integer.parseInt(in.readLine());
		cows = new co[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			cows[i] = new co(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(cows);
		ArrayList<co> posRem = new ArrayList<co>();
		for (int i = 0; i < 3; i++) {
			if (!posRem.contains(cows[i])) posRem.add(cows[i]);
			if (!posRem.contains(cows[N - i - 1])) posRem.add(cows[N - i - 1]);
		}
		Arrays.sort(cows, new co2());
		for (int i = 0; i < 3; i++) {
			if (!posRem.contains(cows[i])) posRem.add(cows[i]);
			if (!posRem.contains(cows[N - i - 1])) posRem.add(cows[N - i - 1]);
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < posRem.size() - 2; i++) {
			for (int j = i + 1; j < posRem.size() - 1; j++) {
				for (int k = j + 1; k < posRem.size(); k++) {
					rem[0] = posRem.get(i);
					rem[1] = posRem.get(j);
					rem[2] = posRem.get(k);
					ans = Math.min(ans, area());
				}
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}