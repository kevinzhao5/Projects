/* Kevin Zhao
   Legion of Learners
   Junior Division
   Contest #4 2017-2018
   Duplicates.java
*/

import java.util.*;
import java.io.*;

public class Duplicates {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/Duplicate"));
		in.useDelimiter("\\s");
		for (int q = 0; q < 5; q++) {
			int position = in.nextInt() - 1, num = 0;
			char preChar = ' ';
			String preStr = in.nextLine(), str = "";
			ArrayList<Character> listChar = new ArrayList<Character>();
			for (int i = 0; i < preStr.length(); i++) {
				if (preStr.charAt(i) >= 97 && preStr.charAt(i) <= 122) str += (char)(preStr.charAt(i) - 32);
				else if (preStr.charAt(i) >= 65 && preStr.charAt(i) <= 90) str += preStr.charAt(i);
			}
			for (int i = 0; i < str.length(); i++) {
				if (position < listChar.size()) {
					preChar = listChar.get(position);
				}
				int index = 0;
				for (int x = 0; x < listChar.size(); x++) {
					if (listChar.get(x) >= str.charAt(i)) break;
					index++;
				}
				listChar.add(index, str.charAt(i));
				if (position < listChar.size()) {
					if (preChar != listChar.get(position)) {
						num++;
					}
				}
			}
			System.out.println(num);
		}
		in.close();
	}
}