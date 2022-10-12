import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class censor {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("USACO3/censor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("USACO3/censor.out")));
		StringBuilder str = new StringBuilder(in.readLine()), s = new StringBuilder(in.readLine()), ans = new StringBuilder("");
		int[] T = new int[s.length() + 1];
		int pos = 1, cnd = 0, len = s.length(), total = str.length(), j = 0, k = 0;
		T[0] = -1;
		while (pos < len) {
			if (s.charAt(pos) == s.charAt(cnd)) {
				T[pos] = T[cnd];
				pos++;
				cnd++;
			} else {
				T[pos] = cnd;
				cnd = T[cnd];
				while (cnd >= 0 && s.charAt(pos) != s.charAt(cnd)) {
					cnd = T[cnd];
				}
				cnd++;
				pos++;
			}
		}
		T[pos] = cnd;
		int[] kmp = new int[str.length()];
		boolean check = true;
		while (j < total) {
			kmp[j] = k;
			char c = str.charAt(j);
			if (check) ans.append(c);
			check = true;
			if (c == s.charAt(k)) {
				j++;
				k++;
				if (k == len) {
					ans.delete(ans.length() - len, ans.length());
					k = kmp[j - len];
				}
			} else {
				k = T[k];
				check = false;
				if (k < 0) {
					j++;
					k++;
					check = true;
				}
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}