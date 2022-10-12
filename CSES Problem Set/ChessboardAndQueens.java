import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class ChessboardAndQueens {
	
	static int[][] board = new int[8][8];
	
	public static long rec(int row) {
		if (row == 8) return 1;
		int count = 0;
		for (int i = 0; i < 8; i++) {
			if (board[row][i] != 0) continue;
			changeQueen(row, i, 1);
			count += rec(row + 1);
			changeQueen(row, i, -1);
		}
		return count;
	}
	
	public static void changeQueen(int r, int c, int val) {
		board[r][c] += val;
		for (int i = r + 1; i < 8; i++) {
			if (board[i][c] == -1) continue;
			board[i][c] += val;
		}
		for (int i = r - 1; i >= 0; i--) {
			if (board[i][c] == -1) continue;
			board[i][c] += val;
		}
		for (int i = c + 1; i < 8; i++) {
			if (board[r][i] == -1) continue;
			board[r][i] += val;
		}
		for (int i = c - 1; i >= 0; i--) {
			if (board[r][i] == -1) continue;
			board[r][i] += val;
		}
		for (int i = 1; r + i < 8 && c + i < 8; i++) {
			if (board[r + i][c + i] == -1) continue;
			board[r + i][c + i] += val;
		}
		for (int i = 1; r - i >= 0 && c - i >= 0; i++) {
			if (board[r - i][c - i] == -1) continue;
			board[r - i][c - i] += val;
		}
		for (int i = 1; r + i < 8 && c - i >= 0; i++) {
			if (board[r + i][c - i] == -1) continue;
			board[r + i][c - i] += val;
		}
		for (int i = 1; r - i >= 0 && c + i < 8; i++) {
			if (board[r - i][c + i] == -1) continue;
			board[r - i][c + i] += val;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for (int i = 0; i < 8; i++) {
			String s = in.readLine();
			for (int j = 0; j < 8; j++) {
				char c = s.charAt(j);
				if (c == '*') board[i][j] = -1;
			}
		}
		System.out.println(rec(0));
		in.close();
		out.close();
	}
	
}
