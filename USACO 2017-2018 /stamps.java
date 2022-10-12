/*
ID: awesome25
LANG: JAVA
TASK: stamps
*/
import java.io.*;
import java.util.*;

class stamps {
	
	static int[] stamps;
	static int max, n, high = 0, end;
	static int[] best;
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		max = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		stamps = new int[n];
		int counter = 0;
		while (in.ready()) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			while (st1.hasMoreTokens()) {
				stamps[counter] = Integer.parseInt(st1.nextToken());
				if (stamps[counter] > high) high = stamps[counter];
				counter++;
			}
		}
		high = high * max;
		best = new int[high + 1];
		Arrays.fill(best, max + 1);
		for (int i = 0; i < n; i++) {
			best[stamps[i]] = 1;
		}
		end = high;
		for (int i = 1; i <= high; i++) {
			for (int x = 0; x < n; x++) {
				if (stamps[x] > i) continue;
				best[i] = Math.min(best[i], best[i - stamps[x]] + 1);
			}
			if (best[i] > max) {
				end = i - 1;
				break;
			}
		}
		out.println(end);
		in.close();
		out.close();
	}
	
}