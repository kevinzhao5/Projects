/*
ID: awesome25
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

class point {
	
	int x, y;
	
	public point(int x2, int y2) {
		x = x2;
		y = y2;
	}
	
}

class beads {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		in.readLine();
		int counter = 0, start = 0, max = 0;
		String beads = in.readLine();
		beads = beads + beads;
		ArrayList<point> strings = new ArrayList<point>();
		boolean check = false;
		while (beads.charAt(counter) == 'w') {
			counter++;
			if (counter == beads.length()) {
				out.println(beads.length() / 2);
				check = true;
				break;
			}
		}
		if (!check) {
			char curr = beads.charAt(counter);
			counter = 0;
			for (int i = 0; i < beads.length(); i++) {
				if (curr == beads.charAt(i) || beads.charAt(i) == 'w') {
					if (counter == 0) {
						if (i == 0) {
							start = 0;
						}
						//System.out.println("Start: " + start + " " + curr);
						counter++;
					} else {
						counter++;
						if (counter == beads.length() / 2) {
							strings.add(new point(start, counter));
							break;
						}
					}
				} else {
					strings.add(new point(start, i - 1));
					curr = beads.charAt(i);
					counter = 0;
					//System.out.println("End: " + start + " " + (i - 1) + " " + curr);
					start = i;
					int temp = start - 1;
					//System.out.println(beads.charAt(temp - 1) + " " + beads.charAt(temp));
					while (beads.charAt(temp) == 'w') {
						start--;
						temp--;
						if (temp - 1 <= 0) break;
					}
				}
			}
			for (int i = 0; i < strings.size() - 1; i++) {
				//System.out.println(strings.get(i).x + " " + strings.get(i).y + " "  + strings.get(i + 1).x + " " + strings.get(i + 1).y + " " + (strings.get(i + 1).x - strings.get(i).y) + " " + max);
				if (strings.get(i + 1).y - strings.get(i).x >= max) {
					int tempMax = strings.get(i + 1).y - strings.get(i).x + 1;
					if (tempMax <= beads.length() / 2) {
						max = tempMax;
					}
				}
			}
			if (strings.size() == 1) out.println(beads.length() / 2);
			else out.println(max);
		}
		in.close();
		out.close();
	}
}