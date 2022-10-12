import java.io.*;
import java.util.*;

public class cowpatibility {
	
	static class five implements Comparable<five> {
		
		int[] a;
		int s;
		
		public five(int[] a1, int size) {
			s = size;
			a = new int[5];
			for (int i = 0; i < 5; i++) {
				a[i] = a1[i];
			}
		}

		@Override
		public int compareTo(five o) {
			int i = 0;
			while (i < 5 && this.a[i] == o.a[i]) i++;
			if (i == 5) return 0;
			return this.a[i] - o.a[i];
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowpatibility.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		int N = Integer.parseInt(in.readLine());
		TreeMap<five, Integer> map = new TreeMap<five, Integer>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int[] curr = new int[5];
			for (int j = 0; j < 5; j++) {
				curr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(curr);
			for (int j = 1; j <= 31; j++) {
				int counter = 0;
				int[] a = new int[5];
				for (int k = 0; k < 5; k++) {
					if ((j & (1 << k)) != 0) {
						a[counter] = curr[k];
						counter++;
					}
				}
				five f = new five(a, counter);
				if (map.containsKey(f)) map.put(f, map.get(f) + 1);
				else map.put(f, 1);
			}
		}
		int[] pie = { 0, 1, -1, 1, -1, 1 };
		long ans = 0l;
		for (five f : map.keySet()) {
			int x = map.get(f);
			ans += pie[f.s] * x * (x - 1) / 2;
		}
		out.println((N * (N - 1)) / 2 - ans);
		in.close();
		out.close();
	}
	
}