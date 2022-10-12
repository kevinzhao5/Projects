import java.io.*;
import java.util.*;

class m {
	
	int a, b;
	
	public m(int aa, int bb) {
		a = aa;
		b = bb;
	}
	
}

public class lightsout {
	
	static int N;
	static int[] x;
	static int[] y;
	static int[] dist;
	static char[] angle;
	static int[] shortOn;
	static TreeMap<String, m> t;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lightsout.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));
		N = Integer.parseInt(in.readLine());
		x = new int[N];
		y = new int[N];
		dist = new int[N];
		angle = new char[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
			if (i > 0) dist[i - 1] = Math.abs(x[i] - x[i - 1] + y[i] - y[i - 1]);
		}
		for (int i = 1; i < N - 1; i++) {
			if (x[i - 1] < x[i] && y[i] > y[i + 1] || y[i - 1] > y[i] && x[i] > x[i + 1] || x[i - 1] > x[i] && y[i] < y[i + 1] || y[i - 1] < y[i] && x[i] < x[i + 1]) angle[i] = 'a';
			else angle[i] = 'b';
		}
		dist[N - 1] = Math.abs(x[N - 1] - x[0] + y[N - 1] - y[0]);
		if (x[N - 1] < x[0] && y[0] > y[1] || y[N - 1] > y[0] && x[0] > x[1] || x[N - 1] > x[0] && y[0] < y[1] || y[N - 1] < y[0] && x[0] < x[1]) angle[0] = 'a';
		else angle[0] = 'b';
		if (x[N - 2] < x[N - 1] && y[N - 1] > y[0] || y[N - 2] > y[N - 1] && x[N - 1] > x[0] || x[N - 2] > x[N - 1] && y[N - 1] < y[0] || y[N - 2] < y[N - 1] && x[N - 1] < x[0]) angle[N - 1] = 'a';
		else angle[N - 1] = 'b';
		shortOn = new int[N];
		int distance = 0;
		for (int i = 0; i < N; i++) {
			shortOn[i] = distance;
			distance += dist[i];
		}
		distance = 0;
		for (int i = N - 1; i >= 0; i--) {
			distance += dist[i];
			shortOn[i] = Math.min(shortOn[i], distance);
		}
		t = new TreeMap<String, m>();
		for (int i = 1; i < N; i++) {
			String s = "";
			for (int j = i; j < N; j++) {
				s += angle[j];
				if (!t.containsKey(s)) t.put(s, new m(1, j));
				else {
					t.put(s, new m(t.get(s).a + 1, -1));
				}
				s += dist[j];
			}
		}
		int ans = 0;
		for (int i = 1; i < N; i++) {
			String s = "";
			int d = 0, j = i;
			boolean figuredOut = false;
			for (; j < N; j++) {
				s += angle[j];
				m mm = t.get(s);
				int b = mm.b;
				if (b != -1) {
					figuredOut = true;
					break;
				}
				s += dist[j];
				d += dist[j];
			}
			if (!figuredOut) ans = Math.max(ans, d - shortOn[i]);
			else ans = Math.max(ans, shortOn[j] + d - shortOn[i]);
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}