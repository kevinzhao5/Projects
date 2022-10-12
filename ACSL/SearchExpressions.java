import java.io.*;
import java.util.*;

public class SearchExpressions {

	public static boolean matchStr(String str, String match) {
		if (match.length() > str.length()) return false;
		for (int i = 0; i <= str.length() - match.length(); i++) {
			if (str.substring(i, match.length() + i).equals(match)) return true;
		}
		return false;
	}
	
	public static boolean matchMid(String str, String match) {
		if (match.length() > str.length()) return false;
		int index = 0;
		for (int i = 0; i < match.length(); i++) {
			if (match.charAt(i) == '?') index = i;
		}
		String first = match.substring(0, index), last = match.substring(index + 1, match.length());
		for (int i = 0; i <= str.length() - match.length(); i++) {
			if (str.substring(i, i + first.length()).equals(first)) {
				if (str.substring(i + first.length() + 1, i + first.length() + 1 + last.length()).equals(last)) return true;
			}
		}
		return false;
	}
	
	public static boolean matchSome(String str, String match) {
		if (match.length() > str.length()) return false;
		int index = 0;
		for (int i = 0; i < match.length(); i++) {
			if (match.charAt(i) == '*') index = i;
		}
		String first = match.substring(0, index), last = match.substring(index + 1, match.length());
		for (int i = 0; i <= str.length() - match.length(); i++) {
			if (str.substring(i, i + first.length()).equals(first)) {
				for (int x = i + first.length(); x <= str.length() - match.length(); x++) {
					if (last.equals(str.substring(x, x + last.length()))) return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/search"));
		in.useDelimiter("\\s");
		String[] strs = new String[10];
		for (int i = 0; i < 10; i++) {
			strs[i] = in.next();
		}
		in.nextLine();
		for (int e = 0; e < 10; e++) {
			String ans = "";
			String op = "", temp = in.nextLine();
			for (int i = 0; i < temp.length(); i++) {
				if (temp.charAt(i) != ' ') op += temp.charAt(i);
			}
			boolean add = false, times = false;
			for (int i = 0; i < op.length(); i++) {
				if (op.charAt(i) == '+') add = true;
				else if (op.charAt(i) == '&') times = true;
			}
			if (!add && !times) {
				char c = ' ';
				for (int i = 0; i < op.length(); i++) {
					if (op.charAt(i) == '[') c = '[';
					else if (op.charAt(i) == ']') c = ']';
					else if (op.charAt(i) == '?') c = '?';
					else if (op.charAt(i) == '*') c = '*';
				}
				if (c == ' ') {
					for (int i = 0; i < 10; i++) {
						if (matchStr(strs[i], op)) ans += strs[i] + ", ";
					}
				} else if (c == ']') {
					for (int i = 0; i < 10; i++) {
						if (op.length() - 1 <= strs[i].length() && strs[i].substring(strs[i].length() - op.length() + 1, strs[i].length()).equals(op.substring(0, op.length() - 1))) ans += strs[i] + ", ";
					}
				} else if (c == '[') {
					for (int i = 0; i < 10; i++) {
						if (op.length() - 1 <= strs[i].length() && strs[i].substring(0, op.length() - 1).equals(op.substring(1, op.length()))) ans += strs[i] + ", ";
					}
				} else if (c == '?') {
					for (int i = 0; i < 10; i++) {
						if (matchMid(strs[i], op)) ans += strs[i] + ", ";
					}
				} else if (c == '*') {
					for (int i = 0; i < 10; i++) {
						if (matchSome(strs[i], op)) ans += strs[i] + ", ";
					}
				}
			} else if (add && times) {
				int indexT = -1, indexP = -1;
				for (int i = 0; i < op.length(); i++) {
					if (op.charAt(i) == '&') indexT = i;
					else if (op.charAt(i) == '+') indexP = i;
				}
				if (indexP > indexT) {
					String T = op.substring(0, indexT), P1 = op.substring(indexT + 1, indexP), P2 = op.substring(indexP + 1, op.length());
					for (int i = 0; i < 10; i++) {
						if ((matchStr(strs[i], P1) || matchStr(strs[i], P2)) && matchStr(strs[i], T)) ans += strs[i] + ", ";
					}
				} else {
					String P1 = op.substring(0, indexP), P2 = op.substring(indexP + 1, indexT), T = op.substring(indexT + 1, op.length());
					for (int i = 0; i < 10; i++) {
						if ((matchStr(strs[i], P1) || matchStr(strs[i], P2)) && matchStr(strs[i], T)) ans += strs[i] + ", ";
					}
				}
			} else if (add && !times) {
				char c = ' ';
				int index = 0, indexC = 0;
				for (int i = 0; i < op.length(); i++) {
					if (op.charAt(i) == '[') {
						c = '[';
						indexC = i;
					}
					else if (op.charAt(i) == ']') {
						c = ']';
						indexC = i;
					}
					else if (op.charAt(i) == '?') {
						c = '?';
						indexC = i;
					}
					else if (op.charAt(i) == '*') {
						indexC = i;
						c = '*';
					}
					else if (op.charAt(i) == '+') index = i;
				}
				String match = "";
				if (indexC < index) {
					match = op.substring(index + 1, op.length());
					op = op.substring(0, index);
				} else {
					match = op.substring(0, index);
					op = op.substring(index + 1, op.length());
				}
				if (c == ' ') {
					for (int i = 0; i < 10; i++) {
						if (matchStr(strs[i], op) || matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == ']') {
					for (int i = 0; i < 10; i++) {
						if ((op.length() - 1 <= strs[i].length() && strs[i].substring(strs[i].length() - op.length() + 1, strs[i].length()).equals(op.substring(0, op.length() - 1))) || matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == '[') {
					for (int i = 0; i < 10; i++) {
						if ((op.length() - 1 <= strs[i].length() && strs[i].substring(0, op.length() - 1).equals(op.substring(1, op.length()))) || matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == '?') {
					for (int i = 0; i < 10; i++) {
						if (matchMid(strs[i], op) || matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == '*') {
					for (int i = 0; i < 10; i++) {
						if (matchSome(strs[i], op) || matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				}
			} else if (!add && times) {
				char c = ' ';
				int index = 0, indexC = 0;
				for (int i = 0; i < op.length(); i++) {
					if (op.charAt(i) == '[') {
						c = '[';
						indexC = i;
					}
					else if (op.charAt(i) == ']') {
						c = ']';
						indexC = i;
					}
					else if (op.charAt(i) == '?') {
						c = '?';
						indexC = i;
					}
					else if (op.charAt(i) == '*') {
						indexC = i;
						c = '*';
					}
					else if (op.charAt(i) == '&') index = i;
				}
				String match = "";
				if (indexC < index) {
					match = op.substring(index + 1, op.length());
					op = op.substring(0, index);
				} else {
					match = op.substring(0, index);
					op = op.substring(index + 1, op.length());
				}
				if (c == ' ') {
					for (int i = 0; i < 10; i++) {
						if (matchStr(strs[i], op) && matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == ']') {
					for (int i = 0; i < 10; i++) {
						if ((op.length() - 1 <= strs[i].length() && strs[i].substring(strs[i].length() - op.length() + 1, strs[i].length()).equals(op.substring(0, op.length() - 1))) && matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == '[') {
					for (int i = 0; i < 10; i++) {
						if ((op.length() - 1 <= strs[i].length() && strs[i].substring(0, op.length() - 1).equals(op.substring(1, op.length()))) && matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == '?') {
					for (int i = 0; i < 10; i++) {
						if (matchMid(strs[i], op) && matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				} else if (c == '*') {
					for (int i = 0; i < 10; i++) {
						if (matchSome(strs[i], op) && matchStr(strs[i], match)) ans += strs[i] + ", ";
					}
				}
			}
			System.out.println(ans.substring(0, ans.length() - 2));
		}
		in.close();
	}

}