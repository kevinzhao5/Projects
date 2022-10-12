import java.io.*;
import java.util.*;

class sto implements Comparable<sto>{
	
	int a, b;
	
	public sto(int a1, int b1) {
		a = a1;
		b = b1;
	}

	@Override
	public int compareTo(sto o) {
		return o.b - this.b;
	}
	
}

public class rental {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rental.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(s1.nextToken()), m = Integer.parseInt(s1.nextToken()), r = Integer.parseInt(s1.nextToken());
		int[] inc = new int[n];
		sto[] sts = new sto[m];
		int[] sel = new int[r];
		for (int i = 0; i < n; i++) {
			inc[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			sts[i] = new sto(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < r; i++) {
			sel[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(inc);
		Arrays.sort(sts);
		Arrays.sort(sel);
		Deque<Integer> cows = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			cows.addFirst(inc[i]);
		}
		long total = 0l;
		int sc = 0, rc = r - 1;
		while (!cows.isEmpty()) {
			int cc = cows.peekFirst(), M = cc, csc = sc;
			long tp = 0l;
			while (M > 0 && csc < m) {
				int sell = Math.min(sts[csc].a, M);
				M -= sell;
				tp += sts[csc].b * sell;
				csc++;
			}
			//System.out.println(tp + " " + sel[rc] + " " + rc + " " + sc);
			if ((rc >= 0 && tp < sel[rc]) || (sc >= m && rc >= 0)) {
				cows.pollLast();
				total += sel[rc];
				rc--;
			} else if (sc < m || (rc < 0 && sc < m)){
				tp = 0l;
				while (cc > 0 && sc < m) {
					int sell = Math.min(sts[sc].a, cc);
					cc -= sell;
					sts[sc].a -= sell;
					tp += sts[sc].b * sell;
					if (sts[sc].a == 0) sc++;
				}
				total += tp;
				cows.pollFirst();
			} else cows.pollFirst();
		}
		out.println(total);
		in.close();
		out.close();
	}
	
}