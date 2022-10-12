/*
ID: awesome25
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;

class concom {
	
	static boolean[][] checks;
	static int[][] comp;
	static int n;
	
	public static int add() {
		int num = 0;
		for (int i = 1; i < 101; i++) {
			for (int x = 1; x < 101; x++) {
				if (i == x) continue;
				if (checks[i][x]) continue;
				if (comp[i][x] < 50) continue;
				num = 1;
				for (int q = 1; q < 101; q++) {
					comp[i][q] += comp[x][q];
					if (checks[x][q]) checks[i][q] = true;
				}
				checks[i][x] = true;
			}
		}
		return num;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		n = Integer.parseInt(in.readLine());
		comp = new int[101][101];
		for (int i = 0; i < n; i++) {
			String ln = in.readLine();
			StringTokenizer st = new StringTokenizer(ln);
			comp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		checks = new boolean[101][101];
		while (add() == 1) ;
		for (int i = 1; i < 101; i++) {
			for (int x = 1; x < 101; x++) {
				if (i == x) continue;
				if (comp[i][x] > 50) out.println(i + " " + x);
			}
		}
		in.close();
		out.close();
	}
	
}