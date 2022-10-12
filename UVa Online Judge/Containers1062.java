import java.util.*;

class Containers1062 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		int counter = 1;
		while (!str.equals("end")) {
			int num = 0;
			int[] top = new int[26];
			for (int i = 0; i < str.length(); i++) {
				int c = (int) (str.charAt(i));
				int min = Integer.MAX_VALUE, index = -1;
				for (int x = 0; x < 26; x++) {
					if (top[x] >= c && top[x] < min) {
						min = top[x];
						index = x;
					}
				}
				if (min == Integer.MAX_VALUE) {
					num++;
					for (int x = 0; x < 26; x++) {
						if (top[x] == 0) {
							index = x;
							break;
						}
					}
					top[index] = c;
				} else {
					top[index] = c;
				}
			}
			System.out.println("Case " + counter + ": " + num);
			str = in.nextLine();
			counter++;
		}
		in.close();
	}

}
