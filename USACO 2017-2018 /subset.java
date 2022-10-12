/*
ID: awesome25
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;

class subset {
	
	static long[][] sums = new long[4000][40];
	
	public static long numCombo(int total, int nums) {
		if (total < 0 || nums < 0) return 0;
		if (sums[total][nums] != -1) return sums[total][nums];
		if (total == 0 && nums == 0) return sums[total][nums] = 1;
		return sums[total][nums] = numCombo(total, nums - 1) + numCombo(total - nums, nums - 1);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int n = Integer.parseInt(in.readLine()), temp = (n * (n + 1)) / 2;
		for (int i = 0; i < sums.length; i++) {
			Arrays.fill(sums[i], -1);
		}
		if (temp % 2 == 1) out.println("0");
		else out.println(numCombo(temp / 2, n) / 2);
		in.close();
		out.close();
	}
}