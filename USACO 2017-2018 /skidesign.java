/*
ID: awesome25
LANG: JAVA
TASK: skidesign
*/
import java.io.*;

class skidesign {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		int numHills = Integer.parseInt(in.readLine()), minCost = Integer.MAX_VALUE;
		int[] hills = new int[numHills];
		for (int i = 0; i < numHills; i++) {
			hills[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 0; i < 84; i++) {
			int tempCost = 0;
			for (int x = 0; x < numHills; x++) {
				if (hills[x] < i) tempCost += (i - hills[x]) * (i - hills[x]);
				else if (hills[x] > i + 17) tempCost += (hills[x] - (i + 17)) * (hills[x] - (i + 17));
			}
			if (tempCost < minCost) minCost = tempCost;
		}
		out.println(minCost);
		in.close();
		out.close();
	}
}