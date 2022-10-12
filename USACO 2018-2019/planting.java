import java.io.*;
import java.util.*;

class b {
	
	ArrayList<Integer> a;
	
}

public class planting {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		int N = Integer.parseInt(in.readLine());
		b[] n = new b[N];
		for (int i = 0; i < N; i++) {
			n[i] = new b();
			n[i].a = new ArrayList<Integer>();
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
			n[a].a.add(b);
			n[b].a.add(a);
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			int s = 1 + n[i].a.size();
			max = Math.max(max, s);
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}