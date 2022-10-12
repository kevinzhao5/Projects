/*
ID: awesome25
LANG: JAVA
TASK: spin
*/
import java.io.*;
import java.util.*;

class spin {
	
	static int[] speed = new int[5];
	static int[][] pos = new int[5][360];
	static int[] zero = new int[5];
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("spin.in"));
		for (int i = 0; i < 5; i++) {
			Arrays.fill(pos[i], 1);
		}
		Arrays.fill(zero, 0);
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			speed[i] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			for (int x = 0; x < num; x++) {
				int t = Integer.parseInt(st.nextToken()), temp = Integer.parseInt(st.nextToken());
				for (int s = t; s <= t + temp; s++) {
					pos[i][s % 360] = -1;
				}
			}
		}
		in.close();
		int ans = -1;
		for (int i = 0; i < 360; i++) {
			for (int x = 0; x < 360; x++) {
				if (pos[0][(zero[0] + x) % 360] == -1 && pos[1][(zero[1] + x) % 360] == -1 && pos[2][(zero[2] + x) % 360] == -1 && pos[3][(zero[3] + x) % 360] == -1 && pos[4][(zero[4] + x) % 360] == -1) {
					ans = i;
					break;
				}
			}
			if (ans != -1) break;
			for (int x = 0; x < 5; x++) {
				zero[x] -= speed[x];
				if (zero[x] < 0) zero[x] += 360;
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		if (ans == -1) out.println("none");
		else out.println(ans);
		out.close();
	}
	
}