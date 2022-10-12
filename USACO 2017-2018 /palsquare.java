/*
ID: awesome25
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class palsquare {
	
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
		BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		int base = Integer.parseInt(in.readLine());
		for (int i = 1; i <= 300; i++) {
			if (isPalindrome(convert(i * i, base))) out.println(convert(i, base) + " " + convert(i * i, base));
		}
		in.close();
		out.close();
	}
}