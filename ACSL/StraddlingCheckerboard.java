import java.io.*;
import java.util.*;

public class StraddlingCheckerboard {
	
	public static boolean isIn(char[][] coding, char c) {
		for (int x = 0; x < 10; x++) {
			if (c == coding[0][x]) return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/check"));
		in.useDelimiter("\\s");
		for (int d = 0; d < 10; d++) {
			String code = in.next(), encode = in.next();
			int key = in.nextInt(), one = -1, two = -1;
			in.nextLine();
			int[] keys = new int[10];
			char[][] coding = new char[3][10];
			coding[2][9] = '/';
			coding[2][8] = '.';
			for (int i = 0; i < 10; i++) {
				keys[i] = key;
				key++;
				key = key % 10;
			}
			for (int i = 0; i < 10; i++) {
				coding[0][i] = code.charAt(i);
				if (code.charAt(i) == '#') {
					if (two == -1) two = keys[i];
					else one = keys[i];
				}
			}
			if (two < one) {
				int temp = one;
				one = two;
				two = temp;
			}
			int point1 = 1, point2 = 0;
			for (int i = 0; i < 26; i++) {
				char c = (char) (i + 65);
				if (!isIn(coding, c)) {
					coding[point1][point2] = c;
					point2++;
					if (point2 == 10) {
						point2 = 0;
						point1++;
					}
				}
			}
			String output = "";
			for (int i = 0; i < encode.length(); i++) {
				int row = -1, col = -1;
				char c = encode.charAt(i);
				for (int w = 0; w < 3; w++) {
					for (int q = 0; q < 10; q++) {
						if (c == coding[w][q]) {
							row = w;
							col = q;
						}
					}
				}
				if (row == 0) output += keys[col] + " ";
				else if (row == 1) output += one + " " + keys[col] + " ";
				else if (row == 2) output += two + " " + keys[col] + " ";
			}
			System.out.println(output);
		}
		in.close();
	}

}