import java.io.*;
import java.util.*;

class tr implements Comparable<tr> {
	
	char a, b, c;
	
	public tr(char x, char y, char z) {
		a = x;
		b = y;
		c = z;
	}

	@Override
	public int compareTo(tr o) {
		if (this.a == o.a) {
			if (this.b == o.b) return this.c - o.c;
			return this.b - o.b;
		}
		return this.a - o.a;
	}
	
}

public class cownomics {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		char[][] spotty = new char[N][M];
		char[][] plain = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < M; j++) {
				spotty[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < M; j++) {
				plain[i][j] = str.charAt(j);
			}
		}
		int count = 0;
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					TreeSet<tr> t = new TreeSet<tr>();
					for (int l = 0; l < N; l++) {
						t.add(new tr(spotty[l][i], spotty[l][j], spotty[l][k]));
					}
					boolean c = false;
					for (int l = 0; l < N; l++) {
						if (t.contains(new tr(plain[l][i], plain[l][j], plain[l][k]))) {
							c = true;
							break;
						}
					}
					if (!c) count++;
				}
			}
		}
		out.println(count);
		in.close();
		out.close();
	}
	
}