/*
ID: awesome25
LANG: JAVA
TASK: fence
*/
import java.io.*;
import java.util.*;

class fence {
	
	static int numFence, circuitPos = 0;
	static int[][] ints;
	static int[] circuit;
	static boolean[] check = new boolean[1025];
	
	public static void find(int n) {
		for (int i = 1; i <= 1024; i++) {
			if (ints[n][i] > 0) {
				ints[n][i]--;
				ints[i][n]--;
				find(i);
			}
		}
		circuit[circuitPos] = n;
		circuitPos++;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fence.in"));
		numFence = Integer.parseInt(in.readLine());
		ints = new int[1025][1025];
		circuit = new int[numFence + 1];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < numFence; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			ints[a][b]++;
			ints[b][a]++;
			check[a] = true;
			check[b] = true;
			if (a < min) min = a;
			if (b < min) min = b;
		}
		in.close();
		ArrayList<Integer> odd = new ArrayList<Integer>();
		for (int i = 1; i <= numFence; i++) {
			int count = 0;
			for (int x = 1; x <= numFence; x++) {
				if (ints[i][x] > 0) count += ints[i][x];
			}
			if (count != 0 && count % 2 == 1) odd.add(i);
		}
		if (odd.size() == 2) find(Math.min(odd.get(0), odd.get(1)));
		else {
			ArrayList<int[]> ans = new ArrayList<int[]>();
			for (int i = 1; i <= 1024; i++) {
				if (check[i]) {
					circuitPos = 0;
					circuit = new int[numFence + 1];
					find(i);
					if (circuit[numFence] > 0) {
						ans.add(circuit);
					}
				}
			}
			if (ans.size() == 1) circuit = ans.get(0);
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		for (int i = circuit.length - 1; i >= 0; i--) {
			out.println(circuit[i]);
		}
		out.close();
	}
	
}