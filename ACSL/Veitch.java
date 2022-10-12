/* Kevin Zhao
 * Legion of Learners
 * Senior Division
 * Contest #3 2019-2020
 */

import java.util.*;
import java.io.*;

public class Veitch {
	
	static class term {
		
		TreeSet<String> t;
		
		public term() {
			t = new TreeSet<String>();
		}
		
		public void add(String[] strs) {
			for (int i = 0; i < strs.length; i++) {
				t.add(strs[i]);
			}
		}
		
		public String str() {
			int[] count = new int[4];
			if (t.contains("~A")) count[0]++;
			if (t.contains("A")) count[0]++;
			if (t.contains("~B")) count[1]++;
			if (t.contains("B")) count[1]++;
			if (t.contains("~C")) count[2]++;
			if (t.contains("C")) count[2]++;
			if (t.contains("~D")) count[3]++;
			if (t.contains("D")) count[3]++;
			String ret = "";
			for (int i = 0; i < 4; i++) {
				if (count[i] < 2) {
					switch(i) {
					case(0): {
						if (t.contains("~A")) ret += "~A";
						else ret += "A";
						break;
					}
					case(1): {
						if (t.contains("~B")) ret += "~B";
						else ret += "B";
						break;
					}
					case(2): {
						if (t.contains("~C")) ret += "~C";
						else ret += "C";
						break;
					}
					case(3): {
						if (t.contains("~D")) ret += "~D";
						else ret += "D";
						break;
					}
					}
				}
			}
			return ret;
		}
		
	}
	
	static String[] hex = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111" };
	static String[][] col = { { "A", "~C" }, { "A", "C" }, { "~A", "C" }, { "~A", "~C" } };
	static String[][] row = { { "B", "~D" }, { "B", "D" }, { "~B", "D" }, { "~B", "~D" } };
	static String[][][] coeff = new String[4][4][4];
	static boolean[][] arr;
	static boolean[][] v;
	static ArrayList<term> a;
	
	public static void one() {
		for (int i = 0; i < 3; i++) {
			boolean b = true;
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					if (v[i + j][k] || !arr[i + j][k]) b = false;
				}
			}
			if (b) {
				term t = new term();
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 4; k++) {
						v[i + j][k] = true;
						t.add(coeff[i + j][k]);
					}
				}
				a.add(t);
			}
		}
		for (int i = 0; i < 3; i++) {
			boolean b = true;
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					if (v[k][i + j] || !arr[k][i + j]) b = false;
				}
			}
			if (b) {
				term t = new term();
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 4; k++) {
						v[k][i + j] = true;
						t.add(coeff[k][i + j]);
					}
				}
				a.add(t);
			}
		}
		boolean b = true;
		for (int k = 0; k < 4; k++) {
			if (v[0][k] || !arr[0][k] || v[3][k] || !arr[3][k]) b = false;
		}
		if (b) {
			term t = new term();
			for (int k = 0; k < 4; k++) {
				v[0][k] = true;
				v[3][k] = true;
				t.add(coeff[0][k]);
				t.add(coeff[3][k]);
			}
			a.add(t);
		}
		b = true;
		for (int k = 0; k < 4; k++) {
			if (v[k][0] || !arr[k][0] || v[k][3] || !arr[k][3]) b = false;
		}
		if (b) {
			term t = new term();
			for (int k = 0; k < 4; k++) {
				v[k][0] = true;
				v[k][3] = true;
				t.add(coeff[k][0]);
				t.add(coeff[k][3]);
			}
			a.add(t);
		}
	}
	

	public static void two() {
		for (int i = 0; i < 4; i++) {
			boolean b = true;
			for (int j = 0; j < 4; j++) {
				if (v[i][j] || !arr[i][j]) b = false;
			}
			if (b) {
				term t = new term();
				for (int j = 0; j < 4; j++) {
					v[i][j] = true;
					t.add(coeff[i][j]);
				}
				a.add(t);
			}
		}
		for (int i = 0; i < 4; i++) {
			boolean b = true;
			for (int j = 0; j < 4; j++) {
				if (v[j][i] || !arr[j][i]) b = false;
			}
			if (b) {
				term t = new term();
				for (int j = 0; j < 4; j++) {
					v[j][i] = true;
					t.add(coeff[j][i]);
				}
				a.add(t);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!v[i][j] && !v[i + 1][j] &&!v[i][j + 1] && !v[i + 1][j + 1] && arr[i][j] && arr[i + 1][j] && arr[i][j + 1] && arr[i + 1][j + 1]) {
					term t = new term();
					v[i][j] = true;
					v[i + 1][j] = true;
					v[i][j + 1] = true;
					v[i + 1][j + 1] = true;
					t.add(coeff[i][j]);
					t.add(coeff[i + 1][j]);
					t.add(coeff[i][j + 1]);
					t.add(coeff[i + 1][j + 1]);
					a.add(t);
				}
			}
		}
	}
	

	public static void three() {
		for (int i = 0; i < 3; i++) {
			if (!v[i][0] && !v[i + 1][0] && !v[i][3] && !v[i + 1][3] && arr[i][0] && arr[i + 1][0] && arr[i][3] && arr[i + 1][3]) {
				term t = new term();
				t.add(coeff[i][0]);
				t.add(coeff[i + 1][0]);
				t.add(coeff[i][3]);
				t.add(coeff[i + 1][3]);
				v[i][0] = true;
				v[i + 1][0] = true;
				v[i][3] = true;
				v[i + 1][3] = true;
				a.add(t);
			}
		}
		for (int i = 0; i < 3; i++) {
			if (!v[0][i] && !v[0][i + 1] && !v[3][i] && !v[3][i + 1] && arr[0][i] && arr[0][i + 1] && arr[3][i] && arr[3][i + 1]) {
				term t = new term();
				t.add(coeff[0][i]);
				t.add(coeff[0][i + 1]);
				t.add(coeff[3][i]);
				t.add(coeff[3][i + 1]);
				v[0][i] = true;
				v[0][i + 1] = true;
				v[3][i] = true;
				v[3][i + 1] = true;
				a.add(t);
			}
		}
	}
	

	public static void four() {
		if (!v[0][0] && !v[0][3] && !v[3][0] && !v[3][3] && arr[0][0] && arr[0][3] && arr[3][0] && arr[3][3]) {
			term t = new term();
			t.add(coeff[0][0]);
			t.add(coeff[0][3]);
			t.add(coeff[3][0]);
			t.add(coeff[3][3]);
			v[0][0] = true;
			v[0][3] = true;
			v[3][0] = true;
			v[3][3] = true;
			a.add(t);
		}
	}
	

	public static void five() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (!v[i][j] && !v[i][j + 1] && arr[i][j] && arr[i][j + 1]) {
					term t = new term();
					t.add(coeff[i][j]);
					t.add(coeff[i][j + 1]);
					v[i][j] = true;
					v[i][j + 1] = true;
					a.add(t);
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (!v[j][i] && !v[j + 1][i] && arr[j][i] && arr[j + 1][i]) {
					term t = new term();
					t.add(coeff[j][i]);
					t.add(coeff[j + 1][i]);
					v[j][i] = true;
					v[j + 1][i] = true;
					a.add(t);
				}
			}
		}
	}
	

	public static void six() {
		for (int i = 0; i < 4; i++) {
			if (!v[i][0] && !v[i][3] && arr[i][0] && arr[i][3]) {
				term t = new term();
				t.add(coeff[i][0]);
				t.add(coeff[i][3]);
				v[i][0] = true;
				v[i][3] = true;
				a.add(t);
			}
		}
		for (int i = 0; i < 4; i++) {
			if (!v[0][i] && !v[3][i] && arr[0][i] && arr[3][i]) {
				term t = new term();
				t.add(coeff[0][i]);
				t.add(coeff[3][i]);
				v[0][i] = true;
				v[3][i] = true;
				a.add(t);
			}
		}
	}
	

	public static void seven() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j] && !v[i][j]) {
					term t = new term();
					t.add(coeff[i][j]);
					a.add(t);
				}
			}
		}
	}
	

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/Veitch"));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				coeff[i][j][0] = col[j][0];
				coeff[i][j][1] = col[j][1];
				coeff[i][j][2] = row[i][0];
				coeff[i][j][3] = row[i][1];
			}
		}
		for (int q = 0; q < 5; q++) {
			String s = in.readLine();
			arr = new boolean[4][4];
			for (int i = 0; i < 4; i++) {
				char c = s.charAt(i);
				int x = (int)c;
				if (x >= 65) x -= 55;
				else x -= 48;
				String temp = hex[x];
				for (int j = 0; j < 4; j++) {
					if (temp.charAt(j) == '1') arr[i][j] = true;
					else arr[i][j] = false;
				}
			}
			v = new boolean[4][4];
			a = new ArrayList<term>();
			one();
			two();
			three();
			four();
			five();
			six();
			seven();
			int len = a.size();
			for (int i = 0; i < len - 1; i++) {
				System.out.print(a.get(i).str() + "+");
			}
			if (len > 0) System.out.println(a.get(len - 1).str());
		}
		in.close();
	}

}
