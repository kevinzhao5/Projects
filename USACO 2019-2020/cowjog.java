import java.io.*;
import java.util.*;

public class cowjog {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
		long[] finalPos = new long[N];
		for (int i = 0 ; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			finalPos[i] = Integer.parseInt(s.nextToken()) + T * Integer.parseInt(s.nextToken());
		}
		TreeMap<Long, Integer> t = new TreeMap<Long, Integer>();
		int ans = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (t.isEmpty() || t.lastKey() <= finalPos[i]) {
				if (!t.isEmpty() && t.containsKey(finalPos[i])) t.put(finalPos[i], t.get(finalPos[i]) + 1);
				else t.put(finalPos[i], 1);
				ans++;
			} else {
				long val = t.ceilingKey(finalPos[i] + 1);
				t.put(val, t.get(val) - 1);
				if (t.get(val) == 0) t.remove(val);
				if (!t.isEmpty() && t.containsKey(finalPos[i])) t.put(finalPos[i], t.get(finalPos[i]) + 1);
				else t.put(finalPos[i], 1);
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}