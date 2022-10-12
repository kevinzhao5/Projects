import java.util.*;
import java.io.*;

public class Checkers {
	
	static int max = 0, e;
	
	public static void find(int curr, int[][] board, int x, int y, boolean isKing) {
		if (x == -1 && y == -1) {
			for (int i = 2; i <= 9; i++) {
				for (int j = 2; j <= 9; j++) {
					boolean f = false;
					if (i == 9) f = true;
					if (board[i][j] == 1) {
						find(0, board, i, j, f);
					}
				}
			}
		} else {
			boolean check = false;
			if (x + 2 <= 9 && y + 2 <= 9 && board[x + 1][y + 1] == 2 && board[x + 2][y + 2] == 0) {
				board[x][y] = 0;
				board[x + 1][y + 1] = 0;
				board[x + 2][y + 2] = 1;
				boolean is = isKing;
				if (x + 2 == 9) is = true;
				find(curr + 1, board, x + 2, y + 2, is);
				board[x][y] = 1;
				board[x + 1][y + 1] = 2;
				board[x + 2][y + 2] = 0;
				check = true;
			}
			if (x + 2 <= 9 && y - 2 >= 2 && board[x + 1][y - 1] == 2 && board[x + 2][y - 2] == 0) {
				board[x][y] = 0;
				board[x + 1][y - 1] = 0;
				board[x + 2][y - 2] = 1;
				boolean is = isKing;
				if (x + 2 == 9) is = true;
				find(curr + 1, board, x + 2, y - 2, is);
				board[x][y] = 1;
				board[x + 1][y - 1] = 2;
				board[x + 2][y - 2] = 0;
				check = true;
			}
			if (isKing) {
				if (x - 2 >= 2 && y - 2 >= 2 && board[x - 1][y - 1] == 2 && board[x - 2][y - 2] == 0) {
					board[x][y] = 0;
					board[x - 1][y - 1] = 0;
					board[x - 2][y - 2] = 1;
					find(curr + 1, board, x - 2, y - 2, isKing);
					board[x][y] = 1;
					board[x - 1][y - 1] = 2;
					board[x - 2][y - 2] = 0;
					check = true;
				}
				if (x - 2 >= 2 && y + 2 <= 9 && board[x - 1][y + 1] == 2 && board[x - 2][y + 2] == 0) {
					board[x][y] = 0;
					board[x - 1][y + 1] = 0;
					board[x - 2][y + 2] = 1;
					find(curr + 1, board, x - 2, y + 2, isKing);
					board[x][y] = 1;
					board[x - 1][y + 1] = 2;
					board[x - 2][y + 2] = 0;
					check = true;
				}
			}
			if (!check) {
				if (curr > max) max = curr;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/Checkers"));
		in.useDelimiter("\\s");
		for (e = 0; e < 5; e++) {
			int[][] board = new int[12][12];
			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				board[in.nextInt() + 1][in.nextInt() + 1] = 1;
			}
			n = in.nextInt();
			for (int i = 0; i < n; i++) {
				board[in.nextInt() + 1][in.nextInt() + 1] = 2;
			}
			find(0, board, -1, -1, false);
			System.out.println(max);
			max = 0;
		}
		in.close();
	}

}
