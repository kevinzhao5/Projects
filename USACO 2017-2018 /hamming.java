/*
ID: awesome25
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;

class hamming {
	
	public static int hamDist(int a, int b) {
		int dist = 0;
		String astr = Integer.toBinaryString(a), bstr = Integer.toBinaryString(b);
		while (astr.length() > bstr.length()) bstr = "0" + bstr;
		while (bstr.length() > astr.length()) astr = "0" + astr;
		for (int i = 0; i < astr.length(); i++) {
			if (bstr.charAt(i) != astr.charAt(i)) dist++;
		}
		return dist;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int numCode = Integer.parseInt(st.nextToken()), len = Integer.parseInt(st.nextToken()), dist = Integer.parseInt(st.nextToken());
		ArrayList<Integer> codes = new ArrayList<Integer>();
		codes.add(0);
		for (int i = 1; i < Math.pow(2, len); i++) {
			boolean check = true;
			for (int x = 0; x < codes.size(); x++) {
				if (hamDist(i, codes.get(x)) < dist) {
					check = false;
					break;
				}
			}
			if (check) codes.add(i);
			if (codes.size() >= numCode) break;
		}
		for (int i = 1; i < codes.size(); i++) {
			if (i % 10 != 0) out.print(codes.get(i - 1) + " ");
			else out.println(codes.get(i - 1));
		}
		out.println(codes.get(codes.size() - 1));
		in.close();
		out.close();
	}
}