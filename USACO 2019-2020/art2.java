import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class art2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
		int N = Integer.parseInt(in.readLine());
		int[] color = new int[N];
		int[] num = new int[N];
		for (int i = 0; i < N; i++) {
			color[i] = Integer.parseInt(in.readLine()) - 1;
			if (color[i] == -1) continue;
			num[color[i]]++;
		}
		int[] seen = new int[N];
		int[] fl = new int[N];
		int ans = 0, layer = 0;
		for (int i = 0; i < N; i++) {
			int c = color[i];
			if (c == -1) {
				layer = 0;
				continue;
			}
			seen[c]++;
			if (seen[c] == 1) {
				layer++;
				fl[c] = layer;
			}
			ans = Math.max(ans, layer);
			if (seen[c] == num[c]) {
				if (layer != fl[c]) {
					ans = -1;
					break;
				}
				layer--;
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}