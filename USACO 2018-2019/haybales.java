import java.io.*;
import java.util.*;

public class haybales {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(s1.nextToken()), Q = Integer.parseInt(s1.nextToken());
		int[] b = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) b[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(b);
		for (int i = 0; i < Q; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(s.nextToken()), y = Integer.parseInt(s.nextToken());
			x = Arrays.binarySearch(b, x);
			if (x < 0) x = -(x + 1);
			y = Arrays.binarySearch(b, y);
			if (y < 0) y = -(y + 2);
			if (x >= N) out.println(0);
			else if (y < 0) out.println(0);
			else out.println(Math.min(y, N - 1) - Math.max(x, 0) + 1);
		}
		in.close();
		out.close();
	}
	
}
