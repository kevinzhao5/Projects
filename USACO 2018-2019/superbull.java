import java.io.*;
import java.util.*;

class ed implements Comparable<ed> {
	
	int w, v;
	
	public ed(int a, int b) {
		w = a;
		v = b;
	}

	@Override
	public int compareTo(ed o) {
		if (this.w == o.w) return o.v - this.v;
		return o.w - this.w;
	}
	
}

public class superbull {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("superbull.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
		int N = Integer.parseInt(in.readLine());
		int[] ids = new int[N];
		boolean[] elim = new boolean[N];
		for (int i = 0; i < N; i++) ids[i] = Integer.parseInt(in.readLine());
		long score = 0l;
		PriorityQueue<ed> pq = new PriorityQueue<ed>();
		elim[0] = true;
		for (int i = 0; i < N; i++) {
			if (!elim[i]) pq.offer(new ed(ids[0] ^ ids[i], i));
		}
		while (!pq.isEmpty()) {
			ed e = pq.poll();
			if (!elim[e.v]) {
				elim[e.v] = true;
				score += e.w;
				for (int i = 0; i < N; i++) {
					if (!elim[i]) pq.offer(new ed(ids[e.v] ^ ids[i], i));
				}
			}
		}
		out.println(score);
		in.close();
		out.close();
	}
	
}