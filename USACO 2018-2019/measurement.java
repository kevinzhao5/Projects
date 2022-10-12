import java.io.*;
import java.util.*;

class ms implements Comparable<ms> {
	
	int day, id, change;
	
	public ms(int day1, int id1, int change1) {
		day = day1;
		id = id1;
		change = change1;
	}

	@Override
	public int compareTo(ms o) {
		return this.day - o.day;
	}
	
}

public class measurement {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		ms[] mes = new ms[Integer.parseInt(st.nextToken())];
		int g = Integer.parseInt(st.nextToken());
		for (int i = 0; i < mes.length; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			mes[i] = new ms(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		Arrays.sort(mes);
		TreeMap<Integer, Integer> cows = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> vals = new TreeMap<Integer, Integer>();
		int c = 0;
		vals.put(g, 1000000);
		for (int i = 0; i < mes.length; i++) {
			int id = mes[i].id, val = 0;
			if (!cows.containsKey(id)) {
				val = g;
			} else {
				val = cows.get(id);
			}
			boolean top = val == vals.lastKey();
			int nc = vals.get(val);
			vals.put(val, vals.get(val) - 1);
			if (nc == 1) vals.remove(val);
			val += mes[i].change;
			cows.put(id, val);
			int newc = 0;
			if (vals.containsKey(val)) {
				newc = vals.get(val) + 1;
				vals.put(val, vals.get(val) + 1);
			} else {
				vals.put(val, 1);
				newc = 1;
			}
			boolean currTop = val == vals.lastKey();
			if (top) {
				if (!currTop || nc != 1 || newc != 1) c++;
			} else if (currTop) c++;
		}
		out.println(c);
		in.close();
		out.close();
	}
	
}