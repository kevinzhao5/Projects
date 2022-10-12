import java.util.*;
import java.io.*;

class list {
	
	ArrayList<Integer> v;
	
	public list() {
		v = new ArrayList<Integer>();
	}
	
}

class WordTransformation {
	
	static ArrayList<String> words;
	static list[] adjlist;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int cases = Integer.parseInt(in.readLine());
		for (int e = 0; e < cases; e++) {
			words = new ArrayList<String>();
			while (true) {
				String str = in.readLine();
				if (str.equals("*")) break;
				words.add(str);
			}
			adjlist = new list[words.size()];
			for (int i = 0; i < adjlist.length; i++) {
				adjlist[i] = new list();
			}
			for (int i = 0; i < adjlist.length; i++) {
				for (int j = 0; j < adjlist.length; j++) {
					int differences = 0;
					String s1 = words.get(i), s2 = words.get(j);
					if (s1.length() != s2.length()) continue;
					for (int k = 0; k < s1.length(); k++) {
						if (s1.charAt(k) != s2.charAt(k)) differences++;
					}
					if (differences == 1) {
						adjlist[i].v.add(j);
						adjlist[j].v.add(i);
					}
				}
			}
			while (true && in.ready()) {
				String s = in.readLine();
				if (s.equals("")) break;
				String[] parts = s.split(" ");
				int x = 0, y = 0;
				for (int i = 0; i < adjlist.length; i++) {
					if (words.get(i).equals(parts[0])) x = i;
					if (words.get(i).equals(parts[1])) y = i;
				}
				Queue<Integer> q = new LinkedList<Integer>();
				int[] dist = new int[words.size()];
				Arrays.fill(dist, -1);
				dist[x] = 0;
				q.offer(x);
				while (!q.isEmpty()) {
					int p = q.poll();
					for (int i = 0; i < adjlist[p].v.size(); i++) {
						if (dist[adjlist[p].v.get(i)] == -1) {
							dist[adjlist[p].v.get(i)] = dist[p] + 1;
							q.offer(adjlist[p].v.get(i));
						}
					}
				}
				out.println(parts[0] + " " + parts[1] + " " + dist[y]);
			}
			if (e < cases - 1) out.println();
		}
		in.close();
		out.close();
	}

}
