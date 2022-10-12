/*
ID: awesome25
LANG: JAVA
TASK: ratios
*/
import java.io.*;
import java.util.*;

class ratios {
	
	static int[][] feeds = new int[3][3];
	static int[] goal = new int[3];
	static int a = 0, b = 0, c = 0, multiple = 0;
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("ratios.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < 3; i++) {
			goal[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 3; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			for (int x = 0; x < 3; x++) {
				feeds[i][x] = Integer.parseInt(st1.nextToken());
			}
		}
		int[] sum = new int[3];
		int min = 301, total = goal[0] + goal[1] + goal[2];
		in.close();
		for (int f1 = 0; f1 < 100; f1++) {
			for (int f2 = 0; f2 < 100; f2++) {
				sum[0] = f1 * feeds[0][0] + f2 * feeds[1][0];
				sum[1] = f1 * feeds[0][1] + f2 * feeds[1][1];
				sum[2] = f1 * feeds[0][2] + f2 * feeds[1][2];
				if (f1 + f2 > min) break;
				for (int f3 = 0; f3 < 100; f3++) {
					if (f1 + f2 + f3 >= min) break;
					int temp = (sum[0] + sum[1] + sum[2]) / total;
					if (temp != 0 && temp * goal[1] == sum[1] && temp * goal[0] == sum[0] && temp * goal[2] == sum[2]) {
						min = f1 + f2 + f3;
						a = f1;
						b = f2;
						c = f3;
						multiple = temp;
					}
					sum[0] += feeds[2][0];
					sum[1] += feeds[2][1];
					sum[2] += feeds[2][2];
				}
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		if (min == 301) out.println("NONE");
		else out.println(a + " " + b + " " + c + " " + multiple);
		out.close();
	}
	
}