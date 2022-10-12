import java.util.*;
import java.io.*;

public class matchingTrees {
	
	static String match, tree;
	static char[] matchTree;
	static char[] bigTree;
	
	public static void addMatch(int index, char c) {
		if (matchTree[index] == ' ') {
			matchTree[index] = c;
		} else {
			if (matchTree[index] < c) {
				addMatch(index * 2 + 1, c);
			} else {
				addMatch(index * 2, c);
			}
		}
	}
	
	public static void addBig(int index, char c) {
		if (bigTree[index] == ' ') {
			bigTree[index] = c;
		} else {
			if (bigTree[index] < c) {
				addBig(index * 2 + 1, c);
			} else {
				addBig(index * 2, c);
			}
		}
	}
	
	public static boolean fits(int indexMatch, int indexBig) {
		if (matchTree[indexMatch] == ' ') return true;
		if (bigTree[indexBig] == ' ') return false;
		return fits(indexMatch * 2, indexBig * 2) && fits(indexMatch * 2 + 1, indexBig * 2 + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/matchingTrees"));
		for (int q = 0; q < 10; q++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			String match = st.nextToken(", "), tree = st.nextToken();
			matchTree = new char[Math.min((int) (Math.pow(2, match.length() + 1)), 8000000) + 1];
			bigTree = new char[Math.min((int) (Math.pow(2, tree.length() + 1)), 8000000) + 1];
			Arrays.fill(matchTree, ' ');
			Arrays.fill(bigTree, ' ');
			for (int i = 0; i < match.length(); i++) {
				addMatch(1, match.charAt(i));
			}
			for (int i = 0; i < tree.length(); i++) {
				addBig(1, tree.charAt(i));
			}
			ArrayList<Character> a = new ArrayList<Character>();
			for (int i = 1; i < bigTree.length; i++) {
				if (bigTree[i] == ' ') continue;
				if (fits(1, i)) {
					a.add(bigTree[i]);
				}
			}
			if (a.size() == 0) {
				System.out.println("NONE");
			} else {
				for (int i = 0; i < a.size() - 1; i++) {
					System.out.print(a.get(i) + ", ");
				}
				System.out.println(a.get(a.size() - 1));
			}
		}
		in.close();
	}

}