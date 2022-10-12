import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class PalindromeReorder {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String str = in.readLine();
		int[] counts = new int[26];
		for (int i = 0; i < str.length(); i++) {
			counts[str.charAt(i) - 65]++;
		}
		int odd = -1;
		boolean broken = false;
		for (int i = 0; i < 26; i++) {
			if (counts[i] % 2 == 1) {
				if (odd != -1) {
					broken = true;
				}
				odd = i;
			}
		}
		if (broken) {
			System.out.println("NO SOLUTION");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 26; i++) {
				char c = (char) (i + 65);
				for (int j = 0; j < counts[i] / 2; j++) {
					sb.append(c);
				}
			}
			if (odd != -1) {
				sb.append((char) (odd + 65));
			}
			for (int i = 25; i >= 0; i--) {
				char c = (char) (i + 65);
				for (int j = 0; j < counts[i] / 2; j++) {
					sb.append(c);
				}
			}
			System.out.println(sb.toString());
		}
		in.close();
		out.close();
	}
	
}
