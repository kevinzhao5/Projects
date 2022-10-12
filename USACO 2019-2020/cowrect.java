import java.io.*;
import java.util.*;

class g implements Comparable<g> {
	
	int a, b;
	boolean H;
	
	public g(int aa, int bb, boolean HH) {
		a = aa;
		b = bb;
		H = HH;
	}

	@Override
	public int compareTo(g o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class cowrect {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowrect.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrect.out")));
		int N = Integer.parseInt(in.readLine());
		TreeSet<Integer> t = new TreeSet<Integer>();
		g[] cows = new g[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			boolean b = c == 'H';
			if (b) t.add(Y);
			cows[i] = new g(X, Y, b);
		}
		int[] y = new int[t.size()];
		int counter = 0;
		for (int i : t) {
			y[counter] = i;
			counter++;
		}
		Arrays.sort(cows);
		int ans = 0, max = 0;
		for (int i = 0; i < counter; i++) {
			for (int j = i + 1; j < counter; j++) {
				int start = -1, count = 0, maxY = 0, minY = 1000;
				for (int k = 0; k < N; k++) {
					int H = 0, l = k;
					boolean G = false;
					while (l < N && cows[l].a == cows[k].a) {
						if (cows[l].b <= y[j] && cows[l].b >= y[i]) {
							if (cows[l].H) {
								H++;
								maxY = Math.max(maxY, cows[l].b);
								minY = Math.min(minY, cows[l].b);
							} else {
								G = true;
							}
						}
						l++;
					}
					if (G) {
						start = -1;
						count = 0;
						maxY = 0;
						minY = 1000;
					} else if (H > 0) {
						if (start == -1) {
							start = cows[k].a;
						}
						count += H;
						int area = (maxY - minY) * (cows[k].a - start);
						if (count > max || count == max && area < ans) {
							max = count;
							ans = area;
						}
					}
					k = l - 1;
				}
			}
		}
		out.println(max);
		out.println(ans);
		in.close();
		out.close();
	}
	
}