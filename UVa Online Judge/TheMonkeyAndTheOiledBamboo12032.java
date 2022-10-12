import java.util.*;
import java.io.*;

class TheMonkeyAndTheOiledBamboo12032 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int cases = Integer.parseInt(in.readLine());
		for (int y = 0; y < cases; y++) {
			int n = Integer.parseInt(in.readLine());
			int[] ladder = new int[n + 1];
			StringTokenizer st = new StringTokenizer(in.readLine());
			ladder[0] = 0;
			for (int i = 1; i <= n; i++) {
				ladder[i] = Integer.parseInt(st.nextToken());
			}
			int lo = 0, hi = 909000, mid = 0;
			for (int x = 0; x < 50; x++) {
				mid = (lo + hi) / 2;
				int temp = mid;
				boolean check = true;
				for (int i = 1; i <= n; i++) {
					if (ladder[i] - ladder[i - 1] == temp) temp--;
					else if (ladder[i] - ladder[i - 1] > temp) {
						check = false;
						break;
					}
				}
				if (check) hi = mid;
				else lo = mid;
			}
			out.println("Case " + (y + 1) + ": " + (mid + 1));
		}
		out.close();
		in.close();
	}

}
