/*
ID: awesome25
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

class combo {
	
	public static int check(int[] code, int[] key, int num) {
		int b = 0;
		for (int i = 0; i < 3; i++) {
			if (Math.abs(code[i] - key[i]) <= 2 || Math.abs(code[i] - num - key[i]) <= 2 || Math.abs(code[i] - num - key[i] + num) <= 2 || Math.abs(code[i] - key[i] + num) <= 2) b++;
		}
		//if (b == 3) System.out.println(code[0] + " " + code[1] + " " + code[2]);
		return b;
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		int num = in.nextInt(), counter = 125;
		if (num == 1) {
			out.println("1");
		} else if (num == 2) {
			out.println("8");
		} else if (num == 3) {
			out.println("27");
		} else if (num == 4) {
			out.println("64");
		} else if (num == 5) {
			out.println("125");
		} else {
			int[] combo1 = new int[3];
			in.nextLine();
			for (int i = 0; i < 3; i++) {
				combo1[i] = in.nextInt();
			}
			int[] combo2 = new int[3];
			in.nextLine();
			for (int i = 0; i < 3; i++) {
				combo2[i] = in.nextInt();
			}
			int[] code = new int[3];
			for (int i = combo1[0] - 2; i <= combo1[0] + 2; i++) {
				for (int x = combo1[1] - 2; x <= combo1[1] + 2; x++) {
					for (int c = combo1[2] - 2; c <= combo1[2] + 2; c++) {
						code[0] = i;
						code[1] = x;
						code[2] = c;
						if (check(code, combo2, num) < 3) counter++;
					}
				}
			}
			out.println(counter);
		}
		in.close();
		out.close();
	}
}