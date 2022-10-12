import java.util.*;
import java.io.*;

class UniqueSnowflakes11572 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(in.readLine());
		for (int e = 0; e < c; e++) {
			TreeMap<Integer, Integer> sizes = new TreeMap<Integer, Integer>();
			sizes.clear();
			int n = Integer.parseInt(in.readLine()), ans = 0, start = 0;
			for (int i = 0; i < n; i++) {
				int t = Integer.parseInt(in.readLine());
				if (sizes.containsKey(t)) {
					int val = sizes.get(t);
					if (val < start) {
						sizes.put(t, i);
						continue;
					}
					if (i - start > ans) ans = i - start;
					start = val + 1;
					sizes.put(t, i);
				} else {
					sizes.put(t, i);
				}
			}
			if (n - start > ans) ans = n - start;
			System.out.println(ans);
		}
		in.close();
	}

}
