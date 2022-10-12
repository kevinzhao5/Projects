import java.io.*;
import java.util.*;

public class bcount {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
		int[] id = new int[N];
		int[][] c = new int[N][4];
		for (int i = 0; i < N; i++) {
			id[i] = Integer.parseInt(in.readLine());
			c[i][id[i]]++;
			if (i > 0) for (int j = 1; j <= 3; j++) c[i][j] += c[i - 1][j];
		}
		for (int i = 0; i < Q; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1;
			if (a == 0) out.println(c[b][1] + " " + c[b][2] + " " + c[b][3]);
			else out.println((c[b][1] - c[a - 1][1]) + " " + (c[b][2] - c[a - 1][2]) + " " + (c[b][3] - c[a - 1][3]));
		}
		in.close();
		out.close();
	}
	
}