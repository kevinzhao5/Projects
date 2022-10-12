import java.util.*;
import java.io.*;

public class Krypto {

	static int target = 0;
	
	public static boolean isIn(char c, ArrayList<Character> ops) {
		for (int i = 0; i < ops.size(); i++) {
			if (c == ops.get(i)) return true;
		}
		return false;
	}
	
	public static int solve(ArrayList<Integer> ints, ArrayList<Character> ops) {
		if (ops.size() == 4) {
			ArrayList<Integer> ints1 = new ArrayList<Integer>();
			ArrayList<Integer> ints2 = new ArrayList<Integer>();
			for (int i = 0; i < ints.size(); i++) {
				ints1.add(ints.get(i));
				ints2.add(ints.get(i));
			}
			ArrayList<Character> ops1 = new ArrayList<Character>();
			for (int i = 0; i < ops.size(); i++) {
				ops1.add(ops.get(i));
			}
			for (int i = 0; i < ops1.size(); i++) {
				if (ops1.get(i) == '*') {
					int temp = ints2.remove(i), temp1 = ints2.remove(i);
					ints2.add(i, temp * temp1);
					ops1.remove(i);
					i--;
				} else if (ops1.get(i) == '/') {
					int temp = ints2.remove(i), temp1 = ints2.remove(i);
					ints2.add(i, temp / temp1);
					ops1.remove(i);
					i--;
				} else if (ops1.get(i) == '+') {
					int temp = ints2.remove(i), temp1 = ints2.remove(i);
					ints2.add(i, temp + temp1);
					ops1.remove(i);
					i--;
				} else if (ops1.get(i) == '-') {
					int temp = ints2.remove(i), temp1 = ints2.remove(i);
					ints2.add(i, temp - temp1);
					ops1.remove(i);
					i--;
				}
			}
			if (ints2.get(0) == target) {
				for (int i = 0; i < 4; i++) {
					System.out.print(ints1.get(i) + " " + ops.get(i) + " ");
				}
				System.out.println(ints1.get(4) + " = " + target);
				return 1;
			} else return 0;
		} else {
			if (!isIn('+', ops)) {
				ops.add('+');
				if (solve(ints, ops) == 1) return 1;
				ops.remove(ops.size() - 1);
			}
			if (!isIn('-', ops)) {
				ops.add('-');
				if (solve(ints, ops) == 1) return 1;
				ops.remove(ops.size() - 1);
			}
			if (!isIn('*', ops)) {
				ops.add('*');
				if (solve(ints, ops) == 1) return 1;
				ops.remove(ops.size() - 1);
			}
			if (!isIn('/', ops)) {
				ops.add('/');
				if (solve(ints, ops) == 1) return 1;
				ops.remove(ops.size() - 1);
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/krypto"));
		in.useDelimiter(",\\s|\\s");
		for (int q = 0; q < 10; q++) {
			ArrayList<Integer> ints = new ArrayList<Integer>();
			ArrayList<Character> ops = new ArrayList<Character>();
			for (int i = 0; i < 5; i++) {
				ints.add(in.nextInt());
			}
			target = in.nextInt();
			in.nextLine();
			solve(ints, ops);
		}
		in.close();
	}

}