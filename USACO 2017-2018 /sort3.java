/*
ID: awesome25
LANG: JAVA
TASK: sort3
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class sort3 {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int len = Integer.parseInt(in.readLine()), one = 0, two = 0, three = 0, num = 0;
		int[] seq = new int[len];
		for (int i = 0; i < len; i++) {
			seq[i] = Integer.parseInt(in.readLine());
			switch(seq[i]) {
			case(1): one++; break;
			case(2): two++; break;
			case(3): three++; break;
			}
		}
		int[][] vals = new int[3][3];
		for (int i = 0; i < one; i++) {
			vals[0][seq[i] - 1]++;
		}
		for (int i = one; i < two + one; i++) {
			vals[1][seq[i] - 1]++;
		}
		for (int i = two + one; i < three + two + one; i++) {
			vals[2][seq[i] - 1]++;
		}
		/*System.out.println(one + " " + two + " " + three);
		for (int i = 0; i < 3; i++) {
			for (int x = 0; x < 3; x++) {
				System.out.print(vals[i][x] + " ");
			}
			System.out.println();
 		}*/
		num += vals[0][1] + vals[0][2];
		vals[0][0] += vals[1][0];
		vals[0][0] += vals[2][0];
		vals[1][1] += Math.min(vals[1][0], vals[0][1]);
		if (vals[0][1] < vals[1][0]) vals[1][2] += vals[1][0] - vals[0][1];
		vals[2][2] += Math.min(vals[2][0], vals[0][2]);
		if (vals[0][2] < vals[2][0]) vals[2][1] += vals[2][0] - vals[0][2];
		num += vals[1][2];
		out.println(num);
		in.close();
		out.close();
	}
}