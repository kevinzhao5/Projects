import java.io.*;
import java.util.*;

public class skilevel {
	
	static class UFDS {
		
		public int[] p, height, size, starts;
		public int numSets;
		
		public UFDS(int M, int N, boolean[][] start) {
			p = new int[N * M];
			height = new int[N * M];
			size = new int[N * M];
			starts = new int[N * M];
			numSets = N * M;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					p[i * N + j] = i * N + j;
					size[i * N + j] = 1;
					height[i * N + j] = 0;
					if (start[i][j]) starts[i * N + j] = 1;
					else starts[i * N + j] = 0;
				}
			}
		}
		
		public int numStarts(int i) {
			return starts[findSet(i)];
		}

		public int setSize(int i) {
			return size[findSet(i)];
		}

		public int findSet(int i) { 
			if (p[i] == i) {
				return i;
			}
			return p[i] = findSet(p[i]);
		}

		public boolean isSameSet(int i, int j) {
			return findSet(i) == findSet(j);
		}

		public void unionSet(int i, int j) {
			numSets--; 
			int a = findSet(i), b = findSet(j);
			if (height[a] > height[b]) {
				p[b] = a;
				size[a] += size[b];
				starts[a] += starts[b];
			} else {
				p[a] = b;
				size[b] += size[a];
				starts[b] += starts[a];
				if (height[a] == height[b]) {
					height[b]++;
				}
			}
		}

	}
	
	static class e implements Comparable<e> {
		
		long a;
		int b, c;
		
		public e(long aa, int bb, int cc) {
			a = aa;
			b = bb;
			c = cc;
		}

		@Override
		public int compareTo(e o) {
			if (this.a < o.a) return -1;
			if (this.a > o.a) return 1;
			return 0;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("skilevel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skilevel.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
		int[][] elevations = new int[M][N];
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				elevations[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		boolean[][] start = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(s.nextToken()) == 1) start[i][j] = true;
			}
		}
		UFDS u = new UFDS(M, N, start);
		ArrayList<e> edges = new ArrayList<e>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (i < M - 1) {
					edges.add(new e(Math.abs(elevations[i][j] - elevations[i + 1][j]), i * N + j, (i + 1) * N + j));
				}
				if (j < N - 1) {
					edges.add(new e(Math.abs(elevations[i][j] - elevations[i][j + 1]), i * N + j, i * N + j + 1));
				}
			}
		}
		Collections.sort(edges);
		long ans = 0;
		for (int i = 0; i < edges.size(); i++) {
			if (u.numSets == 1) break;
			e edge = edges.get(i);
			long a = edge.a;
			int b = edge.b, c = edge.c;
			if (u.isSameSet(b, c)) continue;
			int sizeb = u.setSize(b), sizec = u.setSize(c);
			if (sizeb < T && sizeb + sizec >= T) ans += a * u.numStarts(b);
			if (sizec < T && sizeb + sizec >= T) ans += a * u.numStarts(c);
			u.unionSet(b, c);
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}