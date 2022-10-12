import java.io.*;
import java.util.*;

class w implements Comparable<w> {
	
	char c;
	int a;
	
	public w(int aa, char cc) {
		a = aa;
		c = cc;
	}

	@Override
	public int compareTo(w o) {
		if (this.a == o.a) return this.c - o.c;
		return o.a - this.a;
	}
	
}

public class ACSLLetters {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for (int h = 0; h < 2; h++) {
			String str = in.readLine().toUpperCase(), word = "";
			ArrayList<String> words = new ArrayList<String>();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c >= 'A' && c <= 'Z') word += c;
				else {
					if (word != "") words.add(word);
					word = "";
				}
			}
			
			
			
			TreeMap<Character, Integer> t = new TreeMap<Character, Integer>();
			for (String s:words) {
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (t.isEmpty() || !t.containsKey(c)) t.put(c, 1);
					else t.put(c, t.get(c) + 1);
				}
			}
			char max = 'A';
			for (char c:t.keySet()) {
				if (t.get(c) > t.get(max)) max = c;
			}
			out.println(max + " " + t.get(max));
			
			
			
			t = new TreeMap<Character, Integer>();
			for (String s:words) {
				TreeSet<Character> chars = new TreeSet<Character>();
				for (int i = 0; i < s.length(); i++) {
					chars.add(s.charAt(i));
				}
				for (char c:chars){
					if (t.isEmpty() || !t.containsKey(c)) t.put(c, 1);
					else t.put(c, t.get(c) + 1);
				}
			}
			max = 'A';
			for (char c:t.keySet()) {
				if (t.get(c) > t.get(max)) max = c;
			}
			out.println(max + " " + t.get(max));
			
			
			
			TreeSet<Character> chars = new TreeSet<Character>();
			for (String s:words) {
				for (int i = 0; i < s.length(); i++) {
					chars.add(s.charAt(i));
				}
			}
			String ans = "";
			for (char c:chars.descendingSet()) {
				ans += c;
			}
			out.println(ans);
			
			
			
			chars = new TreeSet<Character>();
			for (String s:words) {
				TreeMap<Character, Integer> chars1 = new TreeMap<Character, Integer>();
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (chars1.isEmpty() || !chars1.containsKey(c)) chars1.put(c, 1);
					else chars1.put(c, t.get(c) + 1);
				}
				for (char c:chars1.keySet()) {
					if (chars1.get(c) <= 1) continue;
					chars.add(c);
				}
			}
			ans = "";
			for (char c:chars) {
				ans += c;
			}
			out.println(ans);
			
			
			
			t = new TreeMap<Character, Integer>();
			for (String s:words) {
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (t.isEmpty() || !t.containsKey(c)) t.put(c, 1);
					else t.put(c, t.get(c) + 1);
				}
			}
			ArrayList<w> arr = new ArrayList<w>();
			for (char c:t.keySet()) {
				if (t.get(c) > 1) arr.add(new w(t.get(c), c));
			}
			Collections.sort(arr);
			ans = "";
			for (w ww:arr) {
				ans += ww.c;
			}
			out.println(ans);
		}
		in.close();
		out.close();
	}

}
