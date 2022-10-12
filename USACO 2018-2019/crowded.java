import java.io.*;
import java.util.*;

class p2 implements Comparable<p2> {
	
	int p, h;
	
	public p2(int p1, int h1) {
		p = p1;
		h = h1;
	}

	@Override
	public int compareTo(p2 o) {
		if (this.p == o.p) return this.h - o.h;
		return this.p - o.p;
	}
	
}

public class crowded {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("crowded.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), D = Integer.parseInt(st.nextToken());
		p2[] ps = new p2[N];
		int[] pos = new int[N];
		int[] h = new int[N];
		int[] rightHi = new int[N];
		int[] leftHi = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			ps[i] = new p2(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
		}
		Arrays.sort(ps);
		for (int i = 0; i < N; i++) {
			pos[i] = ps[i].p;
			h[i] = ps[i].h;
		}
		TreeMap<Integer, Integer> curr = new TreeMap<Integer, Integer>();
		int counter = 0;
		while (counter < N && pos[counter] <= pos[0] + D) {
			if (curr.size() > 0 && curr.containsKey(h[counter])) curr.put(h[counter], curr.get(h[counter]) + 1);
			else curr.put(h[counter], 1);
			counter++;
		}
		for (int i = 0; i < N; i++) {
			while (counter < N && pos[counter] <= pos[i] + D) {
				if (curr.size() > 0 && curr.containsKey(h[counter])) curr.put(h[counter], curr.get(h[counter]) + 1);
				else curr.put(h[counter], 1);
				counter++;
			}
			rightHi[i] = curr.lastKey();
			curr.put(h[i], curr.get(h[i]) - 1);
			if (curr.get(h[i]) == 0) curr.remove(h[i]);
		}
		curr.clear();
		counter = N - 1;
		while (counter >= 0 && pos[counter] >= pos[N - 1] - D) {
			if (curr.size() > 0 && curr.containsKey(h[counter])) curr.put(h[counter], curr.get(h[counter]) + 1);
			else curr.put(h[counter], 1);
			counter--;
		}
		for (int i = N - 1; i >= 0; i--) {
			while (counter >= 0 && pos[counter] >= pos[i] - D) {
				if (curr.size() > 0 && curr.containsKey(h[counter])) curr.put(h[counter], curr.get(h[counter]) + 1);
				else curr.put(h[counter], 1);
				counter--;
			}
			leftHi[i] = curr.lastKey();
			curr.put(h[i], curr.get(h[i]) - 1);
			if (curr.get(h[i]) == 0) curr.remove(h[i]);
		}
		int ct = 0;
		for (int i = 0; i < N; i++) {
			if (rightHi[i] >= h[i] * 2 && leftHi[i] >= h[i] * 2) ct++;
		}
		out.println(ct);
		in.close();
		out.close();
	}
	
}