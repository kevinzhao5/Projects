import java.util.*;
import java.io.*;

public class Str {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\\s");
		for (int e = 0; e < 10; e++) {
			StringTokenizer st = new StringTokenizer(in.nextLine());
			String str = st.nextToken();
			int len = 10, dec = 0;
			if (st.hasMoreTokens()) {
				len = Integer.parseInt(st.nextToken());
				if (st.hasMoreTokens()) {
					dec = Integer.parseInt(st.nextToken());
				}
			}
			String[] parts = str.split("\\.");
			if (parts.length == 1 || dec == 0) {
				if (parts.length == 2) parts[0] = Integer.toString((int) (Math.round(Double.parseDouble(parts[0] + "." + parts[1]))));
				String ans = parts[0];
				if (dec > 0) {
					ans = ans + ".";
					for (int i = 0; i < dec; i++) {
						ans += "0";
					}
				}
				if (ans.length() > len) {
					ans = "";
					if (dec == 0) {
						for (int i = 0; i < len; i++) {
							ans += "#";
						}
						System.out.println(ans);
					} else {
						if (dec + 1 > len) System.out.println("ERROR");
						else {
							String output = "";
							for (int i = 0; i < len - 1; i++) {
								output += "#";
							}
							output = output.substring(0, len - dec - 1) + "." + output.substring(len - dec - 1);
							System.out.println(output);
						}
					}
				} else {
					int n = ans.length();
					for (int i = 0; i < len - n; i++) {
						ans = "#" + ans;
					}
					System.out.println(ans);
				}
			} else {
				if (dec + parts[0].length() + 1 > len) {
					if (dec + 1 > len) System.out.println("ERROR");
					else {
						String output = "";
						for (int i = 0; i < len - 1; i++) {
							output += "#";
						}
						output = output.substring(0, len - dec - 1) + "." + output.substring(len - dec - 1);
						System.out.println(output);
					}
				} else {
					String ans = parts[0] + ".";
					if (parts[1].length() > dec) {
						double x = Double.parseDouble(parts[0] + "." + parts[1]);
						x *= Math.pow(10, dec) + 0.00001;
						double y = Math.round(x);
						ans = Double.toString(y / Math.pow(10, dec));
						if (ans.length() < len) {
							int n = ans.length();
							for (int i = 0; i < len - n; i++) {
								ans = "#" + ans;
							}
						}
						System.out.println(ans);
					} else {
						while (parts[1].length() < dec) parts[1] += "0";
						ans += parts[1];
						if (ans.length() < len) {
							int n = ans.length();
							for (int i = 0; i < len - n; i++) {
								ans = "#" + ans;
							}
						}
						System.out.println(ans);
					}
				}
			}
		}
		in.close();
	}

}


/*
123
123.5
123.5 5 1
123.456 7 3
123.456 7 1
123.456 7 0
123.456 7 8
123.456 10 2
123.45 7 3
-123.45 7 1
*/