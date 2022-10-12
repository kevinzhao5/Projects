import java.util.*;
import java.io.*;

public class Deal {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/deal"));
		in.useDelimiter("\\s");
		for (int i = 0; i < 5; i++) {
			int rn = in.nextInt(), num = in.nextInt(), ls = 0, hs = 0;
			double expected = 0;
			int[] cases = new int[num];
			for (int x = 0; x < num; x++) {
				cases[x] = in.nextInt();
				expected += cases[x];
				if (cases[x] < 10000) ls += cases[x];
				else hs += cases[x];
			}
			expected /= cases.length;
			System.out.print((int)Math.floor(expected) + ", ");
			Arrays.sort(cases);
			if (num % 2 == 0) System.out.print((int)Math.floor((cases[num / 2] + cases[num / 2 - 1]) / 2) + ", ");
			else System.out.print(cases[num / 2] + ", ");
			System.out.println((int)Math.floor(((.07 + rn * .05) * ls) + (6 * rn * .0077 * hs)));
		}
		in.close();
	}

}