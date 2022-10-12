/* Kevin Zhao
 * Legion of Learners
 * Senior Division
 * Contest #2 2019-2020
 */

import java.io.*;

public class ACSLDifferenceFactor {
	
	public static int rec(String s1, String s2) {
		int ans = 0;
		String common = "";
		int index1 = 0, index2 = 0;
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j <= i; j++) {
				String sub = s1.substring(j, i + 1);
				int index = s2.indexOf(sub);
				if (index >= 0) {
					if (i - j + 1 > ans) {
						ans = i - j + 1;
						common = sub;
						index1 = j;
						index2 = index;
					} else if (i - j + 1 == ans && sub.compareTo(common) < 0) {
						common = sub;
						index1 = j;
						index2 = index;
					}
				}
			}
		}
		if (ans > 0) ans += rec(s1.substring(0, index1), s2.substring(0, index2)) + rec(s1.substring(index1 + ans), s2.substring(index2 + ans));
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/ACSLDifferenceFactor"));
		for (int q = 0; q < 5; q++) {
			String s1 = in.readLine(), s2 = in.readLine(), str1 = "", str2 = "";
			for (int i = 0; i < s1.length(); i++) {
				if (Character.isLetter(s1.charAt(i))) str1 += Character.toUpperCase(s1.charAt(i));
			}
			for (int i = 0; i < s2.length(); i++) {
				if (Character.isLetter(s2.charAt(i))) str2 += Character.toUpperCase(s2.charAt(i));
			}
			System.out.println(rec(str1, str2));
		}
		in.close();
	}

}