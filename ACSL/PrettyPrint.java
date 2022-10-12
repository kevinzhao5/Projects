import java.util.*;
import java.io.*;

class tri {
	
	String a, b, c;
	
	public tri(String aa, String bb, String cc) {
		a = aa;
		b = bb;
		c = cc;
	}
	
}

public class PrettyPrint {
	
	static String functions = " LOAD STORE ADD SUB MULT DIV BG BE BL BU READ PRINT DC END ";
	
	public static boolean isFunction(String s) {
		return functions.indexOf(s) >= 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/as5-test.txt"));
		for (int w = 0; w < 10; w++) {
			int k = Integer.parseInt(in.readLine()) - 1;
			ArrayList<String> a = new ArrayList<String>();
			String line = in.readLine();
			while (!line.equals("")) {
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					a.add(st.nextToken());
				}
				if (in.ready()) line = in.readLine();
				else line = "";
			}
			int maxVarLength = 0, maxFunctLength = 0;
			ArrayList<String> vars = new ArrayList<String>();
			ArrayList<tri> lines = new ArrayList<tri>();
			for (int i = 0; i < a.size(); i++) {
				String s = a.get(i).toUpperCase();
				if (isFunction(" " + s + " ")) {
					maxFunctLength = Math.max(maxFunctLength, s.length());
					String aa = "", b = "", c = "";
					b = s;
					int coun = 1;
					if (i > 0) {
						aa = a.get(0);
						coun++;
					}
					if (i < a.size() - 1 && !isFunction(" " + a.get(i + 1).toUpperCase() + " ")) {
						c = a.get(i + 1);
						coun++;
					}
					for (int r = 0; r < coun; r++) a.remove(0);
					i = -1;
					lines.add(new tri(aa, b, c));
				} else {
					vars.add(a.get(i));
					maxVarLength = Math.max(maxVarLength, a.get(i).length());
				}
			}
			tri t = lines.get(k);
			if (t.b.equals("END")) System.out.println(t.a + (maxVarLength - t.a.length() + 1) + t.b);
			else System.out.println(t.a + (maxVarLength - t.a.length() + 1) + t.b + (maxFunctLength - t.b.length() + 1) + t.c);
		}
		in.close();
	}

}
