/*
ID: awesome25
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

class farmer {
	
	int cost, supply;
	
	public farmer(int cost2, int supply2) {
		cost = cost2;
		supply = supply2;
	}
	
}

class milk {
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		int numMilk = in.nextInt(), numFarmers = in.nextInt(), price = 0;
		ArrayList<farmer> farmers = new ArrayList<farmer>();
		for (int i = 0; i < numFarmers; i++) {
			in.nextLine();
			if (farmers.size() > 0) {
				int cost = in.nextInt(), supply = in.nextInt(), x = 0;
				for (int q = 0; q < farmers.size(); q++) {
					if (cost > farmers.get(q).cost) x++;
					else break;
				}
				farmers.add(x, new farmer(cost, supply));
			} else {
				farmers.add(new farmer(in.nextInt(), in.nextInt()));
			}
		}
		for (int i = 0; i < farmers.size(); i++) {
			if (farmers.get(i).supply >= numMilk) {
				price += numMilk * farmers.get(i).cost;
				break;
			} else {
				numMilk -= farmers.get(i).supply;
				price += farmers.get(i).supply * farmers.get(i).cost;
			}
		}
		out.println(price);
		in.close();
		out.close();
	}
}