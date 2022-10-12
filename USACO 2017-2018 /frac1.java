/*
ID: awesome25
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;

class fraction {
	
	double numer, denom, val;
	
	public fraction(int num2, int den2) {
		numer = num2;
		denom = den2;
		val = numer / denom;
	}
	
}

class factorList {
	
	ArrayList<Integer> factors = new ArrayList<Integer>();
	
}

class frac1 {
	
	public static factorList findFactors(int num) {
		factorList list = new factorList();
		for (int i = 2; i < num / 2 + 1; i++) {
			if (num % i == 0) list.factors.add(i);
		}
		list.factors.add(num);
		return list;
	}
	
	public static boolean relativePrime(factorList num1, factorList num2) {
		for (int i = 0; i < num1.factors.size(); i++) {
			for (int x = 0; x < num2.factors.size(); x++) {
				if (num1.factors.get(i) == num2.factors.get(x)) return false;
			}
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int denom = Integer.parseInt(in.readLine());
		ArrayList<fraction> fractions = new ArrayList<fraction>();
		fractions.add(new fraction(0, 1));
		fractions.add(new fraction(1, 1));
		factorList[] factors = new factorList[denom];
		for (int i = 1; i <= denom; i++) {
			factors[i - 1] = findFactors(i);
		}
		for (int i = 2; i <= denom; i++) {
			for (int x = 1; x < i; x++) {
				if (relativePrime(factors[i - 1], factors[x - 1])) fractions.add(new fraction(x, i));
			}
		}
		Collections.sort(fractions, 
                (o1, o2) -> (int) (o1.val * 100000 - o2.val * 100000));
		for (int i = 0; i < fractions.size(); i++) {
			out.println((int)fractions.get(i).numer + "/" + (int)fractions.get(i).denom);
		}
		in.close();
		out.close();
	}
}