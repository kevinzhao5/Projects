import java.io.*;
import java.util.*;

public class Seega {
	
	public static int findMove(int x, int y) {
		int move = 0;
		x = 4 - x;
		move += 5 * x + y + 1;
		return move;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/seega"));
		in.useDelimiter("\\s|,\\s|,");
		for (int q = 0; q < 5; q++) {
			char[] board1 = new char[25];
			int num = in.nextInt();
			Arrays.fill(board1, ' ');
			for (int i = 0; i < num; i++) {
				board1[in.nextInt() - 1] = 'X';
			}
			for (int i = 0; i < num; i++) {
				board1[in.nextInt() - 1] = 'O';
			}
			char[][] board = new char[5][5];
			for (int i = 0; i < 5; i++) {
				for (int x = 0; x < 5; x++) {
					board[i][x] = board1[(4 - i) * 5 + x];
				}
			}
			int move = -1, moveO = -1, Xx = -1, Ox = -1, Xy = -1, Oy = -1;
			boolean Xcapture = false;
			for (int i = 0; i < 5; i++) {
				for (int x = 0; x < 5; x++) {
					if (x < 4) {
						if (board[i][x] == 'O' && board[i][x + 1] == 'X') {
							if (x > 1 && board[i][x - 2] == 'X' && board[i][x - 1] == ' ') move = findMove(i, x - 1);
							else if (i > 0 && x > 0 && board[i - 1][x - 1] == 'X' && board[i][x - 1] == ' ') move = findMove(i, x - 1);
							else if (i < 4 && x > 0 && board[i + 1][x - 1] == 'X' && board[i][x - 1] == ' ') move = findMove(i, x - 1);
							if (move != -1) {
								Ox = i;
								Oy = x;
								Xcapture = true;
							}
						}
					}
					if (i > 1) {
						if (board[i][x] == 'X' && board[i - 1][x] == 'O') {
							if (i > 2 && board[i - 3][x] == 'X' && board[i - 2][x] == ' ') move = findMove(i - 2, x);
							else if (x > 0 && board[i - 2][x - 1] == 'X' && board[i - 2][x] == ' ') move = findMove(i - 2, x);
							else if (x < 4 && board[i - 2][x + 1] == 'X' && board[i - 2][x] == ' ') move = findMove(i - 2, x);
							if (move != -1) {
								Ox = i;
								Oy = x;
								Xcapture = true;
							}
						}
					}
					if (move != -1) break;
				}
				if (move != -1) break;
			}
			if (move == -1) {
				for (int i = 0; i < 5; i++) {
					for (int x = 0; x < 5; x++) {
						if (x > 0) {
							if (board[i][x] == 'O' && board[i][x - 1] == 'X') {
								if (x < 3 && board[i][x + 2] == 'X' && board[i][x + 1] == ' ') move = findMove(i, x + 1);
								else if (i > 0 && x < 4 && board[i - 1][x + 1] == 'X' && board[i][x + 1] == ' ') move = findMove(i, x + 1);
								else if (i < 4 && x < 4 && board[i + 1][x + 1] == 'X' && board[i][x + 1] == ' ') move = findMove(i, x + 1);
								if (move != -1) {
									Ox = i;
									Oy = x;
									Xcapture = true;
								}
							}
						}
						if (i < 3) {
							if (board[i][x] == 'X' && board[i + 1][x] == 'O') {
								if (i < 2 && board[i + 3][x] == 'X' && board[i + 2][x] == ' ') move = findMove(i + 2, x);
								else if (x > 0 && board[i + 2][x - 1] == 'X' && board[i + 2][x] == ' ') move = findMove(i + 2, x);
								else if (x < 4 && board[i + 2][x + 1] == 'X' && board[i + 2][x] == ' ') move = findMove(i + 2, x);
								if (move != -1) {
									Ox = i;
									Oy = x;
									Xcapture = true;
								}
							}
						}
						if (move != -1) break;
					}
					if (move != -1) break;
				}
			}
			for (int i = 0; i < 5; i++) {
				for (int x = 0; x < 5; x++) {
					if (x < 4) {
						if (board[i][x] == 'X' && board[i][x + 1] == 'O') {
							if (x > 1 && board[i][x - 2] == 'O' && board[i][x - 1] == ' ') moveO = findMove(i, x - 1);
							else if (i > 0 && x > 0 && board[i - 1][x - 1] == 'O' && board[i][x - 1] == ' ') moveO = findMove(i, x - 1);
							else if (i < 4 && x > 0 && board[i + 1][x - 1] == 'O' && board[i][x - 1] == ' ') moveO = findMove(i, x - 1);
						}
						if (moveO != -1) {
							Xx = i;
							Xy = x;
						}
					}
					if (i > 1) {
						if (board[i][x] == 'O' && board[i - 1][x] == 'X') {
							if (i > 2 && board[i - 3][x] == 'O' && board[i - 2][x] == ' ') moveO = findMove(i - 2, x);
							else if (x > 0 && board[i - 2][x - 1] == 'O' && board[i - 2][x] == ' ') moveO = findMove(i - 2, x);
							else if (x < 4 && board[i - 2][x + 1] == 'O' && board[i - 2][x] == ' ') moveO = findMove(i - 2, x);
						}
						if (moveO != -1) {
							Xx = i;
							Xy = x;
						}
					}
					if (moveO != -1) break;
				}
				if (moveO != -1) break;
			}
			if (moveO == -1) {
				for (int i = 0; i < 5; i++) {
					for (int x = 0; x < 5; x++) {
						if (x > 0) {
							if (board[i][x] == 'X' && board[i][x - 1] == 'O') {
								if (x < 3 && board[i][x + 2] == 'O' && board[i][x + 1] == ' ') moveO = findMove(i, x + 1);
								else if (i > 0 && x < 4 && board[i - 1][x + 1] == 'O' && board[i][x + 1] == ' ') moveO = findMove(i, x + 1);
								else if (i < 4 && x < 4 && board[i + 1][x + 1] == 'O' && board[i][x + 1] == ' ') moveO = findMove(i, x + 1);
								if (move != -1) {
									Xx = i;
									Xy = x;
								}
							}
						}
						if (i < 3) {
							if (board[i][x] == 'O' && board[i + 1][x] == 'X') {
								if (i < 2 && board[i + 3][x] == 'O' && board[i + 2][x] == ' ') moveO = findMove(i + 2, x);
								else if (x > 0 && board[i + 2][x - 1] == 'O' && board[i + 2][x] == ' ') moveO = findMove(i + 2, x);
								else if (x < 4 && board[i + 2][x + 1] == 'O' && board[i + 2][x] == ' ') moveO = findMove(i + 2, x);
								if (move != -1) {
									Xx = i;
									Xy = x;
								}
							}
						}
						if (moveO != -1) break;
					}
					if (moveO != -1) break;
				}
			}
			if (move == -1) {
				if (moveO != -1) {
					if (board[2][2] == ' ') {
						if (Xx == 1 && Xy == 2 || Xx == 2 && Xy == 1 || Xx == 2 && Xy == 3 || Xx == 3 && Xy == 2) {
							move = 13;
						}
					}
					if (move == -1) {
						if (Xx > 0 && Xy > 0 && Xy < 4) if (board[Xx - 1][Xy] == ' ' && board[Xx - 1][Xy - 1] == 'O' && board[Xx - 1][Xy + 1] == 'O') move = findMove(Xx - 1, Xy);
						if (Xx < 4 && Xy > 0 && Xy < 4) if (board[Xx + 1][Xy] == ' ' && board[Xx + 1][Xy - 1] == 'O' && board[Xx + 1][Xy + 1] == 'O') move = findMove(Xx + 1, Xy);
						if (Xx > 0 && Xy > 0 && Xx < 4) if (board[Xx][Xy - 1] == ' ' && board[Xx - 1][Xy - 1] == 'O' && board[Xx + 1][Xy - 1] == 'O') move = findMove(Xx, Xy - 1);
						if (Xx > 0 && Xy < 4 && Xy < 4) if (board[Xx][Xy + 1] == ' ' && board[Xx - 1][Xy + 1] == 'O' && board[Xx + 1][Xy + 1] == 'O') move = findMove(Xx, Xy + 1);
					}
					if (move == -1) {
						if (Xy > 0 && board[Xx][Xy - 1] == ' ') move = findMove(Xx, Xy - 1);
						else if (Xy < 4 && board[Xx][Xy + 1] == ' ') move = findMove(Xx, Xy + 1);
						else if (Xx > 0 && board[Xx - 1][Xy] == ' ') move = findMove(Xx - 1, Xy);
						else if (Xx < 4 && board[Xx + 1][Xy] == ' ') move = findMove(Xx + 1, Xy);
					}
				}
			}
			if (moveO == -1) {
				if (Xcapture) {
					if (board[2][2] == ' ') {
						if (Ox == 1 && Oy == 2 || Ox == 2 && Oy == 1 || Ox == 2 && Oy == 3 || Ox == 3 && Oy == 2) {
							moveO = 13;
						}
					}
					if (moveO == -1) {
						if (Ox > 0 && Oy > 0 && Oy < 4) if (board[Ox - 1][Oy] == ' ' && board[Ox - 1][Oy - 1] == 'O' && board[Ox - 1][Oy + 1] == 'O') moveO = findMove(Ox - 1, Oy);
						if (Ox < 4 && Oy > 0 && Oy < 4) if (board[Ox + 1][Oy] == ' ' && board[Ox + 1][Oy - 1] == 'O' && board[Ox + 1][Oy + 1] == 'O') moveO = findMove(Ox + 1, Oy);
						if (Ox > 0 && Oy > 0 && Ox < 4) if (board[Ox][Oy - 1] == ' ' && board[Ox - 1][Oy - 1] == 'O' && board[Ox + 1][Oy - 1] == 'O') moveO = findMove(Ox, Oy - 1);
						if (Ox > 0 && Oy < 4 && Oy < 4) if (board[Ox][Oy + 1] == ' ' && board[Ox - 1][Oy + 1] == 'O' && board[Ox + 1][Oy + 1] == 'O') moveO = findMove(Ox, Oy + 1);
					}
					if (moveO == -1) {
						if (Oy > 0 && board[Ox][Oy - 1] == ' ') moveO = findMove(Ox, Oy - 1);
						else if (Oy < 4 && board[Ox][Oy + 1] == ' ') moveO = findMove(Ox, Oy + 1);
						else if (Ox > 0 && board[Ox - 1][Oy] == ' ') moveO = findMove(Ox - 1, Oy);
						else if (Ox < 4 && board[Ox + 1][Oy] == ' ') moveO = findMove(Ox + 1, Oy);
					}
				}
			}
			if (move == -1) {
				for (int i = 4; i >= 0; i--) {
					for (int x = 0; x < 5; x++) {
						if (board[i][x] == 'X') {
							Xx = i;
							Xy = x;
							if (board[2][2] == ' ') {
								if (Xx == 1 && Xy == 2 || Xx == 2 && Xy == 1 || Xx == 2 && Xy == 3 || Xx == 3 && Xy == 2) {
									move = 13;
								}
							}
							if (move == -1) {
								if (Xx > 0 && Xy > 0 && Xy < 4) if (board[Xx - 1][Xy] == ' ' && board[Xx - 1][Xy - 1] == 'O' && board[Xx - 1][Xy + 1] == 'O') move = findMove(Xx - 1, Xy);
								if (Xx < 4 && Xy > 0 && Xy < 4) if (board[Xx + 1][Xy] == ' ' && board[Xx + 1][Xy - 1] == 'O' && board[Xx + 1][Xy + 1] == 'O') move = findMove(Xx + 1, Xy);
								if (Xx > 0 && Xy > 0 && Xx < 4) if (board[Xx][Xy - 1] == ' ' && board[Xx - 1][Xy - 1] == 'O' && board[Xx + 1][Xy - 1] == 'O') move = findMove(Xx, Xy - 1);
								if (Xx > 0 && Xy < 4 && Xy < 4) if (board[Xx][Xy + 1] == ' ' && board[Xx - 1][Xy + 1] == 'O' && board[Xx + 1][Xy + 1] == 'O') move = findMove(Xx, Xy + 1);
							}
							if (move == -1) {
								if (Xy > 0 && board[Xx][Xy - 1] == ' ') move = findMove(Xx, Xy - 1);
								else if (Xy < 4 && board[Xx][Xy + 1] == ' ') move = findMove(Xx, Xy + 1);
								else if (Xx > 0 && board[Xx - 1][Xy] == ' ') move = findMove(Xx - 1, Xy);
								else if (Xx < 4 && board[Xx + 1][Xy] == ' ') move = findMove(Xx + 1, Xy);
							}
						}
						if (move != -1) break;
					}
					if (move != -1) break;
				}
			}
			if (moveO == -1) {
				for (int i = 4; i >= 0; i--) {
					for (int x = 0; x < 5; x++) {
						if (board[i][x] == 'O') {
							Ox = i;
							Oy = x;
							if (board[2][2] == ' ') {
								if (Ox == 1 && Oy == 2 || Ox == 2 && Oy == 1 || Ox == 2 && Oy == 3 || Ox == 3 && Oy == 2) {
									moveO = 13;
								}
							}
							if (moveO == -1) {
								if (Ox > 0 && Oy > 0 && Oy < 4) if (board[Ox - 1][Oy] == ' ' && board[Ox - 1][Oy - 1] == 'O' && board[Ox - 1][Oy + 1] == 'O') moveO = findMove(Ox - 1, Oy);
								if (Ox < 4 && Oy > 0 && Oy < 4) if (board[Ox + 1][Oy] == ' ' && board[Ox + 1][Oy - 1] == 'O' && board[Ox + 1][Oy + 1] == 'O') moveO = findMove(Ox + 1, Oy);
								if (Ox > 0 && Oy > 0 && Ox < 4) if (board[Ox][Oy - 1] == ' ' && board[Ox - 1][Oy - 1] == 'O' && board[Ox + 1][Oy - 1] == 'O') moveO = findMove(Ox, Oy - 1);
								if (Ox > 0 && Oy < 4 && Oy < 4) if (board[Ox][Oy + 1] == ' ' && board[Ox - 1][Oy + 1] == 'O' && board[Ox + 1][Oy + 1] == 'O') moveO = findMove(Ox, Oy + 1);
							}
							if (moveO == -1) {
								if (Oy > 0 && board[Ox][Oy - 1] == ' ') moveO = findMove(Ox, Oy - 1);
								else if (Oy < 4 && board[Ox][Oy + 1] == ' ') moveO = findMove(Ox, Oy + 1);
								else if (Ox > 0 && board[Ox - 1][Oy] == ' ') moveO = findMove(Ox - 1, Oy);
								else if (Ox < 4 && board[Ox + 1][Oy] == ' ') moveO = findMove(Ox + 1, Oy);
							}
						}
						if (moveO != -1) break;
					}
					if (moveO != -1) break;
				}
			}
			if (move != -1) System.out.println(move);
			else System.out.println("LOSE A TURN");
			System.out.println(moveO);
			in.nextLine();
		}
		in.close();
	}

}