import java.io.*;
import java.util.*;

public class cbarn {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int N = Integer.parseInt(in.readLine());
		int[] c = new int[N];
		for (int i = 0; i < N; i++) {
			c[i] = Integer.parseInt(in.readLine());
		}
		Queue<Integer> q = new LinkedList<Integer>();
		long energy = 0;
		int last = 0, sum = 0;
		for (int i = 0; i < 2 * N; i++) {
			int a = i % N;
			sum += c[a] - 1;
			if (sum < 0) {
				energy = 0;
				last = i + 1;
				sum = 0;
				q.clear();
				continue;
			}
			if (c[a] > 0) {
				for (int j = 0; j < c[a]; j++) {
					q.offer(i);
				}
			}
			int index = q.poll();
			energy += (i - index) * (i - index);
			if (i - last == N - 1) break;
		}
		out.println(energy);
		in.close();
		out.close();
	}
	
}