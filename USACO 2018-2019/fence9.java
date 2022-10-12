/*
ID: awesome25
LANG: JAVA
TASK: fence9
*/
import java.io.*;
import java.util.*;

class fence9 {
	
	public static ArrayList<Integer> factorize(int x) {
		ArrayList<Integer> f = new ArrayList<Integer>();
		f.add(1);
		int temp = (int)Math.ceil(Math.sqrt(x));
		for (int i = 2; i <= temp; i++) {
			if (x % i == 0) {
				f.add(i);
				x /= i;
				i--;
			}
		}
		return f;
	}
	
	public static int multiply(ArrayList<Integer> f) {
		int m = 1;
		for (int i = 0; i < f.size(); i++) {
			m *= f.get(i);
		}
		return m;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
		int part1 = 0, part2 = 0;
		if (n == 0) part1 = m + 1;
		else {
			ArrayList<Integer> f1 = factorize(m);
			ArrayList<Integer> f2 = factorize(n);
			int counter1 = 1, counter2 = 1;
			while (counter1 < f1.size() && counter2 < f2.size()) {
				if (f1.get(counter1) == f2.get(counter2)) {
					f1.remove(counter1);
					f2.remove(counter2);
				} else if (f1.get(counter1) > f2.get(counter2)) counter2++;
				else counter1++;
			}
			part1 = n / multiply(f2) + 1;
		}
		if (p == n) part2 = m;
		else if (n > p) {
			ArrayList<Integer> f3 = factorize(n - p);
			ArrayList<Integer> f4 = factorize(m);
			int counter1 = 1;
			int counter2 = 1;
			while (counter1 < f3.size() && counter2 < f4.size()) {
				if (f3.get(counter1) == f4.get(counter2)) {
					f3.remove(counter1);
					f4.remove(counter2);
				} else if (f3.get(counter1) > f4.get(counter2)) counter2++;
				else counter1++;
			}
			part2 = n / multiply(f3) - p / multiply(f3);
		} else {
			ArrayList<Integer> f3 = factorize(p - n);
			ArrayList<Integer> f4 = factorize(m);
			int counter1 = 1;
			int counter2 = 1;
			while (counter1 < f3.size() && counter2 < f4.size()) {
				if (f3.get(counter1) == f4.get(counter2)) {
					f3.remove(counter1);
					f4.remove(counter2);
				} else if (f3.get(counter1) > f4.get(counter2)) counter2++;
				else counter1++;
			}
			part2 = p / multiply(f3) - n / multiply(f3);
		}
		double area = m * p * 0.5;
		double ans = area - (part1 + part2 + p - 1) * 0.5 + 1;
		out.println((int) (Math.round(ans)));
		in.close();
		out.close();
	}
	
}