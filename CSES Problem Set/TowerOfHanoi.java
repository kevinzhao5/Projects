import java.io.*;
import java.util.*;

public class TowerOfHanoi {
	
	static ArrayList<Integer> moves1, moves2;
	
	public static void rec(int n, int start, int middle, int end) {
		if (n == 1) {
			moves1.add(start);
			moves2.add(end);
			return;
		}
		rec(n - 1, start, end, middle);
		moves1.add(start);
		moves2.add(end);
		rec(n - 1, middle, start, end);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		moves1 = new ArrayList<>();
		moves2 = new ArrayList<>();
		rec(n, 1, 2, 3);
		System.out.println(moves1.size());
		for (int i = 0; i < moves1.size(); i++) {
			System.out.println(moves1.get(i) + " " + moves2.get(i));
		}
		in.close();
		out.close();
	}
	
}
