/*
ID: awesome25
LANG: JAVA
TASK: inflate
*/
import java.io.*;
import java.util.*;

class inflate {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int min = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		int[] points = new int[num];
		int[] minutes = new int[num];
		for (int i = 0; i < num; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			points[i] = Integer.parseInt(st1.nextToken());
			minutes[i] = Integer.parseInt(st1.nextToken());
		}
		int[] max = new int[min + 2];
		for (int i = 0; i < num; i++) {
			for (int x = 1; x <= min; x++) {
				if (x >= minutes[i]) {
					max[x] = Math.max(max[x], max[x - minutes[i]] + points[i]);
				}
			}
		}
		out.println(max[min]);
		in.close();
		out.close();
	}
	
}