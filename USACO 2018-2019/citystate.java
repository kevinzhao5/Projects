import java.io.*;
import java.util.*;

public class citystate {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		int N = Integer.parseInt(in.readLine());
		HashMap<String, Long> s = new HashMap<String, Long>();
		int count = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			String f = st.nextToken().substring(0, 2), e = st.nextToken(), key = f + e;
			if (f.equals(e)) continue;
			if (!s.containsKey(key)) s.put(key, 0l);
			s.put(key, s.get(key) + 1);
		}
		for (String key:s.keySet()) {
			String k2 = key.substring(2, 4) + key.substring(0, 2);
			if (s.containsKey(k2)) count += s.get(key) * s.get(k2);
		}
		out.println(count / 2);
		in.close();
		out.close();
	}
	
}