import java.io.*;
import java.util.*;

public class deleg {
	
	static class two implements Comparable<two> {
		
		int a, b;
		
		public two(int a1, int b1) {
			a = a1;
			b = b1;
		}

		@Override
		public int compareTo(two o) {
			if (this.a == o.a) return this.b - o.b;
			return this.a - o.a;
		}
		
	}
	
	static class list {
		
		TreeSet<two> a;
		
		public list() {
			a = new TreeSet<two>();
		}
		
	}
	
	static int N;
	static list[] edges;
	static int[] connections;
	
	public static int test(int K, int index, int last) {
		if (connections[index] == 1) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (two t:edges[index].a) {
			if (t.a == last) continue;
			int curr = test(K, t.a, index);
			if (curr == Integer.MAX_VALUE) return curr;
			if (curr <= t.b) curr = ((K - t.b + curr) % K + K) % K;
			else curr = (curr - t.b) % K;
			if (map.containsKey(curr)) map.put(curr, map.get(curr) + 1);
			else map.put(curr, 1);
		}
		int need = 0;
		TreeSet<Integer> t = new TreeSet<Integer>();
		for (int i : map.keySet()) {
			if (i == 0 || i == K || t.contains(i)) continue;
			int comp = K - i;
			t.add(i);
			t.add(comp);
			if (K % 2 == 0 && i == K / 2) {
				if (map.get(i) % 2 == 0) continue;
				if (map.get(i) % 2 == 1 && need > 0) return Integer.MAX_VALUE;
				else need = i;
			} else {
				if (!map.containsKey(comp)) {
					if (need > 0) return Integer.MAX_VALUE;
					if (map.get(i) > 1) return Integer.MAX_VALUE;
					need = i;
					continue;
				}
				int diff = (int) (Math.abs(map.get(i) - map.get(comp)));
				if (need > 0 && diff > 0 || diff > 1) return Integer.MAX_VALUE;
				if (diff > 0) {
					if (map.get(comp) > map.get(i)) need = comp;
					else need = i;
				}
			}
		}
		return need;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("deleg.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));
		N = Integer.parseInt(in.readLine());
		edges = new list[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new list();
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
			edges[a].a.add(new two(b, 1));
			edges[b].a.add(new two(a, 1));
		}
		connections = new int[N];
		int start = 0, start2 = 0;
		for (int i = 0; i < N; i++) {
			connections[i] = edges[i].a.size();
			if (connections[i] == 1) start = i;
			else if (connections[i] > 2) start2 = i;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		boolean[] processed = new boolean[N];
		while (!q.isEmpty()) {
			int n = q.poll();
			if (processed[n]) continue;
			processed[n] = true;
			if (connections[n] == 2) {
				two t1 = edges[n].a.first();
				two t2 = edges[n].a.last();
				int a = t1.a, b = t1.b, c = t2.a, d = t2.b;
				edges[a].a.remove(new two(n, b));
				edges[c].a.remove(new two(n, d));
				edges[a].a.add(new two(c, b + d));
				edges[c].a.add(new two(a, b + d));
				if (!processed[a]) q.offer(a);
				if (!processed[c]) q.offer(c);
			} else {
				for (two t : edges[n].a) {
					int a = t.a;
					if (!processed[a]) q.offer(a);
				}
			}
		}
		ArrayList<Integer> factors = new ArrayList<Integer>();
		boolean[] pos = new boolean[N];
		for (int i = 1; i <= N - 1; i++) {
			if ((N - 1) % i == 0) factors.add(i);
		}
		for (int i : factors) {
			if (test(i, start2, -1) == 0) pos[i] = true;
		}
		StringBuilder str = new StringBuilder("");
		for (int i = 1; i <= N - 1; i++) {
			if (pos[i]) str.append("1");
			else str.append("0");
		}
		out.println(str);
		in.close();
		out.close();
	}
	
}