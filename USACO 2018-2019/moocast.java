import java.io.*;
import java.util.*;

class cow {
	
	ArrayList<Integer> e;
	
	public cow() {
		e = new ArrayList<Integer>();
	}
	
}

public class moocast {
	
	static cow[] e;
	static boolean[] v;
	
	public static int fill(int w) {
		if (v[w]) return 0;
		v[w] = true;
		int res = 1, s = e[w].e.size();
		for (int i = 0; i < s; i++) {
			res += fill(e[w].e.get(i));
		}
		return res;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int N = Integer.parseInt(in.readLine());
		int[] x = new int[N];
		int[] y = new int[N];
		int[] p = new int[N];
		e = new cow[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
			e[i] = new cow();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (p[i] - (Math.sqrt(Math.pow(Math.abs(x[i] - x[j]), 2) + Math.pow(Math.abs(y[i] - y[j]), 2)))> -0.000000001) {
					e[i].e.add(j);
				}
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			v = new boolean[N];
			max = Math.max(max, fill(i));
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}