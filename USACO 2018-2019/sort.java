import java.io.*;
import java.util.*;

public class sort {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int N = Integer.parseInt(in.readLine());
		int[] a = new int[N];
		int[] b = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(in.readLine());
			b[i] = a[i];
		}
		Arrays.sort(b);
		TreeMap<Integer, Integer> or = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> nl = new TreeMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			if (or.size() == 0 || !or.containsKey(a[i])) or.put(a[i], 1);
			else or.put(a[i], or.get(a[i]) + 1);
		}
		int max = 0, diff = 0;
		for (int i = 1; i < N; i++) {
			int num1 = a[i], num2 = b[i];
			if (nl.size() == 0 || !nl.containsKey(num2)) nl.put(num2, 1);
			else nl.put(num2, nl.get(num2) + 1);
			or.put(num1, or.get(num1) - 1);
			if (nl.containsKey(num2) && or.containsKey(num2) && or.get(num2) <= nl.get(num2)) diff++;
			if (or.containsKey(num1) && nl.containsKey(num1) && or.get(num1) <= nl.get(num1)) diff--;
			if (or.get(num1) == 0) or.remove(num1);
			max = Math.max(max, diff);
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}