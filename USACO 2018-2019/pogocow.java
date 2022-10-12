import java.io.*;
import java.util.*;

class zy implements Comparator<zz> {

	@Override
	public int compare(zz o1, zz o2) {
		return o2.a - o1.a;
	}
	
}

class zz implements Comparable<zz> {
	
	int a, b;
	
	public zz(int c, int d) {
		a = c;
		b = d;
	}

	@Override
	public int compareTo(zz o) {
		return this.a - o.a;
	}
	
}

public class pogocow {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pogocow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));
		int N = Integer.parseInt(in.readLine());
		int[][] dp = new int[N][N];
		zz[] target = new zz[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			target[i] = new zz(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(target);
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				for (int k = i; k >= 0; k--) {
					if (target[i].a - target[k].a <= target[j].a - target[i].a) dp[i][j] = Math.max(dp[i][j], dp[k][i]);
					else break;
				}
				dp[i][j] += target[j].b;
				ans = Math.max(ans, dp[i][j]);
			}
		}
		dp = new int[N][N];
		Arrays.sort(target, new zy());
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				for (int k = i; k >= 0; k--) {
					if (target[k].a - target[i].a <= target[i].a - target[j].a) dp[i][j] = Math.max(dp[i][j], dp[k][i]);
					else break;
				}
				dp[i][j] += target[j].b;
				ans = Math.max(ans, dp[i][j]);
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}