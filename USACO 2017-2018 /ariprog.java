/*
ID: awesome25
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class seq {
	
	int start, diff;
	
	public seq(int start2, int diff2) {
		start = start2;
		diff = diff2;
	}
	
}

class ariprog {
	
	public static boolean isIn(int[] bisquares, int num, int start, int end) {
		int a = Arrays.binarySearch(bisquares, start, end, num);
		if (a > 0) return true;
		else return false;
	}
	
	public static void main (String [] args) throws IOException {
		//long start = System.nanoTime();
		BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int len = Integer.parseInt(in.readLine()), bound = Integer.parseInt(in.readLine());
		ArrayList<Integer> bisquares2 = new ArrayList<Integer>();
		boolean[] checks = new boolean[bound * bound + bound * bound + 1];
		for (int i = 0; i <= bound; i++) {
			for (int x = i; x <= bound; x++) {
				if (!checks[i * i + x * x]) {
					bisquares2.add(i * i + x * x);
					checks[i * i + x * x] = true;
				}
			}
		}
		Collections.sort(bisquares2);
		int count = bisquares2.size();
		int[] bisquares = new int[count];
		for (int i = 0; i < count; i++) {
			bisquares[i] = bisquares2.get(i);
		}
		ArrayList<seq> sequences = new ArrayList<seq>();
		int length = checks.length;
		boolean check = true;
		for (int i = 0; i < count; i++) {
			for (int x = i + 1; x < count; x++) {
				check = true;
				int diff = bisquares[x] - bisquares[i], nextNum = bisquares[x] + diff;
				for (int q = 0; q < len - 2; q++) {
					if (nextNum > length - 1) {
						check = false;
						break;
					} else {
						if (!checks[nextNum]) {
							check = false;
							break;
						} else nextNum += diff;
					}
					/*if (!isIn(bisquares, nextNum, x + 1, count)) {
						check = false;
						break;
					} else nextNum += diff;*/
				}
				if (check) sequences.add(new seq(bisquares[i], diff));
			}
		}
		if (sequences.size() == 0) {
			out.println("NONE");
		} else {
			Collections.sort(sequences, 
					(o1, o2) -> o1.diff - o2.diff);
			for (int i = 0; i < sequences.size(); i++) {
				out.println(sequences.get(i).start + " " + sequences.get(i).diff);
			}
		}
		in.close();
		out.close();
		//System.out.println((System.nanoTime() - start) / 1000000);
	}
}