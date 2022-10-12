/*
ID: awesome25
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

class pprime {
	
	static ArrayList<Integer> pp = new ArrayList<Integer>();
	static int a, b;
	static String astr, bstr;
	
	public static boolean isPrime(int n) {
		if (n == 2) return true;
		if (n % 2 == 0) return false;
		for (int i = 3; i < Math.sqrt(n) + 1; i += 2) {
			if (n % i == 0) return false;
		}
		return true;
	}
	
	public static int power(int ten) {
		int result = 1;
		for (int i = 0; i < ten; i++) {
			result *= 10;
		}
		return result;
	}
	
	public static void find(int depth, int num, int maxDepth, int even) {
		if (depth == 0) {
			//System.out.println(num);
			if (num >= a && num <= b && isPrime(num)) pp.add(num);
		} else if (depth == 1) {
			if (even == 0) {
				for (int i = 0; i <= 9; i++) {
					find(0, num + power(maxDepth / 2) * i, maxDepth, even);
				}	
			} else {
				for (int i = 0; i <= 9; i++) {
					find(0, num + power(maxDepth / 2) * i + power(maxDepth / 2 - 1) * i, maxDepth, even);
				}
			}
		} else if ((even == 0 && depth == maxDepth / 2 + 1) || (even == 1 && depth == maxDepth / 2)) {
			for (int i = 1; i <= 9; i += 2) {
				find(depth - 1, num + i + i * power(maxDepth - 1), maxDepth, even);
			}
		} else {
			for (int i = 0; i <= 9; i++) {
				find(depth - 1, num + i * power(maxDepth - depth) + i * power(depth - 1), maxDepth, even);
			}
		}
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		a = in.nextInt();
		b = in.nextInt();
		astr = Integer.toString(a);
		bstr = Integer.toString(b);
		for (int i = astr.length(); i <= bstr.length(); i++) {
			if (i % 2 == 1) find(i / 2 + 1, 0, i, 0);
			else find(i / 2, 0, i, 1);
		}
		Collections.sort(pp);
		for (int i = 0; i < pp.size(); i++) {
			out.println(pp.get(i));
		}
		in.close();
		out.close();
	}
}