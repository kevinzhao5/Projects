import java.io.*;
import java.util.*;

public class CreatingStrings {
	
	public static TreeSet<String> rec(ArrayList<Character> chars) {
		if (chars.size() == 1) {
			String s = Character.toString(chars.get(0));
			TreeSet<String> list = new TreeSet<>();
			list.add(s);
			return list;
		}
		TreeSet<String> list = new TreeSet<>();
		for (int i = 0; i < chars.size(); i++) {
			char c = chars.get(i);
			String cStr = Character.toString(c);
			chars.remove(i);
			TreeSet<String> subList = rec(chars);
			chars.add(i, c);
			for (String s : subList) {
				list.add(cStr + s);
			}
		}
		return list;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String str = in.readLine();
		ArrayList<Character> chars = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			chars.add(str.charAt(i));
		}
		Collections.sort(chars);
		TreeSet<String> list = rec(chars);
		System.out.println(list.size());
		for (String s : list) {
			System.out.println(s);
		}
		in.close();
		out.close();
	}
	
}
