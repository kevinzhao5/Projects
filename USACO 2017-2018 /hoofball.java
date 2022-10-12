//ID: awesome25

import java.io.*;
import java.util.*;

class cow {
	
	int passTo, position;
	boolean passed;
	
	public cow(int passTo2, int position2, boolean passed2) {
		passTo = passTo2;
		position = position2;
		passed = passed2;
	}
	
}

public class hoofball {
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("hoofball.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hoofball.out")));
		int numCows = in.nextInt(), numBalls = 0;
		if (numCows <= 2) {
			out.println("1");
		} else {
			in.nextLine();
			cow[] cows = new cow[numCows];
			for (int i = 0; i < numCows; i++) {
				cows[i] = new cow(0, in.nextInt(), false);
			}
			for (int i = 1; i < numCows; i++) {
				for (int x = 0; x < numCows - i; x++) {
					if (cows[x].position > cows[x + 1].position) {
						int temp = cows[x].position;
						cows[x].position = cows[x + 1].position;
						cows[x + 1].position = temp;
					}
				}
			}
			cows[0].passTo = 1;
			cows[cows.length - 1].passTo = cows.length - 2;
			for (int i = 1; i < numCows - 1; i++) {
				if (Math.abs(cows[i].position - cows[i - 1].position) > Math.abs(cows[i].position - cows[i + 1].position)) cows[i].passTo = i + 1;
				else cows[i].passTo = i - 1;
			}
			for (int i = 0; i < numCows; i++) {
				if (!cows[i].passed) {
					int index = i;
					int end = 0, begin = i, length = 0;
					while (!cows[index].passed) {
						cows[index].passed = true;
						index = cows[index].passTo;
						length++;
						if (cows[index].position > cows[end].position) end = index;
					}
					numBalls++;
					while (1 == 1) {
						boolean check = false;;
						for (int x = 0; x < numCows; x++) {
							if (cows[x].passTo == begin && !cows[x].passed) {
								cows[x].passed = true;
								begin = x;
								check = true;
								break;
							} else if (cows[x].passTo == end && !cows[x].passed && length == 2) {
								cows[x].passed = true;
								end = x;
								check = true;
								break;
							}
						}
						if (!check) {
							break;
						}
					}
				}
			}
			out.println(numBalls);
		}
		in.close();
		out.close();
	}
}