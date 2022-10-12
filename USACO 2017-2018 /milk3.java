/*
ID: awesome25
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.*;

class bucket {
	
	int max, amt;
	
	public bucket(int max2, int fill2) {
		max = max2;
		amt = fill2;
	}
	
}

class milk3 {
	
	static ArrayList<Integer> nums = new ArrayList<Integer>();
	static boolean[][][] check;
	
	public static void find(bucket a, bucket b, bucket c, int depth) {
		if (!check[a.amt][b.amt][c.amt]) {
			check[a.amt][b.amt][c.amt] = true;
			if (a.amt == 0) {
				nums.add(c.amt);
			}
			if (b.amt > a.max - a.amt) {
				find(new bucket(a.max, a.max), new bucket(b.max, b.amt - (a.max - a.amt)), c, depth + 1);
			} else {
				find(new bucket(a.max, a.amt + b.amt), new bucket(b.max, 0), c, depth + 1);
			}
			if (c.amt > a.max - a.amt) {
				find(new bucket(a.max, a.max), b, new bucket(c.max, c.amt - (a.max - a.amt)), depth + 1);
			} else {
				find(new bucket(a.max, a.amt + c.amt), b, new bucket(c.max, 0), depth + 1);
			}
			if (a.amt > b.max - b.amt) {
				find(new bucket(a.max, a.amt - (b.max - b.amt)), new bucket(b.max, b.max), c, depth + 1);
			} else {
				find(new bucket(a.max, 0), new bucket(b.max, b.amt + a.amt), c, depth + 1);
			}
			if (c.amt > b.max - b.amt) {
				find(a, new bucket(b.max, b.max), new bucket(c.max, c.amt - (b.max - b.amt)), depth + 1);
			} else {
				find(a, new bucket(b.max, b.amt + c.amt), new bucket(c.max, 0), depth + 1);
			}
			if (b.amt > c.max - c.amt) {
				find(a, new bucket(b.max, b.amt - (c.max - c.amt)), new bucket(c.max, c.max), depth + 1);
			} else {
				find(a, new bucket(b.max, 0), new bucket(c.max, c.amt + b.amt), depth + 1);
			}
			if (a.amt > c.max - c.amt) {
				find(new bucket(a.max, a.amt - (c.max - c.amt)), b, new bucket(c.max, c.max), depth + 1);
			} else {
				find(new bucket(a.max, 0), b, new bucket(c.max, c.amt + a.amt), depth + 1);
			}
		}
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		bucket a = new bucket(in.nextInt(), 0), b = new bucket(in.nextInt(), 0);
		int temp = in.nextInt();
		bucket c = new bucket(temp, temp);
		check = new boolean[a.max + 1][b.max + 1][c.max + 1];
		find(a, b, c, 0);
		int[] vals = new int[nums.size()];
		for (int i = 0; i < nums.size(); i++) {
			vals[i] = nums.get(i);
		}
		Arrays.sort(vals);
		for (int i = 0; i < vals.length - 1; i++) {
			out.print(vals[i] + " ");
		}
		out.println(vals[vals.length - 1]);
		in.close();
		out.close();
	}
}