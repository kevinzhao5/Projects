import java.io.*;
import java.util.*;

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

public class circlecross {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		int N = Integer.parseInt(in.readLine());
		int[] ID = new int[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			ID[i] = Integer.parseInt(in.readLine());
		}
		FenwickTree t = new FenwickTree(2 * N);
		int sum = 0;
		int[] entry = new int[N + 1];
		Arrays.fill(entry, -1);
		for (int i = 0; i < 2 * N; i++) {
			int id = ID[i];
			if (entry[id] == -1) {
				sum += t.rsq(i + 1);
				t.change(i + 1, 1);
				entry[id] = i;
			} else {
				t.change(entry[id] + 1, -1);
				sum = sum - t.rsq(entry[id] + 1) + t.rsq(entry[id] + 2, 2 * N);
			}
		}
		out.println(sum / 2);
		in.close();
		out.close();
	}
	
}