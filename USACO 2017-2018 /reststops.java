//ID: awesome25

import java.util.*;
import java.io.*;

class restStop {
	
	int position, tastiness;
	
	public restStop(int position2, int tastiness2) {
		position = position2;
		tastiness = tastiness2;
	}
	
}

public class reststops {
	
	public static int findNextBest(double position, restStop[] stops) {
		int best = 0;
		int maxTasty = 0;
		boolean found = false;
		for (int i = 0; i < stops.length; i++) {
			if (stops[i].position > position && stops[i].tastiness > maxTasty) {
				best = i;
				maxTasty = stops[i].tastiness;
				found = true;
			}
		}
		if (!found) return -1;
		return best;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		double length = in.nextInt(), numRest = in.nextInt(), FJspeed = in.nextInt(), Bspeed = in.nextInt(), time = 0, FJposition = 0, Bposition = 0;
		restStop[] stops = new restStop[(int) numRest];
		for (int i = 0; i < numRest; i++) {
			in.nextLine();
			stops[i] = new restStop(in.nextInt(), in.nextInt());
		}
		int bestRest = findNextBest(0, stops), tastyPoints = 0;
		boolean rest = false;
		while (time / FJspeed < length - 0.0001) {
			time++;
			FJposition += 1 / FJspeed;
			if (bestRest > -1) {
				if (Bposition >= stops[bestRest].position - 0.00001 && Bposition <= stops[bestRest].position + 0.00001) {
					rest = true;
				}
				if (!rest) Bposition += 1 / Bspeed;
				else if (rest) {
					if ((FJposition + 0.0001 >= Bposition)) {
						tastyPoints += stops[bestRest].tastiness;
						rest = false;
						Bposition += 1 / Bspeed;
						bestRest = findNextBest(Bposition, stops);
					} else {
						tastyPoints += stops[bestRest].tastiness;
					}
				}
				if (bestRest == -1) {
					tastyPoints -= stops[stops.length - 1].tastiness;
					bestRest = -2;
				}
			}
		}
		out.println(tastyPoints);
		in.close();
		out.close();
	}

}
