import java.util.*;
import java.io.*;

public class TicTacLogic {
	
	static char[][] b;
	static int N;
	static boolean complete = false;
	
	public static boolean same(char[] a1, char[] a2) {
		for (int i = 0; i < N; i++) {
			if (a1[i] != a2[i]) return false;
		}
		return true;
	}
	
	public static char[] toArr(int col) {
		char[] nums = new char[N];
		for (int i = 0; i < N; i++) {
			nums[i] = b[i][col];
		}
		return nums;
	}
	
	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(b[i]));
		}
		System.out.println();
	}
	
	public static void fill(int row, int col) {
		if (row >= N) {
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (same(b[i], b[j])) {
						return;
					}
					if (same(toArr(i), toArr(j))) {
						return;
					}
				}
			}
			complete = true;
		} else {
			if (col >= N) {
				col = 0;
				row++;
				if (row >= N) {
					fill(row, col);
					return;
				}
			}
			while (b[row][col] != '-') {
				col++;
				if (col >= N) {
					col = 0;
					row++;
				}
				if (row >= N) {
					fill(row, col);
					return;
				}
			}
			int rowX = 0, colX = 0, rowO = 0, colO = 0;
			for (int i = 0; i < N; i++) {
				if (b[i][col] == 'X') colX++;
				else if (b[i][col] == 'O') colO++;
				if (b[row][i] == 'X') rowX++;
				else if (b[row][i] == 'O') rowO++;
			}
			if (rowX < N / 2 && colX < N / 2) {
				b[row][col] = 'X';
				boolean check = true;
				for (int i = 0; i < N - 2; i++) {
					if (b[row][i] == b[row][i + 1] && b[row][i] == b[row][i + 2] && b[row][i] != '-') {
						check = false;
						break;
					}
					if (b[i][col] == b[i + 1][col] && b[i][col] == b[i + 2][col] && b[i][col] != '-') {
						check = false;
						break;
					}
				}
				if (check) fill(row, col + 1);
				if (complete) return;
				b[row][col] = '-';
			}
			if (rowO < N / 2 && colO < N / 2) {
				b[row][col] = 'O';
				boolean check = true;
				for (int i = 0; i < N - 2; i++) {
					if (b[row][i] == b[row][i + 1] && b[row][i] == b[row][i + 2] && b[row][i] != '-') {
						check = false;
						break;
					}
					if (b[i][col] == b[i + 1][col] && b[i][col] == b[i + 2][col] && b[i][col] != '-') {
						check = false;
						break;
					}
				}
				if (check) fill(row, col + 1);
				if (complete) return;
				b[row][col] = '-';
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/as6-test.txt"));
		String line = in.readLine();
		for (int w = 0; w < 10; w++) {
			complete = false;
			N = 100;
			String s = "empty";
			while (N == 100 || s.equals("empty")) {
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					if (N == 100) {
						N = Integer.parseInt(st.nextToken());
					} else if (s.equals("empty")) {
						s = st.nextToken();
					}
				}
				line = in.readLine();
			}
			b = new char[N][N];
			int zeroes = 0, index = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (zeroes == 0) {
						char c = s.charAt(index);
						index++;
						if (c == 'X') {
							b[i][j] = 'X';
						}
						else if (c == 'O') {
							b[i][j] = 'O';
						}
						else {
							int x = (int) (c) - 48;
							while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
								x = x * 10 + (int) (s.charAt(index)) - 48;
								index++;
							}
							zeroes = x;
						}
					}
					if (zeroes > 0) {
						b[i][j] = '-';
						zeroes--;
					}
				}
			}
			fill(0, 0);
			String bin = "";
			for (int i = 0; i < N; i++) {
				if (b[i][i] == 'X') bin += "1";
				else bin += "0";
			}
			int n = Integer.parseInt(bin, 2);
			System.out.println(Integer.toHexString(n).toUpperCase());
		}
		in.close();
	}

}
