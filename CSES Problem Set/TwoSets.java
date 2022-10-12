import java.io.*;
import java.util.*;

public class TwoSets {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		ArrayList<Integer> one = new ArrayList<>();
		ArrayList<Integer> two = new ArrayList<>();
		if (n % 4 == 1 || n % 4 == 2) {
			out.println("NO");
		} else {
			System.out.println("YES");
			if (n % 4 == 0) {
				for (int i = 1; i <= n / 4; i++) {
					one.add(i);
					one.add(n - i + 1);
				}
				for (int i = n / 4 + 1; i <= n / 2; i++) {
					two.add(i);
					two.add(n - i + 1);
				}
			} else {
				one.add(1);
				one.add(2);
				two.add(3);
				for (int i = 4; i <= n; i++) {
					if (i % 4 == 1 || i % 4 == 2) one.add(i);
					else two.add(i);
				}
			}
			System.out.println(one.size());
			StringBuilder s1 = new StringBuilder();
			for (int i = 0; i < one.size() - 1; i++) {
				s1.append(one.get(i));
				s1.append(" ");
			}
			s1.append(one.get(one.size() - 1));
			System.out.println(s1.toString());
			System.out.println(two.size());
			StringBuilder s2 = new StringBuilder();
			for (int i = 0; i < two.size() - 1; i++) {
				s2.append(two.get(i));
				s2.append(" ");
			}
			s2.append(two.get(two.size() - 1));
			System.out.println(s2.toString());
		}
		in.close();
		out.close();
	}
	
}
