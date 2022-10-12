import java.io.*;
import java.util.*;

public class maxcross {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
		boolean[] stat = new boolean[N];
		for (int i = 0; i < B; i++) {
			stat[Integer.parseInt(in.readLine()) - 1] = true;
		}
		int ct = 0, min = Integer.MAX_VALUE;
		for (int i = 0; i < K - 1; i++) {
			if (stat[i]) ct++;
		}
		for (int i = K - 1; i < N; i++) {
			if (stat[i]) ct++;
			min = Math.min(min, ct);
			if (stat[i - K + 1]) ct--;
		}
		out.println(min);
		in.close();
		out.close();
	}
	
}