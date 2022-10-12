import java.util.*;
import java.io.*;

public class binPacking {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/bin"));
		in.useDelimiter("\\s");
		for (int q = 0; q < 5; q++) {
			int num = in.nextInt();
			ArrayList<Integer> weights = new ArrayList<Integer>();
			while (num != 0) {
				weights.add(num);
				num = in.nextInt();
			}
			int[] bins = new int[weights.size()];
			for (int i = 0; i < weights.size(); i++) {
				for (int x = 0; x < bins.length; x++) {
					if (weights.get(i) <= 10 - bins[x]) {
						bins[x] += weights.get(i);
						break;
					}
				}
			}
			String output = "";
			for (int i = 0; i < bins.length; i++) {
				if (bins[i] != 0) output += bins[i] + ", ";
			}
			System.out.println(output.substring(0, output.length() - 2));
			Collections.sort(weights);
			bins = new int[weights.size()];
			for (int i = 0; i < weights.size(); i++) {
				for (int x = 0; x < bins.length; x++) {
					if (weights.get(i) <= 10 - bins[x]) {
						bins[x] += weights.get(i);
						break;
					}
				}
			}
			output = "";
			for (int i = 0; i < bins.length; i++) {
				if (bins[i] != 0) output += bins[i] + ", ";
			}
			System.out.println(output.substring(0, output.length() - 2));
			in.nextLine();
		}
		in.close();
	}

}