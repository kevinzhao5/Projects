import java.io.*;
import java.util.*;

public class shortcut {
	
	static class two {
		
		int a, b;
		
		public two(int aa, int bb) {
			a = aa;
			b = bb;
		}
		
	}
	
	static class state implements Comparable<state> {
		
		String s;
		int a, b;
		
		public state(String s1, int aa, int bb) {
			s = s1;
			a = aa;
			b = bb;
		}

		@Override
		public int compareTo(state o) {
			if (this.a == o.a) return this.s.compareTo(o.s);
			return this.a - o.a;
		}
		
	}
	
	static class list {
		
		ArrayList<two> a;
		
		public list() {
			a = new ArrayList<two>();
		}
		
	}
	
	static class intList {
		
		ArrayList<Integer> a;
		
		public intList() {
			a = new ArrayList<Integer>();
		}
		
	}
	
	static long[] dist, cows, amt;
	static String[] p;
	static boolean[] v;
	static intList[] pred;
	
	public static void add(int n) {
		if (v[n]) return;
		v[n] = true;
		int size = pred[n].a.size();
		for (int i = 0; i < size; i++) {
			int index = pred[n].a.get(i);
			add(index);
			amt[n] += amt[index];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("shortcut.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
		cows = new long[N];
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(st1.nextToken());
		}
		list[] edges = new list[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new list();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1, c = Integer.parseInt(s.nextToken());
			edges[a].a.add(new two(b, c));
			edges[b].a.add(new two(a, c));
		}
		p = new String[N];
		for (int i = 0; i < N; i++) {
			p[i] = "";
		}
		p[0] = "0";
		dist = new long[N];
		PriorityQueue<state> pq = new PriorityQueue<state>();
		pq.offer(new state("0", 0, 0));
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		while (!pq.isEmpty()) {
			state sta = pq.poll();
			int a = sta.a, b = sta.b;
			if (dist[b] < a) continue;
			int size = edges[b].a.size();
			for (int i = 0; i < size; i++) {
				two t = edges[b].a.get(i);
				int c = t.a, d = t.b;
				int newDist = d + a;
				if (newDist > dist[c]) continue;
				String str = c + " " + p[b];
				if (p[c] == "" || str.compareTo(p[c]) < 0) p[c] = str;
				if (newDist < dist[c]) {
					dist[c] = newDist;
					p[c] = str;
					pq.offer(new state(str, newDist, c));
				}
			}
		}
		pred = new intList[N];
		v = new boolean[N];
		for (int i = 0; i < N; i++) {
			pred[i] = new intList();
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(p[i]);
			int next = 0;
			while (s.hasMoreTokens()) {
				int k = Integer.parseInt(s.nextToken());
				if (v[k]) break;
				v[k] = true;
				if (s.hasMoreTokens()) next = Integer.parseInt(s.nextToken());
				else break;
				pred[next].a.add(k);
			}
		}
		v = new boolean[N];
		amt = new long[N];
		for (int i = 0; i < N; i++) {
			amt[i] = cows[i];
		}
		add(0);
		long max = 0l;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, (long)amt[i] * ((long)dist[i] - (long)T));
		}
		out.println(max);
		/*for (int i = 0; i < N; i++) {
			System.out.print(i + ": ");
			for (int j:pred[i].a) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println(Arrays.toString(amt));
		System.out.println(Arrays.toString(p));*/
		in.close();
		out.close();
	}
	
}