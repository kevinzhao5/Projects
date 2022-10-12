import java.io.*;
import java.util.*;

public class cardgame {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		int N = Integer.parseInt(in.readLine());
		boolean[] used = new boolean[2 * N + 1];
		int[] first = new int[N / 2];
		int[] second = new int[N / 2];
		for (int i = 0; i < N / 2; i++) {
			first[i] = Integer.parseInt(in.readLine());
			used[first[i]] = true;
		}
		Arrays.sort(first);
		for (int i = 0; i < N / 2; i++) {
			second[i] = Integer.parseInt(in.readLine());
			used[second[i]] = true;
		}
		Arrays.sort(second);
		TreeSet<Integer> bessie = new TreeSet<Integer>();
		for (int i = 1; i <= 2 * N; i++) {
			if (!used[i]) bessie.add(i);
		}
		int ans = 0;
		for (int i = N / 2 - 1; i >= 0; i--) {
			if (bessie.last() > first[i]) {
				ans++;
				bessie.remove(bessie.last());
			}
		}
		for (int i = 0; i < N / 2; i++) {
			if (bessie.first() < second[i]) {
				ans++;
				bessie.remove(bessie.first());
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}