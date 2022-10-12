//Team Legion of Learners Junior Division

import java.util.*;
import java.io.*;

public class HexgridWalk {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/as3-sample.txt"));
		in.useDelimiter("\\s");
		for (int q = 0; q < 10; q++) {
			String c = in.next(), nums = in.next();
			if (q != 9) in.nextLine();
			char cell = c.charAt(0);
			long index = (long) (c.charAt(1)) - 48l;
			for (int i = 0; i < nums.length(); i++) {
				int num = (int) (nums.charAt(i)) - 48;
				switch(num) {
				case(1): index++; break;
				case(2): 
					if (cell != 'Z') {
						if ((int) (cell) % 2 == 1) index++;
						cell = (char) ((int) (cell) + 1);
					}
					break;
				case(3): 
					if (cell != 'Z') {
						if (index > 1 || (int) (cell) % 2 == 1) {
							if ((int) (cell) % 2 == 0) {
								index--;
							}
							cell = (char) ((int) (cell) + 1);
						}
					}
					break;
				case(4): if (index > 1) index--; break;
				case(5):
					if (cell != 'A') {
						if (index > 1 || (int) (cell) % 2 == 1) {
							if ((int) (cell) % 2 == 0) {
								index--;
							}
							cell = (char) ((int) (cell) - 1);
						}
					}
					break;
				case(6):
					if (cell != 'A') {
						if ((int) (cell) % 2 == 1) index++;
						cell = (char) ((int) (cell) - 1);
					}
					break;
				}
			}
			String space = "";
			if (q == 9) space = " ";
			else space = "  ";
			System.out.println((q + 1) + "." + space + cell + index);
		}
		in.close();
	}

}