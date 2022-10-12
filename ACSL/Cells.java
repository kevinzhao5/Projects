import java.util.*;
import java.io.*;

public class Cells {

	public static String alphOrder(String str) {
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		String re = "";
		for (int i = 0; i < chars.length; i++) {
			re += chars[i];
		}
		return re;
	}
	
	public static String div(String str) {
		return str.substring(0, 4) + str.substring(0, 4) + " and " + str.substring(4, 8) + str.substring(4, 8);
	}
	
	public static String add(String str, int n) {
		return str.substring(0, n) + str.substring(0, n) + str.substring(n, 8 - n);
	}
	
	public static String sub(String str, int n) {
		return str.substring(n, 8) + alphOrder(str.substring(8 - n, 8));
	}
	
	public static String ali(String str) {
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		String re = "";
		for (int i = chars.length - 1; i >= 0; i--) {
			re += chars[i];
		}
		return re;
	}
	
	public static String uni(String str1, String str2) {
		return alphOrder(str1.substring(4, 8)) + alphOrder(str2.substring(0, 4));
	}
	
	public static String intersect(String str1, String str2) {
		return alphOrder(str1.substring(0, 2) + str1.substring(6, 8)) + alphOrder(str2.substring(0, 2) + str2.substring(6, 8));
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/cells"));
		in.useDelimiter("\\s");
		String x = in.nextLine(), y = in.nextLine(), z = in.nextLine();
		for (int e = 0; e < 10; e++) {
			String ln = in.nextLine();
			Scanner in1 = new Scanner(ln);
			in1.useDelimiter("\\s");
			ArrayList<String> ops = new ArrayList<String>();
			ArrayList<String> vars = new ArrayList<String>();
			while (in1.hasNext()) {
				String temp = in1.next();
				if (temp.length() == 1) {
					switch(temp) {
					case("X"):vars.add(x); break;
					case("Y"):vars.add(y); break;
					case("Z"):vars.add(z); break;
					}
					if (ops.size() > 0) {
						temp = ops.get(ops.size() - 1);
						boolean check = false;
						switch(temp.substring(0, 3)) {
						case("DIV"):vars.add(div(vars.remove(vars.size() - 1))); check = true; break;
						case("ADD"):vars.add(add(vars.remove(vars.size() - 1), (int) (temp.charAt(3)) - 48)); check = true; break;
						case("SUB"):vars.add(sub(vars.remove(vars.size() - 1), (int) (temp.charAt(3)) - 48)); check = true; break;
						case("ALI"):vars.add(ali(vars.remove(vars.size() - 1))); check = true; break;
						}
						if (check) {
							while (check) {
								ops.remove(ops.size() - 1);
								check = false;
								if (ops.size() > 0) {
									temp = ops.get(ops.size() - 1);
									switch(temp.substring(0, 3)) {
									case("DIV"):vars.add(div(vars.remove(vars.size() - 1))); check = true; break;
									case("ADD"):vars.add(add(vars.remove(vars.size() - 1), (int) (temp.charAt(3)) - 48)); check = true; break;
									case("SUB"):vars.add(sub(vars.remove(vars.size() - 1), (int) (temp.charAt(3)) - 48)); check = true; break;
									case("ALI"):vars.add(ali(vars.remove(vars.size() - 1))); check = true; break;
									}
								}
							}
						}
					}
				} else ops.add(temp);
			}
			while (ops.size() > 0) {
				String op = ops.remove(ops.size() - 1);
				if (op.equals("UNI")) vars.add(uni(vars.remove(vars.size() - 2), vars.remove(vars.size() - 1)));
				else if (op.equals("INT")) vars.add(intersect(vars.remove(vars.size() - 2), vars.remove(vars.size() - 1)));
				else if (op.equals("DIV")) vars.add(div(vars.remove(vars.size() - 1)));
				else if (op.substring(0, 3).equals("ADD")) vars.add(add(vars.remove(vars.size() - 1), (int) (op.charAt(3)) - 48));
				else if (op.substring(0, 3).equals("SUB")) vars.add(sub(vars.remove(vars.size() - 1), (int) (op.charAt(3)) - 48));
				else if (op.equals("ALI")) vars.add(ali(vars.remove(vars.size() - 1)));
			}
			System.out.println(vars.get(0));
			in1.close();
		}
		in.close();
	}

}