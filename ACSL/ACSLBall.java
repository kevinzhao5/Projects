import java.util.*;
import java.io.*;

public class ACSLBall {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/ACSLBall"));
		in.useDelimiter("\\s");
		String[] names = new String[8];
		int[] ones = new int[8];
		int[] twos = new int[8];
		int[] threes = new int[8];
		int totalTh = 0, maxGoal = 0, index = 0, index2 = 0, maxScore = 0, oneSc = 0, twoSc = 0;
		for (int i = 0; i < 8; i++) {
			names[i] = in.next();
			ones[i] = in.nextInt();
			twos[i] = in.nextInt();
			threes[i] = in.nextInt();
			in.nextLine();
			totalTh += threes[i];
			if (ones[i] + twos[i] + threes[i] > maxGoal) {
				maxGoal = ones[i] + twos[i] + threes[i];
				index = i;
			}
			if (ones[i] + twos[i] * 2 + threes[i] * 3 > maxScore) {
				maxScore = ones[i] + twos[i] * 2 + threes[i] * 3;
				index2 = i;
			}
			if (i < 4) oneSc += ones[i] + twos[i] * 2 + threes[i] * 3;
			else if (i >= 4) twoSc += ones[i] + twos[i] * 2 + threes[i] * 3;
		}
		System.out.println(totalTh);
		System.out.println(names[index]);
		System.out.println(names[index2]);
		System.out.println(Math.max(oneSc, twoSc));
		if (oneSc > twoSc) {
			int index3 = 0, maxScoree = 0;
			for (int i = 4; i < 8; i++) {
				if (ones[i] + twos[i] * 2 + threes[i] * 3 > maxScoree) {
					maxScoree = ones[i] + twos[i] * 2 + threes[i] * 3;
					index3 = i;
				}
			}
			ones[index3] = 0;
			twos[index3] = 0;
			threes[index3] = 0;
			maxScoree = 0;
			for (int i = 4; i < 8; i++) {
				if (ones[i] + twos[i] * 2 + threes[i] * 3 > maxScoree) {
					maxScoree = ones[i] + twos[i] * 2 + threes[i] * 3;
					index3 = i;
				}
			}
			System.out.println(names[index3]);
		} else {
			int index3 = 0, maxScoree = 0;
			for (int i = 0; i < 4; i++) {
				if (ones[i] + twos[i] * 2 + threes[i] * 3 > maxScoree) {
					maxScoree = ones[i] + twos[i] * 2 + threes[i] * 3;
					index3 = i;
				}
			}
			ones[index3] = 0;
			twos[index3] = 0;
			threes[index3] = 0;
			maxScoree = 0;
			for (int i = 0; i < 4; i++) {
				if (ones[i] + twos[i] * 2 + threes[i] * 3 > maxScoree) {
					maxScoree = ones[i] + twos[i] * 2 + threes[i] * 3;
					index3 = i;
				}
			}
			System.out.println(names[index3]);
		}
		in.close();
	}

}