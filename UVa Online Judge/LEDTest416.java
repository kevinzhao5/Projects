import java.io.*;
import java.util.*;

class LEDTest416 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] vals = {
			{true, true, true, true, true, true, false},
			{false, true, true, false, false, false, false},
			{true, true, false, true, true, false, true},
			{true, true, true, true, false, false, true},
			{false, true, true, false, false, true, true},
			{true, false, true, true, false, true, true},
			{true, false, true, true, true, true, true},
			{true, true, true, false, false, false, false},
			{true, true, true, true, true, true, true},
			{true, true, true, true, false, true, true}
		};
		while (true) {
			int n = Integer.parseInt(in.readLine());
			if (n == 0) break;
			boolean[][] stat = new boolean[n][7];
			for (int i = 0; i < n; i++) {
				String str = in.readLine();
				for (int x = 0; x < 7; x++) {
					if (str.charAt(x) == 'Y') stat[i][x] = true;
					else stat[i][x] = false;
				}
			}
			String s = "MISMATCH";
			int[] burned = new int[7];
			Arrays.fill(burned, n);
			for (int i = 0; i < 7; i++) {
				boolean check = false;
				for (int k = n - 1; k >= 0; k--) {
					if (stat[k][i]) {
						burned[i] = k;
						check = true;
						break;
					}
				}
				if (!check) burned[i] = -1;
			}
			for (int i = 9; i >= n - 1; i--) {
				boolean ss = true;
				for (int x = 0; x < n; x++) {
					for (int j = 0; j < 7; j++) {
						if (vals[i - x][j]) {
							if (stat[x][j] && x > burned[j]) ss = false;
							else if (!stat[x][j] && x <= burned[j]) ss = false;
						} else {
							if (stat[x][j]) ss = false;
						}
						if (!ss) break;
					}
					if (!ss) break;
				}
				if (ss) {
					s = "MATCH";
					break;
				}
			}
			System.out.println(s);
		}
		in.close();
	}

}
