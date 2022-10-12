/*
ID: awesome25
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

class dualpal {
	
	public static boolean isPalindrome(String str) {
		boolean check = true;
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
				check = false;
			}
		}
		return check;
	}
	
	public static String convert(int num, int base) {
		String newNum = "";
		int maxExp = 0;
		while (power(base, maxExp + 1) < num) maxExp++;
		for (int i = maxExp; i >= 0; i--) {
			int temp = 0;
			while (power(base, maxExp) <= num) {
				num -= power(base, maxExp);
				temp++;
			}
			maxExp--;
			if (temp < 10) newNum += temp;
			else newNum += (char)(temp + 55);
		}
		return newNum;
	}
	
	public static int power(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		int n = in.nextInt(), s = in.nextInt(), numPal = 0;
		while (numPal < n) {
			s++;
			int palCount = 0;
			for (int i = 2; i <= 10; i++) {
				if (isPalindrome(convert(s, i))) palCount++;
				if (palCount > 1) break;
			}
			if (palCount > 1) {
				out.println(s);
				numPal++;
			}
		}
		in.close();
		out.close();
	}
}