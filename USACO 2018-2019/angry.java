import java.io.*;
import java.util.*;

public class angry {
	
	static TreeSet<Integer> bales;
	static int N, K;
	
	public static boolean pos(int m) {
		int curr = 0;
		for (int i = 0; i < K; i++) {
			if (curr > bales.last()) return true;
			int start = bales.ceiling(curr + 1);
			curr = start + m * 2;
		}
		if (curr < bales.last()) return false;
		return true;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bales = new TreeSet<Integer>();
		for (int i = 0; i < N; i++) bales.add(Integer.parseInt(in.readLine()));
		long lo = 0l, hi = 500000000, mid = 0l, ans = 0l;
		while (lo != hi) {
			if (hi == lo + 1) {
				if (pos((int)lo)) ans = lo;
				else ans = hi;
				break;
			}
			mid = (lo + hi) / 2;
			if (pos((int)mid)) hi = mid;
			else lo = mid + 1;
			if (lo == hi) ans = lo;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}