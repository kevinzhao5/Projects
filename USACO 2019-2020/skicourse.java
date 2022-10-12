import java.io.*;
import java.util.*;

public class skicourse {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/skicourse.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/skicourse.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
		char[][] field = new char[M][N];
		for (int i = 0; i < M; i++) {
			String s = in.readLine();
			for (int j = 0; j < N; j++) {
				field[i][j] = s.charAt(j);
			}
		}
		int ans = 100;
		for (int i = 0; i < M; i++) {
			int count = 1, max = 1;
			for (int j = 1; j < N; j++) {
				if (field[i][j] != field[i][j - 1]) count = 1;
				else count++;
				max = Math.max(max, count);
			}
			ans = Math.min(ans, max);
		}
		for (int i = 0; i < N; i++) {
			int count = 1, max = 1;
			for (int j = 1; j < M; j++) {
				if (field[j][i] != field[j - 1][i]) count = 1;
				else count++;
				max = Math.max(max, count);
			}
			ans = Math.min(ans, max);
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}