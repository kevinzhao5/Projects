/*
ID: awesome25
LANG: JAVA
TASK: nocows
*/
import java.io.*;
import java.util.*;

class nocows {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		long[][] num = new long[201][101];
		for (int i = 1; i <= K; i++) {
			num[1][i] = 1;
			for (int x = 2; x <= N; x++) {
				for (int q = 1; q < x - 1; q++) {
					num[x][i] += num[q][i - 1] * num[x - q - 1][i - 1];
					num[x][i] %= 9901;
				}
			}
		}
		out.println((num[N][K] - num[N][K - 1] + 9901) % 9901);
		in.close();
		out.close();
	}
	
}