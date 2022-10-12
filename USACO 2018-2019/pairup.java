import java.io.*;
import java.util.*;

public class pairup {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		int N = Integer.parseInt(in.readLine());
		TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>(); 
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			t.put(b, a);
		}
		int max = 0;
		while (!t.isEmpty()) {
			if (t.size() == 1) {
				max = Math.max(max, t.firstKey() + t.firstKey());
				break;
			}
			int l = t.firstKey(), h = t.lastKey(), lo = Math.min(t.get(l), t.get(h));
			max = Math.max(max, l + h);
			t.put(l, t.get(l) - lo);
			if (t.get(l) == 0) t.remove(l);
			t.put(h, t.get(h) - lo);
			if (t.get(h) == 0) t.remove(h);
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}