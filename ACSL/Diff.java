import java.util.*;
import java.io.*;

public class Diff {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/diff"));
		for (int e = 0; e < 5; e++) {
			String s1 = in.readLine(), s2 = in.readLine();
			String[] wt1 = s1.split(" ");
			String[] wt2 = s2.split(" ");
			ArrayList<String> w1 = new ArrayList<String>();
			for (int i = 0; i < wt1.length; i++) w1.add(wt1[i]);
			ArrayList<String> w2 = new ArrayList<String>();
			for (int i = 0; i < wt2.length; i++) w2.add(wt2[i]);
			String c1 = "", c2 = "";
			ArrayList<String> w12 = new ArrayList<String>(w1);
			ArrayList<String> w22 = new ArrayList<String>(w2);
			for (int i = 0; i < w1.size(); i++) {
				boolean b = false;
				String str = w1.get(i);
				for (int j = 0; j < w2.size(); j++) {
					String str2 = w2.get(j);
					for (int k = 0; k < str2.length() - str.length() + 1; k++) {
						if (str2.substring(k, str.length() + k).equals(str)) {
							w2.set(j, str2.substring(k));
							c1 += str;
							b = true;
							break;
						}
					}
					if (b) break;
				}
			}
			for (int i = 0; i < w22.size(); i++) {
				boolean b = false;
				String str = w22.get(i);
				for (int j = 0; j < w12.size(); j++) {
					String str2 = w12.get(j);
					for (int k = 0; k < str2.length() - str.length() + 1; k++) {
						if (str2.substring(k, str.length() + k).equals(str)) {
							w12.set(j, str2.substring(k));
							c2 += str;
							b = true;
							break;
						}
					}
					if (b) break;
				}
			}
			String res = "";
			for (int i = 0; i < c1.length(); i++) {
				char c = c1.charAt(i);
				for (int j = 0; j < c2.length(); j++) {
					if (c == c2.charAt(j)) {
						res += c;
						c2 = c2.substring(j + 1);
						break;
					}
				}
			}
			if (res.equals("")) System.out.println("NONE");
			else System.out.println(res);
		}
		in.close();
	}

}
