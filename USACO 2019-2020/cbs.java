import java.io.*;
import java.util.*;

class c {
	
	int a, b;
	
	public c(int aa, int bb) {
		a = aa;
		b = bb;
	}
	
}

class d {
	
	TreeSet<Integer> t;
	
	public d() {
		t = new TreeSet<Integer>();
	}
	
}

public class cbs {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cbs.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbs.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int K = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
		String[] paren = new String[K];
		for (int i = 0; i < K; i++) {
			paren[i] = in.readLine();
		}
		d[][] bal = new d[K][N];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < N; j++) {
				bal[i][j] = new d();
			}
		}
		for (int i = 0; i < K; i++) {
			TreeMap<Integer, TreeSet<Integer>> t = new TreeMap<Integer, TreeSet<Integer>>();
			int sum = 0, low = 0;
			Stack<c> s = new Stack<c>();
			for (int j = 0; j < N; j++) {
				char c = paren[i].charAt(j);
				if (c == '(') sum++;
				else sum--;
				if (sum < low) {
					t.clear();
					low = sum;
				}
				while (!s.isEmpty() && s.peek().a >= sum) {
					s.pop();
				}
				int index = -100;
				if (!s.isEmpty()) index = s.peek().b;
				s.push(new c(sum, j));
				if (!t.isEmpty() && t.containsKey(sum) && c == ')') {
					for (int k : t.get(sum)) {
						if (k > index) bal[i][j].t.add(j - k + 1);
					}
				} else if (c == '(') {
					if (!t.isEmpty() && t.containsKey(sum - 1)) {
						TreeSet<Integer> tt = t.get(sum - 1);
						tt.add(j);
						t.put(sum - 1, tt);
					} else {
						TreeSet<Integer> tt = new TreeSet<Integer>();
						tt.add(j);
						t.put(sum - 1, tt);
					}
				}
			}
			/*for (int j = 0; j < N; j++) {
				for (int k : bal[i][j].t) {
					System.out.print(k + " ");
				}
				System.out.print("/ ");
			}
			System.out.println();*/
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			int add = bal[0][i].t.size();
			if (add == 0) continue;
			for (int j : bal[0][i].t) {
				for (int k = 1; k < K; k++) {
					if (!bal[k][i].t.contains(j)) {
						add--;
						break;
					}
				}
			}
			ans += add;
		}
		//System.out.println(ans);
		out.println(ans);
		in.close();
		out.close();
	}
	
}