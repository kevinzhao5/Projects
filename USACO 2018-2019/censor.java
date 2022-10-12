import java.io.*;

public class censor {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/censor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/censor.out")));
		StringBuilder str = new StringBuilder(in.readLine()), s = new StringBuilder(in.readLine()), ans = new StringBuilder("");
		int[] T = new int[s.length() + 1];
		int pos = 1, cnd = 0, len = s.length(), total = str.length(), j = 0, k = 0, counter = 0;
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
		while (counter < total) {
			char c = str.charAt(counter);
			ans.append(c);
			if (c == s.charAt(k)) {
				k++;
			} else if (c == s.charAt(0)) {
				k = 1;
			} else {
				k = Math.max(T[k], 0);
			}
			if (k == len) {
				ans.delete(ans.length() - len, ans.length());
				if (j >= len) k = kmp[j - len];
				else k = 0;
				j -= len;
			}
			kmp[counter] = k;
			j++;
			counter++;
		}
		out.println(ans);
		in.close();
		out.close();
	}
	
}