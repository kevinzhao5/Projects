import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Repetitions {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String s = in.readLine();
		char last = ' ';
		int length = 1, ans = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != last) {
				last = s.charAt(i);
				length = 1;
			} else {
				length++;
				ans = Math.max(ans, length);
			}
		}
		System.out.println(ans);
		in.close();
		out.close();
	}
	
}
