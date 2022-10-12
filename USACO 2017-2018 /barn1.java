/*
ID: awesome25
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

class barn1 {
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		int numBoards = in.nextInt(), numStalls = in.nextInt(), numCows = in.nextInt();
		int[] stalls = new int[numCows];
		ArrayList<Integer> spaces = new ArrayList<Integer>();
		for (int i = 0; i < numCows; i++) {
			in.nextLine();
			stalls[i] = in.nextInt();
		}
		Arrays.sort(stalls);
		for (int i = 0; i < numCows; i++) {
			if (i > 0) {
				spaces.add(Math.abs(stalls[i] - stalls[i - 1]) - 1);
				//System.out.print(Math.abs(stalls[i] - stalls[i - 1]) + " ");
			}
		}
		Comparator<? super Integer> c = null;
		spaces.sort(c);
		numStalls = stalls[stalls.length - 1] - stalls[0] + 1;
		for (int i = spaces.size() - 1; i >= 0; i--) {
			if (spaces.size() - 1 - i < numBoards  - 1) {
				numStalls -= spaces.get(i);
			}
		}
		out.println(numStalls);
		in.close();
		out.close();
	}
}