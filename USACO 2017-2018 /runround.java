/*
ID: awesome25
LANG: JAVA
TASK: runround
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class runround {
	
	public static boolean isRun(String num, int len) {
		int index = 0;
		String tempNum = num + num + num + num + num + num + num + num + num, newNum = "";
		for (int i = 0; i < num.length(); i++) {
			for (int x = i + 1; x < num.length(); x++) {
				if (num.charAt(i) == num.charAt(x) || num.charAt(x) == '0') return false;
			}
			newNum = newNum + tempNum.charAt(index);
			index += (int) (tempNum.charAt(index)) - 48;
		}
		newNum = newNum + tempNum.charAt(index);
		if (newNum.charAt(0) != newNum.charAt(len)) return false;
		for (int i = 0; i < newNum.length() - 1; i++) {
			for (int x = i + 1; x < newNum.length() - 1; x++) {
				if (newNum.charAt(i) == newNum.charAt(x)) return false;
			}
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		int num = Integer.parseInt(in.readLine()) + 1, len = Integer.toString(num).length();
		while (!isRun(Integer.toString(num), len)) {
			num++;
		}
		out.println(num);
		in.close();
		out.close();
	}
}