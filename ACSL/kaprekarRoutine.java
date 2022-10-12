import java.util.*;
import java.io.*;

public class kaprekarRoutine {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/kaprekar"));
		for (int q = 0; q < 10; q++) {
			String num = in.readLine();
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			map.put(num, -1);
			for (int i = 0; i > -1; i++) {
				char[] chars = new char[num.length()];
				for (int j = 0; j < num.length(); j++) {
					chars[j] = num.charAt(j);
				}
				Arrays.sort(chars);
				String big = "", small = "";
				for (int j = 0; j < chars.length; j++) {
					big = chars[j] + big;
					small += chars[j];
				}
				int bigi = Integer.parseInt(big, 16), smalli = Integer.parseInt(small, 16), diff = bigi - smalli;
				if (diff == 0) {
					System.out.println("ZERO, " + (i + 1));
					break;
				}
				num = Integer.toHexString(diff);
				num = num.toUpperCase();
				if (map.containsKey(num)) {
					if (i - map.get(num) == 1) System.out.println("CONSTANT, " + num);
					else System.out.println("CYCLE, " + (i - map.get(num)));
					break;
				}
				map.put(num, i);
			}
		}
		in.close();
	}

}
