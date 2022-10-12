/*
ID: awesome25
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;

class mod {
	
	int component;
	boolean N, E, S, W;
	
	public mod(boolean Nn, boolean Ee, boolean Ss, boolean Ww) {
		N = Nn;
		E = Ee;
		S = Ss;
		W = Ww;
		component = -2;
	}
	
}

class castle {
	
	static mod[][] plan;
	static ArrayList<Integer> areas = new ArrayList<Integer>();
	
	public static int floodFill(int index, int i, int x) {
		int num = 1;
		plan[i][x].component = index;
		if (!plan[i][x].N && plan[i - 1][x].component == -2) num += floodFill(index, i - 1, x);
		if (!plan[i][x].E && plan[i][x + 1].component == -2) num += floodFill(index, i, x + 1);
		if (!plan[i][x].S && plan[i + 1][x].component == -2) num += floodFill(index, i + 1, x);
		if (!plan[i][x].W && plan[i][x - 1].component == -2) num += floodFill(index, i, x - 1);
		return num;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int wide = Integer.parseInt(st.nextToken()), len = Integer.parseInt(st.nextToken()), temp = 0;
		plan = new mod[len][wide];
		for (int i = 0; i < len; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			for (int x = 0; x < wide; x++) {
				boolean N = false, E = false, S = false, W = false;
				temp = Integer.parseInt(st1.nextToken());
				if (temp >= 8) {
					temp -= 8;
					S = true;
				}
				if (temp >= 4) {
					temp -= 4;
					E = true;
				}
				if (temp >= 2) {
					temp -= 2;
					N = true;
				}
				if (temp >= 1) {
					W = true;
				}
				plan[i][x] = new mod(N, E, S, W);
			}
		}
		int counter = 0;
		for (int i = 0; i < len; i++) {
			for (int x = 0; x < wide; x++) {
				if (plan[i][x].component == -2) {
					areas.add(floodFill(counter, i, x));
					counter++;
				}
			}
		}
		int largest = 0, comp1 = -2, comp2 = -2;
		char c = ' ';
		out.println(areas.size());
		for (int i = 0; i < areas.size(); i++) {
			if (areas.get(i) > largest) largest = areas.get(i);
		}
		out.println(largest);
		for (int x = wide - 1; x >= 0; x--) {
			for (int i = 0; i < len; i++) {
				if (x > 0) {
					if (plan[i][x].component != plan[i][x - 1].component) {
						temp = areas.get(plan[i][x].component) + areas.get(plan[i][x - 1].component);
						if (largest <= temp) {
							largest = temp;
							comp1 = i + 1;
							comp2 = x;
							c = 'E';
						}
					}
				}
				if (i < len - 1) {
					if (plan[i][x].component != plan[i + 1][x].component) {
						temp = areas.get(plan[i][x].component) + areas.get(plan[i + 1][x].component);
						if (largest <= temp) {
							largest = temp;
							comp1 = i + 2;
							comp2 = x + 1;
							c = 'N';
						}
					}
				}
			}
		}
		out.println(largest);
		out.println(comp1 + " " + comp2 + " " + c);
		in.close();
		out.close();
	}
}