import java.util.*;
import java.io.*;

class radical {
	
	int num = 0, mult = 0, rad = 0;
	boolean imag = false;
	
	public radical(int a, int b, int c) {
		num = a;
		mult = b;
		rad = c;
	}
	
	public void simplify() {
		if (rad == 0 || mult == 0) {
			rad = 0;
			mult = 0;
			return;
		}
		if (rad < 0) {
			rad *= -1;
			imag = true;
		}
		int i = 2;
		while (i * i <= rad) {
			if (rad % (i * i) == 0) {
				rad /= i * i;
				mult *= i;
			} else i++;
		}
		if (rad == 1) {
			if (num != 0) {
				num += mult;
				mult = 0;
			}
			rad = 0;
		}
	}
	
	public void add(radical radi) {
		num += radi.num;
		mult += radi.mult;
		
	}
	
	public void sub(radical radi) {
		num -= radi.num;
		mult -= radi.mult;
	}
	
	public void mult(radical radi) {
		radical old = new radical(num, mult, rad);
		num *= radi.num;
		num += old.mult * radi.mult * old.rad;
		mult = old.num * radi.mult + old.mult * radi.num;
	}
	
	public void div(radical radi) {
		mult(new radical(radi.num, radi.mult * -1, radi.rad));
		int temp = radi.num * radi.num - radi.mult * radi.mult * radi.rad;
		num /= temp;
		mult /= temp;
	}
	
}

public class Radicals {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/Radical"));
		in.useDelimiter("\\s");
		for (int i = 0; i < 5; i++) {
			radical radi = new radical(Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()));
			radi.simplify();
			String str = "";
			if (radi.imag) str = "i";
			System.out.println(radi.num + ", " + radi.mult + str + ", " + radi.rad);
			in.nextLine();
		}
		for (int i = 0; i < 5; i++) {
			radical radi = new radical(Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()));
			radical radi1 = new radical(Integer.parseInt(in.next()), Integer.parseInt(in.next()), Integer.parseInt(in.next()));
			char c = in.next().charAt(0);
			radi.simplify();
			radi1.simplify();
			if (c == '+') radi.add(radi1);
			if (c == '-') radi.sub(radi1);
			if (c == '*') radi.mult(radi1);
			if (c == '/') radi.div(radi1);
			String str = "";
			if (radi.imag) str = "i";
			System.out.println(radi.num + ", " + radi.mult + str + ", " + radi.rad);
			in.nextLine();
		}
		in.close();
	}

}
