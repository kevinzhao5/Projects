import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class paintbarn {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/paintbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/paintbarn.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[][] layers = new int[201][201];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(s.nextToken()), y1 = Integer.parseInt(s.nextToken()), x2 = Integer.parseInt(s.nextToken()), y2 = Integer.parseInt(s.nextToken());
			for (int j = x1; j <= x2; j++) {
				for (int k = y1; k <= y2; k++) {
					layers[j][k]++;
				}
			}
		}
		int bfrArea = 0;
		int[][] vals = new int[201][201];
		for (int i = 0; i < 201; i++) {
			for (int j = 0; j < 201; j++) {
				if (layers[i][j] == K) {
					vals[i][j] = -1;
					bfrArea++;
				} else if (layers[i][j] == K - 1) {
					vals[i][j] = 1;
				}
			}
		}
		int[][] sums = new int[202][202];
		for (int i = 1; i <= 201; i++) {
			for (int j = 1; j <= 201; j++) {
				sums[i][j] = vals[i - 1][j - 1];
				sums[i][j] += sums[i - 1][j] + sums[i][j - 1];
				sums[i][j] -= sums[i - 1][j - 1];
			}
		}
		int max = bfrArea;
		for (int i = 0; i < 201; i++) {
			for (int j = 0; j < 201; j++) {
				int area = sums[i + 1][j + 1] - sums[i][j + 1] - sums[i + 1][j];
			}
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}