/*
ID: awesome25
LANG: JAVA
TASK: msquare
*/
import java.io.*;
import java.util.*;

class msquare {
	
	static boolean[] v = new boolean[8765433];
	static String target;
	static int depth = 0;
	static ArrayList<Integer> ints = new ArrayList<Integer>(40320);
	static ArrayList<String> sols = new ArrayList<String>(40320);
	
	public static int A(int n) {
		int a = n % 10;
		a = a * 10 + (n % 100) / 10;
		a = a * 10 + (n % 1000) / 100;
		a = a * 10 + (n % 10000) / 1000;
		a = a * 10 + (n % 100000) / 10000;
		a = a * 10 + (n % 1000000) / 100000;
		a = a * 10 + (n % 10000000) / 1000000;
		a = a * 10 + (n % 100000000) / 10000000;
		return a;
	}
	
	public static int B(int n) {
		int a = 0;
		a = (n % 100000) / 10000;
		a = a * 10 + (n % 100000000) / 10000000;
		a = a * 10 + (n % 10000000) / 1000000;
		a = a * 10 + (n % 1000000) / 100000;
		a = a * 10 + (n % 1000) / 100;
		a = a * 10 + (n % 100) / 10;
		a = a * 10 + n % 10;
		a = a * 10 + (n % 10000) / 1000;
		return a;
	}
	
	public static int C(int n) {
		int a = (n % 100000000) / 10000000;
		a = a * 10 + (n % 100) / 10;
		a = a * 10 + (n % 10000000) / 1000000;
		a = a * 10 + (n % 100000) / 10000;
		a = a * 10 + (n % 10000) / 1000;
		a = a * 10 + (n % 1000000) / 100000;
		a = a * 10 + (n % 1000) / 100;
		a = a * 10 + n % 10;
		return a;
	}
	
	public static String solve() {
		int temp = ints.size();
		for (int i = 0; i < temp; i++) {
			int s = ints.remove(0);
			if (v[s / 10]) { 
				sols.remove(0);
				continue;
			}
			String sol = sols.remove(0);
			if (Integer.toString(s).equals(target)) return sol;
			v[s / 10] = true;
			ints.add(A(s));
			sols.add(sol + 'A');
			ints.add(B(s));
			sols.add(sol + 'B');
			ints.add(C(s));
			sols.add(sol + 'C');
		}
		depth++;
		return solve();
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		in.close();
		target = "";
		for (int i = 0; i < 8; i++) {
			target += st.nextToken();
		}
		ints.add(12345678);
		sols.add("");
		String sol = solve();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		out.println(depth);
		for (int i = 0; i <= sol.length() / 60; i++) {
			out.println(sol.substring(i, Math.min(sol.length(), i + 60)));
		}
		out.close();
	}
	
}