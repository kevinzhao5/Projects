import java.util.*;
import java.io.*;

public class Checksum {

	public static boolean valid(int[] dig) {
		int[] newDig = new int[dig.length - 1];
		int sum = 0;
		for (int i = 0; i < dig.length - 1; i++) {
			newDig[i] = dig[i];
			if (dig.length % 2 == 1 && i % 2 == 1) newDig[i] *= 2;
			else if (dig.length % 2 == 0 && i % 2 == 0) newDig[i] *= 2;
			if (newDig[i] > 9) newDig[i] = newDig[i] / 10 + newDig[i] % 10;
			sum += newDig[i];
		}
		sum *= 9;
		sum %= 10;
		if (sum == dig[dig.length - 1]) {
			for (int i = 0; i < dig.length; i++) {
				System.out.print(dig[i]);
			}
			System.out.println();
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/checksum"));
		for (int e = 0; e < 5; e++) {
			String num = in.nextLine();
			int[] dig = new int[num.length()];
			int[] newDig = new int[num.length() - 1];
			int sum = 0;
			for (int i = 0; i < num.length() - 1; i++){
				dig[i] = (int) (num.charAt(i) - 48);
				newDig[i] = (int) (num.charAt(i) - 48);
				if (num.length() % 2 == 1 && i % 2 == 1) newDig[i] *= 2;
				else if (num.length() % 2 == 0 && i % 2 == 0) newDig[i] *= 2;
				if (newDig[i] > 9) newDig[i] = newDig[i] / 10 + newDig[i] % 10;
				sum += newDig[i];
			}
			dig[dig.length - 1] = (int) (num.charAt(num.length() - 1) - 48);
			sum *= 9;
			sum %= 10;
			if (sum == (int) (num.charAt(num.length() - 1) - 48)) System.out.println("VALID");
			else {
				for (int i = 0; i < dig.length; i++) {
					if (dig[i] > 0) {
						dig[i]--;
						if (valid(dig)) break;
						dig[i]++;
					}
					if (dig[i] < 9) {
						dig[i]++;
						if (valid(dig)) break;
						dig[i]--;
					}
				}
			}
		}
		in.close();
	}

}