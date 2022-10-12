import java.io.*;
import java.util.*;

public class scode {
	
	static String str;
	static TreeMap<String, Integer> t = new TreeMap<String, Integer>();
	
	public static int rec(String s) {
		if (t.size() > 0 && t.containsKey(s)) return t.get(s);
		int ans = 1, l = s.length() / 2;
		if (s.length() % 2 == 1) l++;
		for (int i = 1; i < l; i++) {
			if (s.substring(0, i).equals(s.substring(i, 2 * i))) ans += rec(s.substring(i));
			if (s.substring(0, i).equals(s.substring(s.length() - i))) ans += rec(s.substring(i));
			if (s.substring(s.length() - i).equals(s.substring(0, i))) ans += rec(s.substring(0, s.length() - i));
			if (s.substring(s.length() - i).equals(s.substring(s.length() - i - i, s.length() - i))) ans += rec(s.substring(0, s.length() - i));
		}
		ans %= 2014;
		t.put(s, ans);
		return ans;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("scode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
		str = in.readLine();
		out.println(rec(str) - 1);
		in.close();
		out.close();
	}
	
}