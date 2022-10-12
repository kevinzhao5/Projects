/*
ID: awesome25
LANG: JAVA
TASK: fact4
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class fact4 {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		int n = Integer.parseInt(in.readLine()), dig = 1;
		for (int i = 2; i <= n; i++) {
			dig *= i;
			String temp = Integer.toString(dig);
			int d = temp.length() - 1;
			while (temp.charAt(d) == '0') d--;
			dig = Integer.parseInt(temp.substring(Math.max(0, d - 4), d + 1));
		}
		out.println(Integer.toString(dig).charAt(Integer.toString(dig).length() - 1));
		in.close();
		out.close();
	}
	
}