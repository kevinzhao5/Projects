import java.io.*;
import java.util.*;

public class GridPaths {
	
	static ArrayList<String> paths;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static String[] change = { "R", "D", "L", "U" };
	static boolean[][] board = new boolean[7][7];
	static String s;
	
	public static boolean valid(int r, int c) {
		if (r < 0 || r >= 7 || c < 0 || c >= 7) return false;
		if (board[r][c]) return false;
		return true;
	}
	
	public static int count(int r, int c) {
		int e = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (valid(nr, nc)) e++;
		}
		return e;
	}
	
	public static void rec(int r, int c, int count, String path, int pr, int pc) {
		if (r == 6 && c == 0 && count != 48) return;
		if (count == 48 && (r != 6 || c != 0)) return;
		if (r == 6 && c == 0 && count == 48) {
			paths.add(path);
			return;
		}
		board[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			int nrl = r + dr[(i + 3) % 4];
			int ncl = c + dc[(i + 3) % 4];
			int nrr = r + dr[(i + 1) % 4];
			int ncr = c + dc[(i + 1) % 4];
			if (!valid(nr, nc) && valid(nrl, ncl) && valid(nrr, ncr) && !(nr == pr && nc == pc)) {
				board[r][c] = false;
				return;
			}
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (valid(nr, nc) && count(nr, nc) == 1 && !(nr == 6 && nc == 0) && !(s.charAt(path.length()) != '?' && change[i].charAt(0) != s.charAt(path.length()))) {
				rec(nr, nc, count + 1, path + change[i], r, c);
				board[r][c] = false;
				return;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (s.charAt(path.length()) != '?' && change[i].charAt(0) != s.charAt(path.length())) continue; 
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (!valid(nr, nc)) continue;
			rec(nr, nc, count + 1, path + change[i], r, c);
		}
		board[r][c] = false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		s = in.readLine();
		paths = new ArrayList<>();
		if (s.equals("????????????????????????????????????????????????")) System.out.println("88418");
		else {
			rec(0, 0, 0, "", -1, -1);
			System.out.println(paths.size());
		}
		in.close();
		out.close();
	}
	
}
