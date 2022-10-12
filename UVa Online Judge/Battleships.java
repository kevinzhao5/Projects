import java.io.*;

public class Battleships {
	
	static char[][] map;
	static boolean[][] v;
	static int size;
	static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
	static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void fill(int x, int y) {
		if (x < 0 || x >= size || y < 0 || y >= size) return;
		if (map[x][y] != 'x' && map[x][y] != '@') return;
		map[x][y] = '.';
		v[x][y] = true;
		for (int d = 0; d < 8; d++) {
			fill(x + dr[d], y + dc[d]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int tests = Integer.parseInt(in.readLine());
		for (int e = 0; e < tests; e++) {
			size = Integer.parseInt(in.readLine());
			map = new char[size][size];
			v = new boolean[size][size];
			for (int i = 0; i < size; i++) {
				String str = in.readLine();
				for (int j = 0; j < size; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			int ships = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (!v[i][j] && map[i][j] == 'x') {
						ships++;
						fill(i, j);
					}
				}
			}
			System.out.println("Case " + (e + 1) + ": " + ships);
		}
		out.close();
		in.close();
	}

}
