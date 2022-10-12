/*
ID: awesome25
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

class crypt1 {
	
	public static boolean isIn(char c, int[] a) {
		boolean check = false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == (int) (c) - 48) check = true;
		}
		return check;
	}
	
	public static boolean isPrime(int n) {
		boolean check = false;
		if ((n == 2) || (n == 3) || (n == 5) || (n == 7)) check = true;
		return check;
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int n = in.nextInt(), counter = 0;
		int[] a = new int[n];
		in.nextLine();
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		for (int i = 0; i < n; i++) {
			//if (isPrime(a[i])) {
			for (int x = 0; x < n; x++) {
				//if (isPrime(a[x])) {
				for (int q = 0; q < n; q++) {
					//if (isPrime(a[q])) {
					for (int c = 0; c < n; c++) {
						//if (isPrime(a[c])) {
						for (int s = 0; s < n; s++) {
							//if (isPrime(a[s])) {
								int firstNum = (a[i] * 100 + a[x] * 10 + a[q]);
								if ((firstNum * a[c]) / 100 > 0 && (firstNum * a[c]) / 1000 == 0) {
									if ((firstNum * a[s]) / 100 > 0 && (firstNum * a[s]) / 1000 == 0) {
										if ((firstNum * (a[c] * 10 + a[s])) / 1000 > 0 && (firstNum * (a[c] * 10 + a[s])) / 10000 == 0) {
											String num = Integer.toString(firstNum * (a[c] * 10 + a[s])), num1 = Integer.toString(firstNum * (a[c])), num2 = Integer.toString(firstNum * (a[s]));
											boolean check = true;
											for (int e = 0; e < 4; e++) {
												if (!isIn(num.charAt(e), a)) {
													check = false;
												}
											}
											for (int e = 0; e < 3; e++) {
												if (!isIn(num1.charAt(e), a) || !isIn(num2.charAt(e), a)) {
													check = false;
												}
											}
											if (check) {
												counter++;
												System.out.println(firstNum + " " + num1 + " " + num2 + " " + num);
											}
										}
									}
								}
							//}
						}
						//}
					}
					//}
				}
				//}
			}
			//}
		}
		out.println(counter);
		in.close();
		out.close();
	}
}