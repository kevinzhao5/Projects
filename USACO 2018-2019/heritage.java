/*
ID: awesome25
LANG: JAVA
TASK: heritage
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class heritage {
	
	static String in, pre;
	
	public static String post(int sp, int ep, int si, int ei) {
		if (sp > ep || si > ei) return "";
		if (sp == ep) return pre.substring(sp, ep + 1);
		char root = pre.charAt(sp);
		int index = 0;
		for (int i = si; i <= ei; i++) {
			if (in.charAt(i) == root) {
				index = i - si;
				break;
			}
		}
		return post(sp + 1, sp + index, si, si + index - 1) + post(sp + index + 1, ep, si + index + 1, ei) + root;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in1 = new BufferedReader(new FileReader("heritage.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
		in = in1.readLine();
		pre = in1.readLine();
		out.println(post(0, in.length() - 1, 0, pre.length() - 1));
		in1.close();
		out.close();
	}
	
}