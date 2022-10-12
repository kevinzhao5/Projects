/*
ID: awesome25
LANG: JAVA
TASK: humble
*/
import java.io.*;
import java.util.*;

class pro {
	
	int prime, product, next, i;
	
	public pro(int prime1, int i2) {
		prime = prime1;
		product = prime * prime;
		next = prime1;
		i = i2;
	}
	
}

class humble {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		ArrayList<pro> nums = new ArrayList<pro>();
		ArrayList<Integer> h = new ArrayList<Integer>();
		for (int i = 0; i < k; i++) {
			nums.add(new pro(Integer.parseInt(st1.nextToken()), i));
			h.add(nums.get(i).prime);
		}
		int last = 0;
		while (h.size() < n) {
			pro temp = nums.remove(0);
			if (temp.product != h.get(last)) {
				if (temp.product < h.get(h.size() - 1)) {
					int count = 0;
					while (h.get(count) < temp.product) count++;
					h.add(count, temp.product);
					for (int i = 0; i < nums.size(); i++) {
						if (nums.get(i).prime > temp.product) nums.get(i).i++;
					}
					last = count;
				} else {
					h.add(temp.product);
					last = h.size() - 1;
				}
			}
			temp.i++;
			temp.next = h.get(temp.i);
			temp.product = temp.next * temp.prime;
			int count = 0;
			while (count < nums.size() && nums.get(count).product < temp.product) count++;
			nums.add(count, temp);
		}
		out.println(h.get(h.size() - 1));
		in.close();
		out.close();
	}
	
}