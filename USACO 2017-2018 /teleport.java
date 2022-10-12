//ID: awesome25

import java.io.*;
import java.util.*;

public class teleport {
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("teleport.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		int start = in.nextInt(), end = in.nextInt(), x = in.nextInt(), y = in.nextInt();
		if (x > y) {
			int temp = x;
			x = y;
			y = temp;
		}
		if (start > end) {
			int temp = start;
			start = end;
			end = temp;
		}
		if (Math.abs(start - x) + Math.abs(end - y) < end - start) {
			out.println(Math.abs(start - x) + Math.abs(end - y));
		} else {
			out.println(end - start);
		}
		in.close();
		out.close();
	}
}