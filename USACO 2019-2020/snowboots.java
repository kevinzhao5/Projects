import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class snowboots {
	
	static class UFDS {
		
		public int[] p, height, size;
		
		public UFDS(int N) {
			p = new int[N];
			height = new int[N];
			size = new int[N];
			for (int i = 0; i < N; i++) {
				p[i] = i;
				size[i] = 1;
				height[i] = 0;
			}
		}

		public int setSize(int i) {
			return size[findSet(i)];
		}

		public int findSet(int i) { 
			if (p[i] == i) return i;
			return p[i] = findSet(p[i]);
		}

		public boolean isSameSet(int i, int j) {
			return findSet(i) == findSet(j);
		}

		public void unionSet(int i, int j) { 
			if (!isSameSet(i, j)) {
				int a = findSet(i), b = findSet(j);
				if (height[a] > height[b]) {
					p[b] = a;
					size[a] += size[b];
				} else {
					p[a] = b;
					size[b] += size[a];
					if (height[a] == height[b]) {
						height[b]++;
					}
				}
			}
		}

	}
	
	static class e implements Comparable<e> {
		
		int a, b;
		
		public e(int aa, int bb) {
			a = aa;
			b = bb;
		}

		@Override
		public int compareTo(e o) {
			return this.a - o.a;
		}
		
	}
	
	static class r implements Comparable<r> {
		
		int a, b, c;
		
		public r(int aa, int bb, int cc) {
			a = aa;
			b = bb;
			c = cc;
		}

		@Override
		public int compareTo(r o) {
			if (this.a == o.a) return this.b - o.b;
			return this.a - o.a;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
		e[] t = new e[N];
		boolean[] tooDeep = new boolean[N];
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int fi = Integer.parseInt(st1.nextToken());
			t[i] = new e(fi, i);
		}
		Arrays.sort(t);
		r[] boots = new r[B];
		for (int i = 0; i < B; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int si = Integer.parseInt(s.nextToken()), di = Integer.parseInt(s.nextToken());
			boots[i] = new r(si, di, i);
		}
		Arrays.sort(boots);
		boolean[] possible = new boolean[B];
		UFDS u = new UFDS(N);
		int maxLen = 0, pointer = N - 1;
		for (int i = B - 1; i >= 0; i--) {
			r boot = boots[i];
			int si = boot.a, di = boot.b, c = boot.c;
			while (t[pointer].a > si) {
				e ee = t[pointer];
				pointer--;
				int a = ee.a, b = ee.b;
				tooDeep[b] = true;
				if (tooDeep[b - 1]) u.unionSet(b, b - 1);
				if (tooDeep[b + 1]) u.unionSet(b, b + 1);
				maxLen = Math.max(maxLen, u.setSize(b));
			}
			if (di > maxLen) possible[c] = true;
			else possible[c] = false;
		}
		for (int i = 0; i < B; i++) {
			if (possible[i]) out.println(1);
			else out.println(0);
		}
		in.close();
		out.close();
	}
	
}