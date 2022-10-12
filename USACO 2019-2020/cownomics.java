import java.io.*;
import java.util.*;

public class cownomics {
	
	static int N, M;
	static String[] spotty;
	static String[] plain;
	
	public static boolean pos(int a, int b) {
		TreeSet<String> s = new TreeSet<String>();
		for (int i = 0; i < N; i++) {
			s.add(plain[i].substring(a, b));
		}
		for (int i = 0; i < N; i++) {
			if (s.contains(spotty[i].substring(a, b))) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		spotty = new String[N];
		plain = new String[N];
		for (int i = 0; i < N; i++) {
			spotty[i] = in.readLine();
		}
		for (int i = 0; i < N; i++) {
			plain[i] = in.readLine();
		}
		int ans = M;
		for (int i = 0; i < M; i++) {
			int lo = 1, hi = Math.min(ans, M), mid = 0;
			while (lo < hi && (lo + hi) / 2 + i <= M) {
				if (lo == hi - 1) {
					if (pos(i, i + lo)) ans = Math.min(ans, lo);
					else ans = Math.min(ans, hi);
					break;
				}
				mid = (lo + hi) / 2;
				if (pos(i, i + mid)) hi = mid;
				else lo = mid + 1;
			}
			ans = Math.min(ans, hi);
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}