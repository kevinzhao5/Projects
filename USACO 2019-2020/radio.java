import java.io.*;
import java.util.*;

public class radio {
	
	static int N, M;
	static int[] fx;
	static int[] fy;
	static int[] bx;
	static int[] by;
	
	public static int dist(int i, int j) {
		return (fx[i] - bx[j]) * (fx[i] - bx[j]) + (fy[i] - by[j]) * (fy[i] - by[j]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("radio.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fx = new int[N + 1];
		fy = new int[N + 1];
		bx = new int[M + 1];
		by = new int[M + 1];
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		fx[0] = Integer.parseInt(st1.nextToken());
		fy[0] = Integer.parseInt(st1.nextToken());
		StringTokenizer st2 = new StringTokenizer(in.readLine());
		bx[0] = Integer.parseInt(st2.nextToken());
		by[0] = Integer.parseInt(st2.nextToken());
		String s = in.readLine();
		for (int i = 0; i < N; i++) {
			switch(s.charAt(i)){
			case('N'):
				fy[i + 1] = fy[i] + 1;
				fx[i + 1] = fx[i];
				break;
			case('E'):
				fx[i + 1] = fx[i] + 1;
				fy[i + 1] = fy[i];
				break;
			case('S'):
				fy[i + 1] = fy[i] - 1;
				fx[i + 1] = fx[i];
				break;
			case('W'):
				fx[i + 1] = fx[i] - 1;
				fy[i + 1] = fy[i];
				break;
			}
		}
		s = in.readLine();
		for (int i = 0; i < M; i++) {
			switch(s.charAt(i)){
			case('N'):
				by[i + 1] = by[i] + 1;
				bx[i + 1] = bx[i];
				break;
			case('E'):
				bx[i + 1] = bx[i] + 1;
				by[i + 1] = by[i];
				break;
			case('S'):
				by[i + 1] = by[i] - 1;
				bx[i + 1] = bx[i];
				break;
			case('W'):
				bx[i + 1] = bx[i] - 1;
				by[i + 1] = by[i];
				break;
			}
		}
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0][0] = 0;
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				if (i == 0 && j == 0) continue;
				if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
				if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
				if (i > 0 && j > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
				dp[i][j] += dist(i, j);
			}
		}
		out.println(dp[N][M]);
		in.close();
		out.close();
	}
	
}