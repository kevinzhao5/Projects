/* Kevin Zhao
 * Legion of Learners
 * Senior Division
 * Contest #1 2019-2020
 */

import java.util.*;
import java.io.*;

public class numberTransformation {
		
	/*public static int countPFactors(long b) {
		long sqrt = (int) (Math.sqrt(b)) + 1;
		int num = 0;
		for (int i = 2; i <= sqrt; i++) {
			if (b % i == 0) {
				num++;
				while (b % i == 0) {
					b /= i;
				}
			}
			if (b == 1) break;
		}
		if (b != 1) {
			if (num > 0) {
				num += countPFactors(b);
			} else {
				num++;
			}
		}
		return num;
	}*/
	
	public static int countPFactors(long b) {
		long sqrt = (int) (Math.ceil(Math.sqrt(b)));
		int num = 0;
		for (int i = 2; i <= sqrt; i++) {
			if (b % i == 0) {
				num++;
				while (b % i == 0) {
					b /= i;
				}
			}
			if (b == 1) break;
		}
		if (b != 1) {
			num++;
		}
		return num;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/numberTransformation"));
		for (int q = 0; q < 5; q++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			long N = Long.parseLong(st.nextToken());
			int P = Integer.parseInt(st.nextToken()), numFactors = countPFactors(N), len = (int) (Math.ceil((Math.log10(N))));
			int[] digits = new int[len];
			for (int i = len - 1; i >= 0; i--) {
				digits[i] = (int) (N % 10);
				N /= 10;
			}
			int digit = digits[len - P];
			digits[len - P] = numFactors;
			for (int i = 0; i < len - P; i++) {
				digits[i] += digit;
			}
			for (int i = len - P + 1; i < len; i++) {
				digits[i] = (int) (Math.abs(digit - digits[i]));
			}
			for (int i = 0; i < len; i++) {
				System.out.print(digits[i]);
			}
			System.out.println();
		}
		in.close();
	}
	
}
