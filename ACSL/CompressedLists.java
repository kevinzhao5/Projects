//Team Legion of Learners Junior Division

import java.io.*;
import java.util.*;

class term {
	
	String str;
	int num;
	
	public term(String c2, int num2) {
		str = c2;
		num = num2;
	}
	
}

public class CompressedLists {

	public static int alphaBig(String str1, String str2) {
		int index = 0;
		while (index < str1.length() && index < str2.length()) {
			if (str1.charAt(index) < str2.charAt(index)) return 0;
			else if (str1.charAt(index) > str2.charAt(index)) return 1;
		}
		return 0;
	}
	
	public static String alpha(String str) {
		String result = "";
		for (int i = 0; i < 26; i++) {
			for (int x = 0; x < str.length(); x++) {
				if ((int) (str.charAt(x)) - 65 == i) result += (char) (i + 65);
			}
		}
		return result;
	}
	
	public static boolean isIn(ArrayList<String> strs, String str) {
		for (int i = 0; i < strs.size(); i++) {
			if (strs.get(i).equals(str)) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/as2-sample.txt"));
		in.useDelimiter("\\s");
		for (int q = 0; q < 10; q++) {
			String str = in.next();
			int freq = in.nextInt();
			if (q != 9) in.nextLine();
			ArrayList<term> terms = new ArrayList<term>();
			int[] first = new int[26];
			for (int i = 0; i < str.length(); i++) {
				first[(int) (str.charAt(i)) - 65]++;
			}
			for (int i = 0; i < 26; i++) {
				if (first[i] > 0) {
					int index = 0;
					while (index < terms.size() && terms.get(index).num < first[i]) index++;
					while (index < terms.size() && alphaBig(terms.get(index).str, (char) (i + 65) + "") == 0 && terms.get(index).num == first[i]) index++;
					terms.add(index, new term((char) (i + 65) + "", first[i]));
				}
			}
			String answer = "";
			ArrayList<String> strs = new ArrayList<String>();
			for (int i = 0; i < terms.size(); i++) {
				if (!isIn(strs, terms.get(i).str) && terms.get(i).num == freq) {
					strs.add(terms.get(i).str);
					answer += terms.get(i).str;
				}
			}
			while (terms.size() > 1) {
				int index = 0;
				term term1 = terms.remove(0), term2 = terms.remove(0), temp = new term(term1.str + term2.str, term1.num + term2.num);
				temp.str = alpha(temp.str);
				while (index < terms.size() && terms.get(index).num < temp.num) index++;
				while (index < terms.size() && alphaBig(terms.get(index).str, temp.str) == 0 && terms.get(index).num == temp.num) index++;
				terms.add(index, temp);
				for (int i = 0; i < terms.size(); i++) {
					if (!isIn(strs, terms.get(i).str) && terms.get(i).num == freq) {
						strs.add(terms.get(i).str);
						answer += terms.get(i).str;
					}
				}
			}
			String space = "  ";
			if (q == 9) space = " ";
			if (answer == "") System.out.println((q + 1) + "." + space + "NONE");
			else System.out.println((q + 1) + "." + space + alpha(answer));
		}
		in.close();
	}

}