/*
ID: awesome25
LANG: JAVA
TASK: sprime
*/
import java.io.*;
import java.util.*;

class sprime {
	static ArrayList<Integer> nums = new ArrayList<Integer>();
	static int len;
	
	public static boolean isPrime(int n) {
		if (n == 2) return true;
		if (n % 2 == 0) return false;
		for (int i = 3; i < Math.sqrt(n) + 1; i += 2) {
			if (n % i == 0) return false;
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		len = Integer.parseInt(in.readLine());
		//fill(len, 0);
		nums.add(2);
		nums.add(3);
		nums.add(5);
		nums.add(7);
		for (int i = 0; i < len - 1; i++) {
			ArrayList<Integer> newNums = new ArrayList<Integer>();
			int counter = 0;
			for (int x = 0; x < nums.size(); x++) {
				for (int q = 1; q <= 9; q += 2) {
					newNums.add(nums.get(x) * 10 + q);
					if (!isPrime(newNums.get(counter))) {
						newNums.remove(counter);
					} else counter++;
				}
			}
			nums = newNums;
		}
		for (int i = 0; i < nums.size(); i++) {
			out.println(nums.get(i));
		}
		in.close();
		out.close();
	}
}