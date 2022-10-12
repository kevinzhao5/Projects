import java.io.*;
import java.util.*;

public class highcard {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		int N = Integer.parseInt(in.readLine());
		boolean[] used = new boolean[2 * N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			int t = Integer.parseInt(in.readLine());
			used[t] = true;
			q.offer(t);
		}
		TreeSet<Integer> t = new TreeSet<Integer>();
		for (int i = 1; i <= N * 2; i++) {
			if (!used[i]) t.add(i);
		}
		int score = 0;
		for (int i = 0; i < N; i++) {
			int m = q.poll();
			if (m > t.last()) t.remove(t.first());
			else {
				score++;
				t.remove(t.ceiling(m));
			}
		}
		out.println(score);
		in.close();
		out.close();
	}
	
}