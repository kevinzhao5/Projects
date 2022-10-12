//ID: awesome25

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class sort {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int len = Integer.parseInt(in.readLine()), moo = 0, top = len - 1;
		int[] A = new int[len];
		for (int i = 0; i < len; i++) {
			A[i] = Integer.parseInt(in.readLine());
		}
		boolean sorted = false;
		int temp;
		while (!sorted) {
			sorted = true;
			moo++;
			for (int i = 0; i < top; i++) {
				if (A[i + 1] < A[i]) {
					temp = A[i + 1];
					A[i + 1] = A[i];
					A[i] = temp;
					sorted = false;
				}
			}
			top--;
		}
		out.println(moo);
		in.close();
		out.close();
	}
}