import java.io.*;
import java.util.*;

public class Sum100 {
	
	static int num;
	static ArrayList<String> sols = new ArrayList<String>();
	
	public static boolean eval(ArrayList<Character> ops) {
		ArrayList<Integer> vals = new ArrayList<Integer>();
		for (int i = 0; i < ops.size() + 1; i++) {
			vals.add(i + 1);
		}
		int counter = 0, count = 0;
		for (int i = 0; i < ops.size(); i++) {
			if (ops.get(i) == ' ') {
				int temp1 = vals.remove(i - counter), temp2 = vals.remove(i - counter);
				vals.add(i - counter, temp1 * 10 + temp2);
				counter++;
			}
		}
		while (count < num - 1) {
			char c = ops.get(count);
			count++;
			if (c == ' ') continue;
			int temp1 = vals.remove(0), temp2 = vals.remove(0);
			if (c == '+') vals.add(0, temp1 + temp2);
			else vals.add(0, temp1 - temp2);
		}
		if (vals.get(0) == 100) return true;
		return false;
	}
	
	public static void solve(int depth, ArrayList<Character> ops) throws IOException {
		if (depth == 0) {
			if (eval(ops)) {
				String sol = "";
				for (int i = 0; i < ops.size(); i++) {
					sol += ops.get(i);
				}
				sols.add(sol);
			}
		} else {
			ops.add('+');
			solve(depth - 1, ops);
			ops.remove(ops.size() - 1);
			ops.add('-');
			solve(depth - 1, ops);
			ops.remove(ops.size() - 1);
			ops.add(' ');
			solve(depth - 1, ops);
			ops.remove(ops.size() - 1);
		}
	}
	
	public static void main (String [] args) throws IOException {
		num = 9;
		ArrayList<Character> ops = new ArrayList<Character>();
		solve(num - 1, ops);
		Collections.sort(sols);
		for (int i = 0; i < sols.size(); i++) {
			System.out.print("1");
			for (int x = 0; x < num - 1; x++) {
				System.out.print(sols.get(i).charAt(x) + "" + (x + 2));
			}
			System.out.println();
		}
	}
	
}