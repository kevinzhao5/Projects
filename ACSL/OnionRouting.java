import java.util.*;
import java.io.*;

public class OnionRouting {

	public static String toDec(String hex) {
		String num = Integer.toString(Integer.parseInt(hex.trim(), 16 ));
		return num;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/onion"));
		in.useDelimiter("\\s");
		for (int w = 0; w < 10; w++) {
			String str = in.next();
			int numRoute = in.nextInt();
			String[] routers = new String[numRoute];
			String[] names = new String[numRoute];
			for (int i = 0; i < numRoute; i++) {
				String temp = in.next();
				routers[i] = toDec(temp);
				names[i] = temp;
			}
			int index = in.nextInt();
			in.nextLine();
			for (int i = 0; i < numRoute; i++) {
				if (((int)routers[i].charAt(0) - 48) % 2 == 0) {
					int num1 = (int)routers[i].charAt(1) - 48, num2 = (int)routers[i].charAt(2) - 48;
					if (num1 > num2) {
						int temp = num1;
						num1 = num2;
						num2 = temp;
					}
					String temp = "";
					temp = str.substring(0, num1 - 1) + str.charAt(num2 - 1) + str.substring(num1, num2 - 1) + str.charAt(num1 - 1) + str.substring(num2, str.length());
					str = temp;
				} else {
					int num1 = (int)routers[i].charAt(1) - 48, num2 = (int)routers[i].charAt(2) - 48;
					if (num2 > num1) {
						int temp = num1;
						num1 = num2;
						num2 = temp;
					}
					num1 = (str.length() - 1) - (num1 - 1);
					num2 = (str.length() - 1) - (num2 - 1);
					String temp = "";
					temp = str.substring(0, num1) + str.charAt(num2) + str.substring(num1 + 1, num2) + str.charAt(num1 ) + str.substring(num2 + 1, str.length());
					str = temp;
				}
				if (i == 0) {
					str = "A" + str + "AA";
				}
				str = names[i].charAt(0) + str + names[i].charAt(2);
			}
			System.out.println(str.charAt(index - 1));
		}
		in.close();
	}

}