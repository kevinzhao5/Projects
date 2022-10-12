import java.util.*;
import java.io.*;
public class Repeat {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/repeat"));
		in.useDelimiter("\\s|\\s,");
		for (int q = 0; q < 10; q++) {
			String Nstr = in.next();
			double N = Double.parseDouble(Nstr);
			Nstr = Nstr.substring(2, Nstr.length());
			in.nextLine();
			int len = Nstr.length(), max = len / 3, index = 0;
			for (int i = 1; i <= max; i++) {
				String re = Nstr.substring(len - i, len);
				boolean check = true;
				for (int x = 1; x <= 2; x++) {
					if (!re.equals(Nstr.substring(len - i - i * x, len - i * x))) check = false;
				}
				if (check) {
					index = i;
					break;
				}
			}
			int n = index, x = len - index * 3, den = (int) (Math.pow(10, n + x) - Math.pow(10, x)), num = (int) Math.ceil(Math.pow(10, n + x) * N - Math.pow(10, x) * N), tempDen = den;
			for (int i = 2; i <= Math.sqrt(tempDen); i++) {
				if (den % i == 0 && num % i == 0) {
					den /= i;
					num /= i;
					i--;
					continue;
				}
			}
			System.out.println(num + " / " + den);
		}
		in.close();
	}

}