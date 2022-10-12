import java.io.*;
import java.util.*;

public class piepie {
	
	static class bPie implements Comparable<bPie> {
		
		int a, b, c;
		
		public bPie(int aa, int bb, int cc) {
			a = aa;
			b = bb;
			c = cc;
		}

		@Override
		public int compareTo(bPie o) {
			if (this.b == o.b) {
				if (this.a == o.a) {
					return this.c - o.c;
				}
				return this.a - o.a;
			}
			return this.b - o.b;
		}
		
	}
	
	static class ePie implements Comparable<ePie> {
		
		int a, b;
		
		public ePie(int aa, int bb) {
			a = aa;
			b = bb;
		}

		@Override
		public int compareTo(ePie o) {
			if (this.a == o.a) return this.b - o.b;
			return this.a - o.a;
		}
		
	}
	
	static class state {
		
		int a, b;
		
		public state(int aa, int bb) {
			a = aa;
			b = bb;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("piepie.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), D = Integer.parseInt(st.nextToken());
		bPie[] b = new bPie[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			b[i] = new bPie(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()), i);
		}
		ePie[] e = new ePie[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			e[i] = new ePie(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		Arrays.sort(b);
		Arrays.sort(e);
		int[][] dist = new int[2][N];
		for (int i = 0; i < 2; i++) {
			Arrays.fill(dist[i], 1000000000);
		}
		Queue<state> q = new LinkedList<state>();
		for (int i = 0; i < N; i++) {
			if (b[i].b == 0) {
				dist[0][i] = 1;
				q.offer(new state(0, i));
			}
			if (e[i].a == 0) {
				dist[1][i] = 1;
				q.offer(new state(1, i));
			}
		}
		while (!q.isEmpty()) {
			state s = q.poll();
			int id = s.a, index = s.b;
			if (id == 0) {
				int taste = b[index].a, tasteLower = Math.max(1, taste - D);
				int index1 = Arrays.binarySearch(e, new ePie(taste, 1000000001)), index2 = Arrays.binarySearch(e, new ePie(tasteLower, 0));
				if (index1 < 0) {
					index1 = -index1 - 1;
				}
				if (index2 < 0) {
					index2 = -index2 - 1;
				}
				index1++;
				index2--;
				for (int i = index2; i <= index1; i++) {
					if (i >= N) break;
					if (i < 0) continue;
					if (dist[1][i] != 1000000000) continue;
					if (e[i].a < tasteLower || e[i].a > taste) continue;
					dist[1][i] = dist[0][index] + 1;
					q.offer(new state(1, i));
				}
			} else {
				int taste = e[index].b, tasteLower = Math.max(1, taste - D);
				int index1 = Arrays.binarySearch(b, new bPie(1000000001, taste, 1000000001)), index2 = Arrays.binarySearch(b, new bPie(0, tasteLower, 0));
				if (index1 < 0) {
					index1 = -index1 - 1;
				}
				if (index2 < 0) {
					index2 = -index2 - 1;
				}
				index1++;
				index2--;
				for (int i = index2; i <= index1; i++) {
					if (i >= N) break;
					if (i < 0) continue;
					if (dist[0][i] != 1000000000) continue;
					if (b[i].b < tasteLower || b[i].b > taste) continue;
					dist[0][i] = dist[1][index] + 1;
					q.offer(new state(0, i));
				}
			}
		}
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			ans[b[i].c] = dist[0][i];
		}
		for (int i = 0; i < N; i++) {
			if (ans[i] == 1000000000) out.println(-1);
			else out.println(ans[i]);
		}
		in.close();
		out.close();
	}
	
}