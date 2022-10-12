import java.io.*;
import java.util.*;

class i {
	
	int a, b;
	
	public i(int aa, int bb) {
		a = aa;
		b = bb;
	}
	
}

public class checklist {
	
	public static int dist(i u1, i u2) {
		return (int) (Math.pow(u1.a - u2.a, 2) + Math.pow(u1.b - u2.b, 2));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("checklist.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int H = Integer.parseInt(st.nextToken()), G = Integer.parseInt(st.nextToken());
		i[] h = new i[H];
		i[] g = new i[G];
		for (int i = 0; i < H; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			h[i] = new i(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		for (int i = 0; i < G; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			g[i] = new i(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		int[][][] dp = new int[H + 1][G + 1][2];
		for (int i = 0; i <= H; i++) {
			for (int j = 0; j <= G; j++) {
				Arrays.fill(dp[i][j], 2000000000);
			}
		}
		dp[0][0][0] = 0;
		dp[1][0][0] = 0;
		for (int i = 0; i <= H; i++) {
			for (int j = 0; j <= G; j++) {
				if (i >= 1 && j >= 1) dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][1] + dist(h[i - 1], g[j - 1]));
				if (i >= 2) dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][j][0] + dist(h[i - 1], h[i - 2]));
				if (j >= 1 && i >= 1) dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j - 1][0] + dist(h[i - 1], g[j - 1]));
				if (j >= 2) dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j - 1][1] + dist(g[j - 1], g[j - 2]));
			}
		}
		out.println(dp[H][G][0]);
		in.close();
		out.close();
	}
	
}