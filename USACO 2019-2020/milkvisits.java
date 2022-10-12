import java.io.*;
import java.util.*;

public class milkvisits {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[] T = new int[N];
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			T[i] = Integer.parseInt(st1.nextToken());
		}
		boolean[][] matrix = new boolean[N][N];
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1;
			matrix[a][b] = true;
			matrix[b][a] = true;
		}
		String ans = "";
		for (int q = 0; q < M; q++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1, c = Integer.parseInt(s.nextToken());
			boolean[] drank = new boolean[N];
			boolean[] v = new boolean[N];
			Queue<Integer> q1 = new LinkedList<Integer>();
			q1.offer(a);
			v[a] = true;
			while (!q1.isEmpty()) {
				int n = q1.poll();
				if (T[n] == c) {
					drank[n] = true;
				}
				for (int i = 0; i < N; i++) {
					if (matrix[n][i] && !v[i]) {
						drank[i] = drank[n];
						q1.offer(i);
						v[i] = true;
					}
				}
			}
			if (drank[b]) {
				ans += "1";
			} else {
				ans += "0";
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}