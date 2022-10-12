import java.io.*;
import java.util.*;

class l implements Comparable<l> {
	
	int a, b;
	
	public l(int aa, int bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(l o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class fuel {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fuel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), G = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken()), D = Integer.parseInt(st.nextToken());
		l[] stations = new l[N + 2];
		long cost = 0l;
		for (int i = 1; i <= N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			stations[i] = new l(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		stations[0] = new l(0, -10);
		stations[N + 1] = new l(D, 0);
		Arrays.sort(stations);
		int f = B, counter = 0, curr = 0;
		boolean b = true;
		for (int i = N + 1; i > 0; i--) {
			if (stations[i].a - stations[i - 1].a > G) {
				b = false;
				out.println(-1);
				break;
			}
		}
		if (b) {
			while (counter < N + 1) {
				if (counter == 0) {
					counter++;
					int minCost = Integer.MAX_VALUE, minIndex = 0;
					while (counter < N + 2 && stations[counter].a <= f) {
						if (stations[counter].b < minCost) {
							minCost = stations[counter].b;
							minIndex = counter;
						}
						counter++;
					}
					f -= stations[minIndex].a;
					counter = minIndex;
					curr = counter;
				} else {
					int minCost = Integer.MAX_VALUE, minCost2 = Integer.MAX_VALUE, minIndex = 0, minIndex2 = 0;
					while (counter < N + 2 && stations[counter].a <= G + stations[curr].a) {
						if (stations[counter].b < minCost) {
							minCost2 = minCost;
							minIndex2 = minIndex;
							minCost = stations[counter].b;
							minIndex = counter;
						} else if (stations[counter].b < minCost2) {
							minCost2 = stations[counter].b;
							minIndex2 = counter;
						}
						counter++;
					}
					if (minIndex == curr) {
						cost += minCost * (G - f);
						f = G - (stations[minIndex2].a - stations[minIndex].a);
						curr = minIndex2;
						counter = curr;
					} else {
						int dist = stations[minIndex].a - stations[curr].a;
						if (f < dist) {
							cost += stations[curr].b * (dist - f);
							f = 0;
						} else {
							f -= dist;
						}
						curr = minIndex;
						counter = curr;
					}
				}
			}
			out.println(cost);
		}
		in.close();
		out.close();
	}
	
}