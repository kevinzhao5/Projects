import java.util.*;
import java.io.*;

public class WrapAroundCode {
	
	public static int sumFactors(int n) {
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) ans += i;
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/Wrap Around Code"));
		in.useDelimiter(",");
		for (int u = 0; u < 5; u++) {
			String line = in.nextLine();
			int counter = 1;
			char c = line.charAt(0);
			String ans = "";
			int curr = 1;
			while (c != '$') {
				int op = Integer.parseInt(line.substring(counter, counter + 1)), n = (int) (c) - 64, index = 0;
				counter++;
				switch(op){
				case(1): index = n * 2; break;
				case(2): index = (n % 3) * 5; break;
				case(3): index = (n / 4) * -8; break;
				case(4): index = (int) (Math.sqrt(n)) * -12; break;
				case(5): index = sumFactors(n) * 10; break;
				}
				index %= 26;
				curr += index;
				if (curr < 1) curr = 26 + curr;
				if (curr > 26) curr %= 26;
				ans += (char) (curr + 64);
				c = line.charAt(counter);
				counter++;
			}
			System.out.println(ans);
		}
		in.close();
	}

}
