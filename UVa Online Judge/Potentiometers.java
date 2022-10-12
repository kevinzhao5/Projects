import java.util.*;
import java.io.*;

class FenwickTree {
	
	int[] f;
	
	public FenwickTree(int n) {
		f = new int[n + 1];
	}

	public void change(int index, int val) {
		while (index < f.length) {
			f[index] += val;
			index += lsb(index);
		}
	}
	
	public int lsb(int n) {
		return (n & (-n));
	}
	
	public int rsq(int n) {
		int sum = 0;
		while (n > 0) {
			sum += f[n];
			n -= lsb(n);
		}
		return sum;
	}
	
	public int rsq(int a, int b) {
		int ans = rsq(b);
		if (a == 1) return ans;
		else return ans - rsq(a - 1);
	}
	
}


class Potentiometers {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int N = -1, c = 1;
		while (N != 0) {
			N = Integer.parseInt(in.readLine());
			if (N == 0) break;
			if (c > 1) out.println();
			out.println("Case " + c + ":");
			FenwickTree ft = new FenwickTree(N);
			int[] vals = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				vals[i] = Integer.parseInt(in.readLine());
				ft.change(i, vals[i]);
			}
			String act = "";
			while (!act.equals("END")) {
				act = in.readLine();
				if (act.equals("END")) break;
				StringTokenizer st = new StringTokenizer(act);
				char ch = st.nextToken().charAt(0);
				if (ch == 'S') {
					int index = Integer.parseInt(st.nextToken()), newVal = Integer.parseInt(st.nextToken()), diff = newVal - vals[index];
					ft.change(index, diff);
					vals[index] = newVal;
				} else {
					out.println(ft.rsq(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				}
			}
			c++;
		}
		in.close();
		out.close();
	}

}
