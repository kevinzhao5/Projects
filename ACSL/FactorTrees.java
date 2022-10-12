import java.util.*;
import java.io.*;

public class FactorTrees {

	static int n;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/factors"));
		for (int w = 0; w < 10; w++) {
			n = Integer.parseInt(in.nextLine());
			String ans = "";
			System.out.println(n);
			int[] factors = new int[10000];
			while (n > 1) {
				boolean check = false;
				for (int i = 2; i <= n; i++) {
					if (n % i == 0) {
						n /= i;
						factors[i]++;
						if (n == 1) break;
						check = true;
						ans = " X " + i + ans;
						break;
					}
				}
				if (check) System.out.println(n + ans);
			}
			String print = "";
			for (int i = 9999; i > 0; i--) {
				if (factors[i] > 0) {
					if (factors[i] == 1) print += i + " X ";
					else print += i + " ^ " + factors[i] + " X ";
				}
			}
			System.out.println(print.substring(0, print.length() - 3));
		}
		in.close();
	}

}