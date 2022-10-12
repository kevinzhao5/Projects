import java.io.*;
import java.util.*;

class q implements Comparable<q> {
	
	String s;
	int a;
	
	public q(String str, int b) {
		s = str;
		a = b;
	}

	@Override
	public int compareTo(q o) {
		return this.s.compareTo(o.s);
	}
	
}

public class auto {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("auto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int W = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
		q[] words = new q[W];
		for (int i = 0; i < W; i++) {
			words[i] = new q(in.readLine(), i);
		}
		Arrays.sort(words);
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int k = Integer.parseInt(s.nextToken());
			String str = s.nextToken();
			int n = Arrays.binarySearch(words, new q(str, 0));
			if (n < 0) n = -(n + 1);
			n += k - 1;
			if (n < 0 || n >= words.length || str.length() > words[n].s.length() || !words[n].s.substring(0, str.length()).equals(str)) out.println(-1);
			else out.println(words[n].a + 1);
		}
		in.close();
		out.close();
	}
	
}