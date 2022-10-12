import java.util.*;
import java.io.*;

class pair implements Comparable<pair> {
	
	char c;
	int i;
	
	public pair(char c2, int i2) {
		c = c2;
		i = i2;
	}

	@Override
	public int compareTo(pair o) {
		if (this.i > o.i) return -1;
		else if (this.i < o.i) return 1;
		return this.c - o.c;
	}
	
}

class YouWantWhatFilled {
	
	static char[][] map;
	static boolean[][] v;
	static int x = -1, y = -1;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static int fill(int i, int j, char c) {
		if (i < 0 || j < 0 || i >= x || j >= y) return 0;
		if (map[i][j] != c) return 0;
		int size = 1;
		v[i][j] = true;
		map[i][j] = '.';
		for (int k = 0; k < 4; k++) {
			size += fill(i + dr[k], j + dc[k], c);
		}
		return size;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int counter = 0;
		while (x != 0 || y != 0) {
			counter++;
			StringTokenizer st = new StringTokenizer(in.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			if (x == 0 && y == 0) break;
			map = new char[x][y];
			v = new boolean[x][y];
			for (int i = 0; i < x; i++) {
				String str = in.readLine();
				for (int j = 0; j < y; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			ArrayList<pair> pairs = new ArrayList<pair>();
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					if (!v[i][j] && map[i][j] != '.') {
						pairs.add(new pair(map[i][j], fill(i, j, map[i][j])));
					}
				}
			}
			Collections.sort(pairs);
			out.println("Problem " + counter + ":");
			for (int i = 0; i < pairs.size(); i++) {
				out.println(pairs.get(i).c + " " + pairs.get(i).i);
			}
		}
		out.close();
		in.close();
	}

}
