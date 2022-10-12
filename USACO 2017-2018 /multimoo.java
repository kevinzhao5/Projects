/*
ID: awesome25
*/
import java.io.*;
import java.util.*;

class sqr {
	
	int val, comp;
	
	public sqr(int val2) {
		val = val2;
		comp = -2;
	}
	
}

class var {
	
	int x, q;
	
	public var(int x2, int q2) {
		x = x2;
		q = q2;
	}
	
}

public class multimoo {
	
	static sqr[][] board;
	
	public static int find(int i, int x, int index) {
		int result = 1;
		board[i][x].comp = index;
		if (i > 0) if (board[i - 1][x].comp == -2 && board[i - 1][x].val == board[i][x].val) result += find(i - 1, x, index);
		if (x > 0) if (board[i][x - 1].comp == -2 && board[i][x - 1].val == board[i][x].val) result += find(i, x - 1, index);
		if (i < board.length - 1) if (board[i + 1][x].comp == -2 && board[i + 1][x].val == board[i][x].val) result += find(i + 1, x, index);
		if (x < board.length - 1) if (board[i][x + 1].comp == -2 && board[i][x + 1].val == board[i][x].val) result += find(i, x + 1, index);
		return result;
	}
	
	public static int find(sqr[][] board, int i, int x, int index, int or) {
		int result = 1;
		board[i][x].comp = index;
		if (i > 0) if (board[i - 1][x].comp == -2 && (board[i - 1][x].val == index || board[i - 1][x].val == or)) result += find(board, i - 1, x, index, or);
		if (x > 0) if (board[i][x - 1].comp == -2 && (board[i][x - 1].val == index || board[i][x - 1].val == or)) result += find(board, i, x - 1, index, or);
		if (i < board.length - 1) if (board[i + 1][x].comp == -2 && (board[i + 1][x].val == index || board[i + 1][x].val == or)) result += find(board, i + 1, x, index, or);
		if (x < board.length - 1) if (board[i][x + 1].comp == -2 && (board[i][x + 1].val == index || board[i][x + 1].val == or)) result += find(board, i, x + 1, index, or);
		return result;
	}
	
	public static boolean isIn(int n, ArrayList<Integer> used) {
		for (int i = 0; i < used.size(); i++) {
			if (n == used.get(i)) return true;
		}
		return false;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("multimoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
		int len = Integer.parseInt(in.readLine());
		board = new sqr[len][len];
		sqr[][] tempBoard = new sqr[len][len];
		boolean[] checks = new boolean[1000000];
		ArrayList<Integer> vals = new ArrayList<Integer>();
		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int x = 0; x < len; x++) {
				board[i][x] = new sqr(Integer.parseInt(st.nextToken()));
				tempBoard[i][x] = board[i][x];
				if (!checks[board[i][x].val]) {
					checks[board[i][x].val] = true;
					vals.add(board[i][x].val);
				}
			}
		}
		ArrayList<Integer> comps = new ArrayList<Integer>();
		for (int i = 0; i < len; i++) {
			for (int x = 0; x < len; x++) {
				if (board[i][x].comp == -2) {
					comps.add(find(i, x, board[i][x].val));
				}
			}
		}
		int largest = 0;
		for (int i = 0; i < comps.size(); i++) {
			largest = Math.max(largest, comps.get(i));
		}
		out.println(largest);
		for (int w = 0; w < len; w++) {
			for (int q = 0; q < len; q++) {
				tempBoard[w][q].comp = -2;
			}
		}
		ArrayList<Integer> areas = new ArrayList<Integer>();
		for (int i = 0; i < vals.size(); i++) {
			for (int w = i + 1; w < vals.size(); w++) {
			label:
			for (int e = 0; e < len; e++) {
				for (int x = 0; x < len; x++) {
					if (tempBoard[e][x].val == vals.get(w)) {
						areas.add(find(tempBoard, e, x, vals.get(w), vals.get(i)));
						for (int r = 0; r < len; r++) {
							for (int q = 0; q < len; q++) {
								tempBoard[r][q].comp = -2;
							}
						}
						break label;
					}
				}
			}
			}
		}
		for (int e = 0; e < areas.size(); e++) {
			largest = Math.max(largest, areas.get(e));
		}
		out.println(largest);
		in.close();
		out.close();
	}
}