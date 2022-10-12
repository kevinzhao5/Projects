import java.io.*;
import java.util.*;

class it implements Comparable<it> {
	
	int a, b;
	
	public it(int c, int d) {
		b = c;
		a = d;
	}

	@Override
	public int compareTo(it o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class recording {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("recording.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("recording.out")));
		int N = Integer.parseInt(in.readLine());
		it[] ivs = new it[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			ivs[i] = new it(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(ivs);
		int t1 = 0, t2 = 0, ans = 0;
		for (int i = 0; i < N; i++) {
			int t = ivs[i].b;
			if (t < t1) continue;
			if (t < t2) {
				t1 = t2;
				t2 = ivs[i].a;
				ans++;
			} else {
				t2 = ivs[i].a;
				ans++;
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}