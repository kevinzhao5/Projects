import java.io.*;
import java.util.*;

class checkpt {
	
	int x, y;
	
	public checkpt(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
}

class marathon {
	
	static checkpt[] points;
	
	public static int distance(int i, int s) {
		return Math.abs(points[i].x - points[s].x) + Math.abs(points[i].y - points[s].y);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
		points = new checkpt[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			points[i] = new checkpt(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()));
		}
		int[][] dp = new int[k + 1][n];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], 1 << 30);
		}
		dp[0][0] = 0;
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j < n; j++) {
				for (int l = j + 1; l < n && i + (l - j - 1) <= k; l++) {
					int nextN = l;
					int nextK = i + (l - j - 1);
					dp[nextK][nextN] = Math.min(dp[nextK][nextN], dp[i][j] + distance(j, l));
				}
			}
		}
		out.println(dp[k][n - 1]);
		in.close();
		out.close();
	}
	
}