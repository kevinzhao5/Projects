/*
ID: awesome25
LANG: JAVA
TASK: friday
*/
import java.io.*;

class friday {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		int numYears = Integer.parseInt(in.readLine()), dayNum = 0;
		int[] monthDay = {18, 15, 18, 17, 18, 17, 18, 18, 17, 18, 17, 18};
		int[] num13 = new int[7];
		for (int i = 0; i < numYears * 12; i++) {
			if (i % 12 == 2 && (1900 + (i / 12)) % 4 == 0) {
				if ((1900 + (i / 12)) % 100 == 0 && (1900 + (i / 12)) % 400 == 0) {
					dayNum++;
				} else if ((1900 + (i / 12)) % 100 != 0) {
					dayNum++;
				}
			}
			if (i % 12 == 0 && i != 0) {
				dayNum = (dayNum + 6 + monthDay[11]) % 7;
			} else if (i != 0) {
				dayNum = (dayNum + 6 + monthDay[i % 12 - 1]) % 7;
			}
			num13[dayNum]++;
		}
		for (int i = 0; i < 6; i++) {
			out.print(num13[i] + " ");
		}
		out.println(num13[6]);
		in.close();
		out.close();
	}
}