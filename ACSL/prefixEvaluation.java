import java.util.*;
import java.io.*;

public class prefixEvaluation {
	
	public static boolean isOperator(String str) {
		if (str.length() > 1) return false;
		char c = str.charAt(0);
		if (c == '|' || c == '+' || c == '-' || c == '*' || c == '@' || c == '>') return true;
		return false;
	}
	
	public static int numOperands(char c) {
		if (c == '|') return 1;
		if (c == '@' || c == '>') return 3;
		return 2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for (int q = 0; q < 5; q++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			ArrayList<String> a = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				a.add(st.nextToken());
			}
			boolean b = true;
			while (b) {
				b = false;
				ArrayList<String> d = new ArrayList<String>();
				int index = 0;
				while (index < a.size()) {
					String s = a.get(index);
					if (isOperator(s)) {
						if (index + numOperands(s.charAt(0)) < a.size()) {
							boolean done = true;
							for (int i = index + 1; i <= index + numOperands(s.charAt(0)); i++) {
								if (isOperator(a.get(i))) {
									done = false;
									break;
								}
							}
							if (done) {
								char c = s.charAt(0);
								switch(c){
								case('|'):
									d.add("" + Math.abs(Integer.parseInt(a.get(index + 1))));
								break;
								case('+'):
									d.add("" + (Integer.parseInt(a.get(index + 1)) + Integer.parseInt(a.get(index + 2))));
								break;
								case('-'):
									d.add("" + (Integer.parseInt(a.get(index + 1)) - Integer.parseInt(a.get(index + 2))));
								break;
								case('*'):
									d.add("" + (Integer.parseInt(a.get(index + 1)) * Integer.parseInt(a.get(index + 2))));
								break;
								case('>'):
									d.add("" + Math.max(Integer.parseInt(a.get(index + 1)), Math.max(Integer.parseInt(a.get(index + 2)), Integer.parseInt(a.get(index + 3)))));
								break;
								case('@'):
									if (Integer.parseInt(a.get(index + 1)) > 0) d.add("" + Integer.parseInt(a.get(index + 2)));
									else d.add("" + Integer.parseInt(a.get(index + 3)));
								}
								b = true;
								index += numOperands(s.charAt(0));
							} else {
								d.add(s);
							}
						} else {
							d.add(s);
						}
					} else {
						d.add(s);
					}
					index++;
				}
				a = d;
			}
			out.println(Integer.parseInt(a.get(0)));
		}
		in.close();
		out.close();
	}

}
