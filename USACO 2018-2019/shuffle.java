import java.util.*;
import java.io.*;

public class shuffle {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		int n = Integer.parseInt(in.readLine());
		int[] pos = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			pos[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		boolean[] l = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (l[i]) continue;
			TreeMap<Integer, Integer> v = new TreeMap<Integer, Integer>();
			v.put(i, 0);
			int curr = pos[i], counter = 0, bound = 0;
			while (counter <= n) {
				counter++;
				if (l[curr]) {
					bound = counter;
					break;
				}
				if (v.containsKey(curr)) {
					bound = v.get(curr);
					break;
				}
				v.put(curr, counter);
				curr = pos[curr];
			}
			Set<Integer> set = v.keySet();
			for (Integer j : set) {
				int b = v.get(j);
				if (b >= bound) {
					count++;
				}
				l[j] = true;
			}
		}
		out.println(count);
		in.close();
		out.close();
	}

}
