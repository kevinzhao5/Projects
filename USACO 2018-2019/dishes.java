import java.io.*;
import java.util.*;

public class dishes {
	
	static int N;
	static int[] dishes;
	
	public static boolean pos(int mid) {
		if (mid <= 3) return true;
		int[][] stacks = new int[N][N];
		int[] counters = new int[N];
		TreeSet<Integer> currDishes = new TreeSet<Integer>();
		int stackCounter = 0;
		for (int i = 0; i < mid; i++) {
			currDishes.add(dishes[i]);
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(stacks[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < mid; i++) {
			if (dishes[i] == currDishes.first()) {
				currDishes.remove(dishes[i]);
				while (counters[stackCounter] > 0 && stacks[stackCounter][counters[stackCounter] - 1] == currDishes.first()) {
					currDishes.remove(currDishes.first());
					stacks[stackCounter][counters[stackCounter]] = Integer.MAX_VALUE;
					counters[stackCounter]--;
					if (counters[stackCounter] == 0) stackCounter++;
				}
			} else {
				int counter = stackCounter;
				while (counters[counter] > 0 && dishes[i] > stacks[counter][counters[counter] - 1] && stacks[counter][counters[counter] - 1] != Integer.MAX_VALUE) {
					counter++;
				}
				if (counter > 0 && stacks[counter - 1][0] > dishes[i]) return false;
				stacks[counter][counters[counter]] = dishes[i];
				counters[counter]++;
			}
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("dishes.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
		N = Integer.parseInt(in.readLine());
		dishes = new int[N];
		for (int i = 0; i < N; i++) {
			dishes[i] = Integer.parseInt(in.readLine());
		}
		int low = 0, high = N, mid = 0, ans = 0;
		while (low != high) {
			if (low + 1 == high) {
				if (pos(high)) ans = high;
				else ans = low;
				break;
			}
			mid = (high + low) / 2;
			if (pos(mid)) low = mid;
			else high = mid - 1;
			ans = mid;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}