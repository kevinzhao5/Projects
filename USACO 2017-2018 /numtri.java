/*
ID: awesome25
LANG: JAVA
TASK: numtri
*/
import java.io.*;
import java.util.*;

class numtri {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		int length = Integer.parseInt(in.readLine());
		int[][] triangle = new int[length][length];
		for (int i = 0; i < length; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int x = 0; x < i + 1; x++) {
				triangle[i][x] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = length - 1; i > 0; i--) {
			for (int x = 0; x < i; x++) {
				triangle[i - 1][x] += Math.max(triangle[i][x], triangle[i][x + 1]);
			}
		}
		out.println(triangle[0][0]);
		in.close();
		out.close();
	}
}