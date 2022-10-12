import java.util.*;
import java.io.*;

public class MN {
	
	static int num, on;
	static String mask;
	static ArrayList<String> sols;
	
	public static void solve(int left, int total, String solution, int start) {
		if (left == 0 && total == num) sols.add(solution);
		else if (left > 0) {
			for (int i = start; i < Math.min(mask.length(), start + mask.length() - on + 1); i++) {
				solve(left - 1, total + (int) (mask.charAt(i)) - 48, solution.substring(0, i) + "1" + solution.substring(i + 1, solution.length()), i + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/MN"));
		in.useDelimiter("\\s");
		for (int e = 0; e < 10; e++) {
			sols = new ArrayList<String>();
			mask = in.next();
			on = in.nextInt();
			num = in.nextInt();
			String s = "00000000";
			solve(on, 0, s.substring(0, mask.length()), 0);
			for (int i = 0; i < sols.size() - 1; i++) {
				System.out.print(sols.get(i) + ", ");
			}
			if (sols.size() > 0) System.out.println(sols.get(sols.size() - 1));
			else System.out.println("NONE");
		}
		in.close();
	}

}