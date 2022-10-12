import java.io.*;
import java.util.*;

public class balance {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("balance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N * 2];
		StringTokenizer st = new StringTokenizer(in.readLine());
		int c1 = 0, c2 = 0, num1 = 0, num2 = 0;
		for (int i = 0; i < N * 2; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i < N) {
				if (arr[i] == 1) num1++;
				else c1 += num1;
			} else {
				if (arr[i] == 1) num2++;
				else c2 += num2;
			}
		}
		out.println(Math.min(Math.abs(c1 - c2), Math.abs(num1 - num2)));
		in.close();
		out.close();
	}
	
}