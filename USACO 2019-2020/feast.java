import java.io.*;
import java.util.*;

public class feast {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("feast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int T = Integer.parseInt(st.nextToken()), A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
		boolean[][] pos = new boolean[T + 1][2];
		int ans = 0;
		pos[0][0] = true;
		for (int i = 0; i < T; i++) {
			if (!pos[i][0]) continue;
			if (i + A <= T) pos[i + A][0] = true;
			if (i + B <= T) pos[i + B][0] = true;
			pos[i / 2][1] = true;
			ans = Math.max(ans, i);
		}
		for (int i = 0; i < T; i++) {
			if (!pos[i][1]) continue;
			if (i + A <= T) pos[i + A][1] = true;
			if (i + B <= T) pos[i + B][1] = true;
			ans = Math.max(ans, i);
		}
		if (pos[T][0] || pos[T][1]) ans = T;
		out.println(ans);
		in.close();
		out.close();
	}
	
}