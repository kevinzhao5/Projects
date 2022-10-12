import java.io.*;
import java.util.*;

class gd implements Comparable<gd> {
	
	int g, d;
	
	public gd(int a, int b) {
		g = a;
		d = b;
	}

	@Override
	public int compareTo(gd o) {
		return o.d - this.d;
	}
	
}

public class msched {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
		int N = Integer.parseInt(in.readLine());
		gd[] cows = new gd[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			cows[i] = new gd(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(cows);
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
		int counter = 0, gals = 0;
		for (int i = 10000; i >= 0; i--) {
			if (counter < N && cows[counter].d == i + 1) {
				while (counter < N && cows[counter].d == i + 1) {
					if (!m.isEmpty() && m.containsKey(cows[counter].g)) m.put(cows[counter].g, m.get(cows[counter].g) + 1);
					else m.put(cows[counter].g, 1);
					counter++;
				}
			}
			if (!m.isEmpty()) {
				gals += m.lastKey();
				m.put(m.lastKey(), m.get(m.lastKey()) - 1);
				if (m.get(m.lastKey()) == 0) m.remove(m.lastKey());
			}
		}
		out.println(gals);
		in.close();
		out.close();
	}
	
}