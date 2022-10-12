import java.io.*;
import java.util.*;

public class HexFractions {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/hex"));
		in.useDelimiter("\\s");
		for (int t = 0; t < 10; t++) {
			String nstr = in.nextLine();
			nstr = nstr.substring(1, nstr.length());
			int len = nstr.length(), n = Integer.parseInt(nstr);
			String frac = "";
			for (int i = 0; i < 15; i++) {
				n *= 16;
				int temp = (int) (n / Math.pow(10, len)) + 48;
				char c = ' ';
				switch(temp) {
				case(58):c = 'A'; break;
				case(59):c = 'B'; break;
				case(60):c = 'C'; break;
				case(61):c = 'D'; break;
				case(62):c = 'E'; break;
				case(63):c = 'F'; break;
				default:c = (char) (temp);
				}
				frac += c;
				if (n % Math.pow(10, len) == 0) break;
				n %= Math.pow(10, len);
			}
			len = frac.length();
			int repeat = 0, counter = 0, len2 = 0;
			String re = "";
			for (int x = len; x >= len / 2; x--) {
				boolean check2 = true;
				for (int i = 1; i <= x / 2; i++) {
					re = frac.substring(x - i, x);
					boolean check = true;
					if (!re.equals(frac.substring(x - 2 * i, x - i))) check = false;
					if (check) {
						repeat = i;
						len2 = x;
						check2 = false;
						break;
					}
				}
				if (!check2) break;
			}
			if (repeat == 0) System.out.println("." + frac.substring(0, Math.min(frac.length(), 10)));
			else {
				while (len2 - repeat * (counter + 1) > 0 && frac.substring(len2 - repeat * (counter + 1), len2 - repeat * counter).equals(re)) counter++;
				if (repeat == 1) System.out.println("." + frac.substring(0, len2 - counter * repeat + 1));
				else System.out.println("." + frac.substring(0, len2 - counter * repeat));
			}
		}
		in.close();
	}

}