import java.util.*;

class CounterfeitDollar608 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		for (int q = 0; q < testCases; q++) {
			int[] coins = new int[12];
			int[] degree = new int[12];
			String[] w = new String[3];
			w[0] = in.nextLine();
			w[1] = in.nextLine();
			w[2] = in.nextLine();
			for (int i = 0; i < 3; i++) {
				String[] words = w[i].split(" ");
				if (words[2].equals("even")) {
					for (int x = 0; x < words[0].length(); x++) {
						coins[(int) (words[0].charAt(x)) - 65] = 3;
						degree[(int) (words[0].charAt(x)) - 65] = 0;
						coins[(int) (words[1].charAt(x)) - 65] = 3;
						degree[(int) (words[1].charAt(x)) - 65] = 0;
					}
				} else if (words[2].equals("up")) {
					for (int x = 0; x < words[0].length(); x++) {
						int index = (int) (words[0].charAt(x)) - 65;
						if (coins[index] == 0) {
							coins[index] = 2;
							degree[index]++;
						} else if (coins[index] == 1) {
							coins[index] = 3;
							degree[index] = 0;
						} else if (coins[index] == 2) {
							degree[index]++;
						}
						index = (int) (words[1].charAt(x)) - 65;
						if (coins[index] == 0) {
							coins[index] = 1;
							degree[index]++;
						} else if (coins[index] == 2) {
							coins[index] = 3;
							degree[index] = 0;
						} else if (coins[index] == 1) {
							degree[index]++;
						}
					}
				} else if (words[2].equals("down")) {
					for (int x = 0; x < words[0].length(); x++) {
						int index = (int) (words[0].charAt(x)) - 65;
						if (coins[index] == 0) {
							coins[index] = 1;
							degree[index]++;
						} else if (coins[index] == 2) {
							coins[index] = 3;
							degree[index] = 0;
						} else if (coins[index] == 1) {
							degree[index]++;
						}
						index = (int) (words[1].charAt(x)) - 65;
						if (coins[index] == 0) {
							coins[index] = 2;
							degree[index]++;
						} else if (coins[index] == 1) {
							coins[index] = 3;
							degree[index] = 0;
						} else if (coins[index] == 2) {
							degree[index]++;
						}
					}
				}
			}
			int max = 0, index = 0;
			for (int i = 0; i < 12; i++) {
				if (degree[i] > max) {
					max = degree[i];
					index = i;
				}
			}
			if (coins[index] == 1) System.out.println((char)(index + 65) + " is the counterfeit coin and it is light.");
			else System.out.println((char)(index + 65) + " is the counterfeit coin and it is heavy.");
		}
		in.close();
	}

}
