import java.io.*;
import java.util.*;

public class hayfeast {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		int[] F = new int[N];
		int[] S = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			F[i] = Integer.parseInt(s.nextToken());
			S[i] = Integer.parseInt(s.nextToken());
			
		}
		long flavor = 0;
		int ans = Integer.MAX_VALUE, counter = 0;
		TreeMap<Integer, Integer> spice = new TreeMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			while (flavor < M && counter < N) {
				flavor += F[counter];
				if (!spice.isEmpty() && spice.containsKey(S[counter])) spice.put(S[counter], spice.get(S[counter]) + 1);
				else spice.put(S[counter], 1);
				counter++;
			}
			if (flavor < M) break;
			ans = Math.min(ans, spice.lastKey());
			flavor -= F[i];
			spice.put(S[i], spice.get(S[i]) - 1);
			if (spice.get(S[i]) == 0) spice.remove(S[i]);
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}