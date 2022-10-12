import java.io.*;
import java.util.*;

public class movie {
	
	static int N, L, ans;
	static int[][] movies;
	static int[] duration;
	static int[] dp;
	
	public static void process(int bitmask) {
		int a = dp[bitmask];
		if (a == -1) return;
		if (a >= L) {
			int ans = 0;
			for (int i = 0; i < N; i++) {
				if ((bitmask & (1 << i)) != 0) ans++;
			}
			movie.ans = Math.min(movie.ans, ans);
		} else {
			for (int i = 0; i < N; i++) {
				if ((bitmask & (1 << i)) != 0) continue;
				int newmask = bitmask | (1 << i);
				int index = Arrays.binarySearch(movies[i], a);
				if (index < 0) index = -index - 1;
				if (index == 0 && movies[i][0] > a) continue;
				if (index == movies[i].length || movies[i][index] > a) index--;
				dp[newmask] = Math.max(dp[newmask], duration[i] + movies[i][index]);
			}
		}
	}
	
	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("movie.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("movie.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		movies = new int[N][];
		duration = new int[N];
		int len = (int) (Math.pow(2, N));
		dp = new int[len];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			duration[i] = Integer.parseInt(s.nextToken());
			int C = Integer.parseInt(s.nextToken());
			movies[i] = new int[C];
			for (int j = 0; j < C; j++) {
				movies[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		Arrays.fill(dp, -1);
		dp[0] = 0;
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			process(i);
		}
		if (ans == Integer.MAX_VALUE) out.println(-1);
		else out.println(ans);
		in.close();
		out.close();
	}
	
}