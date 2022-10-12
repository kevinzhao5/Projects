import java.io.*;
import java.util.*;

public class cbarn {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int n = Integer.parseInt(in.readLine());
		int[] rooms = new int[n];
		for (int i = 0; i < n; i++) rooms[i] = Integer.parseInt(in.readLine());
		int minCost = Integer.MAX_VALUE;
		for (int w = 0; w < n; w++) {
			boolean[] v = new boolean[n];
			Queue<Integer> q = new LinkedList<Integer>();
			int cost = 0, curr = w;
			for (int i = w;; i = (i + 1) % n) {
				if (rooms[i] > 0) {
					int t = rooms[i];
					for (int j = 0; j < t; j++) q.offer(i);
				}
				if (!q.isEmpty()) {
					int t = q.poll();
					if (i < t) cost += (int)Math.pow(i + (n - t), 2);
					else cost += (int)Math.pow(i - t, 2);
					v[i] = true;
				}
				if (w == 0 && i == n - 1) break;
				if (i == w - 1) break;
			}
			while (!q.isEmpty()) {
				if (v[curr]) {
					curr = (curr + 1) % n;
					continue;
				}
				int t = q.poll();
				if (curr < t) cost += (int)Math.pow(curr + (n - t), 2);
				else cost += (int)Math.pow(curr - t, 2);
				v[curr] = true;
				curr = (curr + 1) % n;
			}
			minCost = Math.min(cost, minCost);
		}
		out.println(minCost);
		in.close();
		out.close();
	}
	
}