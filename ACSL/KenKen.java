import java.util.*;
import java.io.*;

class section {
	
	ArrayList<Integer> tiles = new ArrayList<Integer>();
	int sum;
	
	public section(ArrayList<Integer> tiles2, int sum2) {
		tiles = tiles2;
		sum = sum2;
	}
	
}

public class KenKen {

	static int[] board = new int[9];
	static ArrayList<section> sections = new ArrayList<section>();
	
	public static int solve(int left) {
		if (left == 0) {
			for (int i = 0; i < 9; i += 3) {
				if (board[i] == board[i + 1] || board[i + 1] == board[i + 2] || board[i] == board[i + 2]) return 0;
			}
			for (int i = 0; i < 3; i++) {
				if (board[i] == board[i + 3] || board[i + 3] == board[i + 6] || board[i] == board[i + 6]) return 0;
			}
			for (int i = 0; i < sections.size(); i++) {
				int sum = 0;
				for (int x = 0; x < sections.get(i).tiles.size(); x++) {
					sum += board[sections.get(i).tiles.get(x)];
				}
				if (sum != sections.get(i).sum) return 0;
			}
			return 1;
		}
		left--;
		for (int i = 0; i < 9; i++) {
			if (board[i] == 0) {
				board[i] = 1;
				if (solve(left) == 1) return 1;
				board[i] = 2;
				if (solve(left) == 1) return 1;
				board[i] = 3;
				if (solve(left) == 1) return 1;
				board[i] = 0;
			}
		}
		return 0;
	}
	
	public static int solve2(int left) {
		if (left == 0) {
			for (int i = 0; i < 16; i += 4) {
				if (board[i] == board[i + 1] || board[i] == board[i + 2] || board[i] == board[i + 3] || board[i + 1] == board[i + 2] || board[i + 1] == board[i + 3] || board[i + 2] == board[i + 3]) return 0;
			}
			for (int i = 0; i < 4; i++) {
				if (board[i] == board[i + 4] || board[i] == board[i + 8] || board[i] == board[i + 12] || board[i + 4] == board[i + 8] || board[i + 4] == board[i + 12] || board[i + 8] == board[i + 12]) return 0;
			}
			for (int i = 0; i < sections.size(); i++) {
				int sum = 0;
				for (int x = 0; x < sections.get(i).tiles.size(); x++) {
					sum += board[sections.get(i).tiles.get(x)];
				}
				if (sum != sections.get(i).sum) return 0;
			}
			return 1;
		}
		left--;
		for (int i = 0; i < 16; i++) {
			if (board[i] == 0) {
				board[i] = 1;
				if (check(i) && solve2(left) == 1) return 1;
				board[i] = 2;
				if (check(i) && solve2(left) == 1) return 1;
				board[i] = 3;
				if (check(i) && solve2(left) == 1) return 1;
				board[i] = 4;
				if (check(i) && solve2(left) == 1) return 1;
				board[i] = 0;
				return 0;
			}
		}
		return 0;
	}
	
	public static boolean check(int i) {
		int t = i / 4 * 4;
		for (int j = 0; j < 4; j++) {
			if (t + j == i) continue;
			if (board[t + j] == board[i]) return false;
		}
		t = i % 4;
		for (int j = 0; j < 16; j += 4) {
			if (t + j == i) continue;
			if (board[t + j] == board[i]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/KenKen"));
		in.useDelimiter("\\s");
		int numSec = in.nextInt(), left = 9;
		Arrays.fill(board, 0);
		for (int i = 0; i < numSec; i++) {
			ArrayList<Integer> tiles = new ArrayList<Integer>();
			String temp = "";
			while (temp.length() < 2) {
				temp = in.next();
				if (temp.length() == 2) {
					if (temp.charAt(1) == '#') {
						board[tiles.get(0)] = (int) (temp.charAt(0)) - 48;
						left--;
					} else {
						sections.add(new section(tiles, (int) (temp.charAt(0)) - 48));
					}
				} else {
					tiles.add(Integer.parseInt(temp) - 1);
				}
			}
		}
		in.nextLine();
		StringTokenizer st = new StringTokenizer(in.nextLine());
		solve(left);
		ArrayList<Integer> indices = new ArrayList<Integer>();
		while (st.hasMoreTokens()) {
			indices.add(Integer.parseInt(st.nextToken()) - 1);
		}
		for (int i = 0; i < indices.size(); i++) {
			System.out.println(board[indices.get(i)]);
		}
		
		
		sections = new ArrayList<section>();
		st = new StringTokenizer(in.nextLine());
		numSec = Integer.parseInt(st.nextToken());
		left = 16;
		board = new int[16];
		Arrays.fill(board, 0);
		for (int i = 0; i < numSec; i++) {
			ArrayList<Integer> tiles = new ArrayList<Integer>();
			String temp = "";
			while (temp.equals("") || (int)temp.charAt(temp.length() - 1) >= 48) {
				temp = st.nextToken();
				if ((int)temp.charAt(temp.length() - 1) < 48) {
					if (temp.charAt(temp.length() - 1) == '#') {
						board[tiles.get(0)] = Integer.parseInt(temp.substring(0, temp.length() - 1));
						left--;
					} else {
						sections.add(new section(tiles, Integer.parseInt(temp.substring(0, temp.length() - 1))));
					}
				} else {
					tiles.add(Integer.parseInt(temp) - 1);
				}
			}
		}
		solve2(left);
		indices = new ArrayList<Integer>();
		while (in.hasNextInt()) {
			indices.add(in.nextInt() - 1);
		}
		for (int i = 0; i < indices.size(); i++) {
			System.out.println(board[indices.get(i)]);
		}
		in.close();
	}

}