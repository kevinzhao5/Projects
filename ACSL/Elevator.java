import java.util.*;
import java.io.*;

public class Elevator {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/elevator"));
		in.useDelimiter(",\\s|\\s");
		String elev = in.next();
		if (elev.charAt(0) == 'G') elev = "1" + elev.charAt(1);
		ArrayList<Integer> floors = new ArrayList<Integer>();
		for (int u = 0; u < 10; u++) {
			String next = in.next();
			int temp = 1;
			if ((int) (next.charAt(0)) < 65) temp = Integer.parseInt(next);
			floors.add(temp);
			for (int i = 0; i < floors.size(); i++) {
				if (floors.get(i) == (int) (elev.charAt(0)) - 48) {
					floors.remove(i);
					i--;
				}
			}
			System.out.println(floors.size());
			if (elev.charAt(1) == 'U' && elev.charAt(0) != '5') elev = ((char) ((int) (elev.charAt(0)) + 1)) + "U";
			else if (elev.charAt(1) == 'U' && elev.charAt(0) == '5') elev = "4D";
			else if (elev.charAt(1) == 'D' && elev.charAt(0) != '1') elev = ((char) ((int) (elev.charAt(0)) - 1)) + "D";
			else if (elev.charAt(1) == 'D' && elev.charAt(0) == '1') elev = "2U";
		}
		in.close();
	}

}