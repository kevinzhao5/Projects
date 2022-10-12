import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class IncreasingArray {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		long moves = 0, num = Integer.parseInt(st.nextToken());
		while (st.hasMoreTokens()) {
			long currNum = Integer.parseInt(st.nextToken());
			if (currNum < num) {
				moves += num - currNum;
				currNum = num;
			}
			num = currNum;
		}
		out.println(moves);
		in.close();
		out.close();
	}
	
}
