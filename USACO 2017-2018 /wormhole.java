/*
ID: awesome25
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;

class hole {
	
	int x, y, goTo, index;
	boolean traveled;
	
	public hole(int x2, int y2, int index2) {
		x = x2;
		y = y2;
		traveled = false;
		goTo = 0;
		index = index2;
	}
	
}

class wormhole {
	
	public static int findLoops(ArrayList<hole> wormholes, int p) {
		int num = 0;
		if (p == wormholes.size() - 2) {
			wormholes.get(p).goTo = wormholes.get(p + 1).index;
			wormholes.get(p + 1).goTo = wormholes.get(p).index;
			boolean check = false;
			for (int c = 0; c < wormholes.size(); c++) {
				for (int d = 0; d < wormholes.size(); d++) {
					wormholes.get(d).traveled = false;
				}
				int y = wormholes.get(c).y, x = wormholes.get(c).x - 1;
				check = false;
				for (int r = 0; r < wormholes.size() + 1; r++) {
					int m = 0;
					hole lowest = new hole(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
					for (int q = 0; q < wormholes.size(); q++) {
						if (wormholes.get(q).y == y && wormholes.get(q).x > x) {
							if (wormholes.get(q).x <= lowest.x) {
								lowest = wormholes.get(q);
								check = true;
								m = q;
							}
						}
					}
					if (check) {
						if (wormholes.get(m).traveled) {
							/*for (int e = 0; e < wormholes.size(); e++) {
								System.out.println(wormholes.get(e).x + " " + wormholes.get(e).y + " " + wormholes.get(e).index + " " + wormholes.get(e).goTo);
							}
							System.out.println();*/
							return 1;
						} 
						int index = -1;
						for (int s = 0; s < wormholes.size(); s++) {
							if (wormholes.get(s).index == wormholes.get(m).goTo) {
								index = s;
								break;
							}
						}
						y = wormholes.get(index).y;
						x = wormholes.get(index).x;
						wormholes.get(m).traveled = true;
						check = false;
					} else break;
				}
			}
			return 0;
		} else {
			for (int i = p + 1; i < wormholes.size(); i++) {
				wormholes.get(p).goTo = wormholes.get(i).index;
				wormholes.get(i).goTo = wormholes.get(p).index;
				hole temp = wormholes.remove(i);
				wormholes.add(p + 1, temp);
				num += findLoops(wormholes, p + 2);
				temp = wormholes.remove(p + 1);
				wormholes.add(i, temp);
			}
		}
		return num;
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("wormhole.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		int numWormholes = in.nextInt();
		ArrayList<hole> wormholes = new ArrayList<hole>();
		for (int i = 0; i < numWormholes; i++) {
			in.nextLine();
			wormholes.add(new hole(in.nextInt(), in.nextInt(), i));
		}
		out.println(findLoops(wormholes, 0));
		in.close();
		out.close();
	}
}