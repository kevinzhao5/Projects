import java.io.*;
import java.util.*;

class edges {
	
	TreeSet<Integer> t = new TreeSet<Integer>();
	
	public edges() {
		t = new TreeSet<Integer>();
	}
	
}

class UFDS {
	
	public int[] p, height, size;
	public int numSets;
	
	public UFDS(int N) {
		p = new int[N];
		height = new int[N];
		size = new int[N];
		numSets = N;
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
		if (p[i] == i) {
			return i;
		}
		return p[i] = findSet(p[i]);
	}

	public boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}

	public void unionSet(int i, int j) { 
		if (!isSameSet(i, j)) {
			numSets--; 
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


public class closing {
		
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		edges[] e = new edges[N];
		for (int i = 0; i < N; i++) {
			e[i] = new edges();
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1;
			e[a].t.add(b);
			e[b].t.add(a);
		}
		int[] order = new int[N];
		for (int i = 0; i < N; i++) {
			order[N - i - 1] = Integer.parseInt(in.readLine()) - 1;
		}
		String[] answers = new String[N];
		boolean[] opened = new boolean[N];
		UFDS set = new UFDS(N);
		answers[N - 1] = "YES";
		opened[order[0]] = true;
		for (int i = 1; i < N; i++) {
			int a = order[i];
			for (int b : e[a].t) {
				if (opened[b]) {
					set.unionSet(a, b);
				}
			}
			opened[a] = true;
			if (set.numSets == N - i) {
				answers[N - 1 - i] = "YES";
			} else {
				answers[N - 1 - i] = "NO";
			}
		}
		for (int i = 0; i < N; i++) {
			out.println(answers[i]);
		}
		in.close();
		out.close();
	}
	
}