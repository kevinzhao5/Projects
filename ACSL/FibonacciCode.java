import java.util.*;
import java.io.*;
public class FibonacciCode {
	
	public static String toHex(String bin) {
		String hex = "";
		int i = 0;
		for (i = bin.length(); i >= 4; i -= 4) {
			switch(bin.substring(i - 4, i)){
			case("0000"):hex = 0 + hex; break;
			case("0001"):hex = 1 + hex; break;
			case("0010"):hex = 2 + hex; break;
			case("0011"):hex = 3 + hex; break;
			case("0100"):hex = 4 + hex; break;
			case("0101"):hex = 5 + hex; break;
			case("0110"):hex = 6 + hex; break;
			case("0111"):hex = 7 + hex; break;
			case("1000"):hex = 8 + hex; break;
			case("1001"):hex = 9 + hex; break;
			case("1010"):hex = "A" + hex; break;
			case("1011"):hex = "B" + hex; break;
			case("1100"):hex = "C" + hex; break;
			case("1101"):hex = "D" + hex; break;
			case("1110"):hex = "E" + hex; break;
			case("1111"):hex = "F" + hex; break;
			}
		}
		if (i > 0) {
			switch(bin.substring(0, i)){
			case("000"):hex = 0 + hex; break;
			case("001"):hex = 1 + hex; break;
			case("010"):hex = 2 + hex; break;
			case("011"):hex = 3 + hex; break;
			case("100"):hex = 4 + hex; break;
			case("101"):hex = 5 + hex; break;
			case("110"):hex = 6 + hex; break;
			case("111"):hex = 7 + hex; break;
			case("00"):hex = 0 + hex; break;
			case("01"):hex = 1 + hex; break;
			case("10"):hex = 2 + hex; break;
			case("11"):hex = 3 + hex; break;
			case("1"):hex = 1 + hex; break;
			case("0"):hex = 0 + hex; break;
			}
		}
		return hex;
	}
	
	public static String toBin(String hex) {
		String bin = "";
		int i = 0;
		for (i = 0; i < hex.length(); i++) {
			switch(hex.charAt(i)){
			case('0'):bin += "0000"; break;
			case('1'):bin += "0001"; break;
			case('2'):bin += "0010"; break;
			case('3'):bin += "0011"; break;
			case('4'):bin += "0100"; break;
			case('5'):bin += "0101"; break;
			case('6'):bin += "0110"; break;
			case('7'):bin += "0111"; break;
			case('8'):bin += "1000"; break;
			case('9'):bin += "1001"; break;
			case('A'):bin += "1010"; break;
			case('B'):bin += "1011"; break;
			case('C'):bin += "1100"; break;
			case('D'):bin += "1101"; break;
			case('E'):bin += "1110"; break;
			case('F'):bin += "1111"; break;
			}
		}
		return bin;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/fib"));
		in.useDelimiter("\\s");
		for (int f = 0; f < 5; f++) {
			int num = in.nextInt();
			in.nextLine();
			ArrayList<Integer> fibs = new ArrayList<Integer>();
			fibs.add(1);
			fibs.add(2);
			while (fibs.get(fibs.size() - 1) < num) fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));
			fibs.remove(fibs.size() - 1);
			String output = "";
			for (int i = 0; i < fibs.size() + 1; i++) output += "0";
			for (int i = fibs.size() - 1; i >= 0; i--) {
				if (fibs.get(i) <= num) {
					num -= fibs.get(i);
					output = output.substring(0, i) + "1" + output.substring(i + 1, output.length());
					i++;
					continue;
				}
			}
			output = output.substring(0, output.length() - 1) + "1";
			System.out.println(toHex(output) + ", " + output.length());
		}
		for (int f = 0; f < 5; f++) {
			String num = in.next(), bin = toBin(num);
			int len = in.nextInt();
			bin = bin.substring(bin.length() - len, bin.length());
			int[] fibs = new int[bin.length()];
			fibs[0] = 1;
			fibs[1] = 2;
			for (int i = 2; i < fibs.length; i++) {
				fibs[i] = fibs[i - 1] + fibs[i - 2];
			}
			int output = 0;
			for (int i = 0; i < bin.length() - 1; i++) {
				if (bin.charAt(i) == '1') output += fibs[i];
			}
			System.out.println(output);
			in.nextLine();
		}
		in.close();
	}

}
