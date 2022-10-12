import java.util.*;
import java.io.*;
public class Vampire {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/Vampire"));
		in.useDelimiter("\\s");
		for (int q = 0; q < 5; q++) {
			int number = in.nextInt(), numFactors = 0;
			ArrayList<Integer> factors = new ArrayList<Integer>();
			for (int i = 1; i <= number; i++) {
				if (number % i == 0) factors.add(i);
			}
			for (int i = 0; i < factors.size() / 2; i++) {
				if (factors.get(i).toString().length() == Integer.toString(number).length() / 2 && factors.get(factors.size() - i - 1).toString().length() == Integer.toString(number).length() / 2) {
					String str = factors.get(i).toString() + factors.get(factors.size() - i - 1).toString();
					boolean check = true, check2 = false;
					for (int x = 0; x < str.length(); x++) {
						check2 = false;
						for (int s = x; s < str.length(); s++) {
							if (str.charAt(x) == str.charAt(s) && x != s) check = false;
						}
						for (int s = 0; s < str.length(); s++) {
							if (str.charAt(x) == Integer.toString(number).charAt(s)) {
								check2 = true;
							}
						}
						if (!check2) check = false;
					}
					if (check && factors.get(i) % 100 != 0 && factors.get(factors.size() - i - 1) != 0) {
						numFactors++;
						if (numFactors > 1) System.out.print(" and ");
						System.out.print(factors.get(i) + ", " + factors.get(factors.size() - i - 1));
					}
				}
			}
			if (numFactors == 0) System.out.print("NONE");
			System.out.println();
			in.nextLine();
		}
		in.close();
	}
}