import java.util.*;
import java.io.*;

public class Change {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/change"));
		in.useDelimiter("\\s");
		for (int x = 0; x < 5; x++) {
			double price = in.nextDouble();
			double payment = in.nextDouble();
			double change = payment - price;
			int[] currency = new int[10];
			while (change > 0) {
				if (change - 100 >= -0.001) {
					change -= 100;
					currency[0]++;
					continue;
				}
				if (change - 50 >= -0.001) {
					change -= 50;
					currency[1]++;
					continue;
				}
				if (change - 20 >= -0.001) {
					change -= 20;
					currency[2]++;
					continue;
				}
				if (change - 10 >= -0.001) {
					change -= 10;
					currency[3]++;
					continue;
				}
				if (change - 5 >= -0.001) {
					change -= 5;
					currency[4]++;
					continue;
				}
				if (change - 1 >= -0.001) {
					change -= 1;
					currency[5]++;
					continue;
				}
				if (change - 0.25 >= -0.001) {
					change -= 0.25;
					currency[6]++;
					continue;
				}
				if (change - 0.10 >= -0.001) {
					change -= 0.10;
					currency[7]++;
					continue;
				}
				if (change - 0.05 >= -0.001) {
					change -= 0.05;
					currency[8]++;
					continue;
				}
				if (change - 0.01 >= -0.001) {
					change -= 0.01;
					currency[9]++;
					continue;
				}
				if (change < 0.001) break;
			}
			for (int i = 0; i <= 9; i++) {
				if (currency[i] == 0) {
					continue;
				} else {
					System.out.print(currency[i]);
				}
			}
			System.out.println();
			in.nextLine();
		}
		in.close();
	}

}