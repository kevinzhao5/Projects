/*
ID: awesome25
LANG: JAVA
TASK: agrinet
*/
import java.io.*;
import java.util.*;

class agrinet {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		int n = Integer.parseInt(in.readLine());
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			int counter = 0;
			while (counter < n) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				while (st.hasMoreTokens()) {
					dist[i][counter] = Integer.parseInt(st.nextToken());
					counter++;
				}
			}
		}
		int[] distance = new int[n];
		int[] source = new int[n];
		boolean[] intree = new boolean[n];
		intree[0] = true;
		int size = 1, cost = 0;
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(source, 0);
		for (int i = 1; i < n; i++) {
			distance[i] = dist[0][i];
		}
		while (size < n) {
			int min = Integer.MAX_VALUE, index = -1;
			for (int i = 0; i < n; i++) {
				if (!intree[i] && distance[i] < min) {
					index = i;
					min = distance[i];
				}
			}
			size++;
			cost += distance[index];
			intree[index] = true;
			for (int i = 0; i < n; i++) {
				if (!intree[i] && distance[i] > dist[i][index]) {
					distance[i] = dist[i][index];
					source[i] = index;
				}
			}
		}
		out.println(cost);
		in.close();
		out.close();
	}
	
}