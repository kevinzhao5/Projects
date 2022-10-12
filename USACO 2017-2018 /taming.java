//ID: awesome25

import java.util.*;
import java.io.*;

public class taming {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("taming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		int numDays = in.nextInt(), max = 0, min = 0, counter = -1;
		in.nextLine();
		int[] log = new int[numDays];
		for (int i = 0; i < numDays; i++) {
			log[i] = in.nextInt();
		}
		log[0] = 0;
		boolean brk = false, seq = true;
		for (int i = numDays - 1; i >= 0; i--) {
			if (counter > 0) {
				counter--;
			}
			if (log[i] >= 0) {
				if (counter > 0 && log[i] != counter) {
					seq = false;
					break;
				} else if (log[i] > 0) {
					brk = true;
					counter = log[i];
				} if (log[i] == 0) {
					counter = 0;
				}
			} else if (log[i] == -1) {
				if (!brk) {
					max++;
				}
			}
			if (counter == 0) {
				brk = false;
				counter = -1;
				if (log[i] > 0) {
					seq = false;
					break;
				} else {
					min++;
					max++;
				}
			}
		}
		if (seq) out.println(min + " " + max);
		else out.println("-1");
		in.close();
		out.close();
	}

}