import java.io.*;
import java.util.*;

class v implements Comparable<v> {
	
	int a, b;
	
	public v(int aa, int bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(v o) {
		if (this.b == o.b) return o.a - this.a;
		return o.b - this.b;
	}
	
}

public class mountains {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("mountains.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		int N = Integer.parseInt(in.readLine()), n = N;
		v[] mts = new v[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			mts[i] = new v(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(mts);
		boolean[] s = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (s[i]) continue;
			for (int j = N - 1; j > i; j--) {
				if (s[j]) continue;
				double slope = (mts[i].b - mts[j].b) / (mts[i].a - mts[j].a);
				if (slope >= 1 || slope <= -1) {
					s[j] = true;
					n--;
				}
			}
		}
		out.println(n);
		in.close();
		out.close();
	}
	
}