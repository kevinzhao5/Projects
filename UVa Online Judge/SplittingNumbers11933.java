import java.util.*;

class SplittingNumbers11933 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long n = Long.parseLong(in.nextLine());
		while (n > 0) {
			int pow = 0;
			for (int i = 0; i < 32; i++) {
				if (Math.pow(2, i) > n) {
					pow = i;
					break;
				}
			}
			ArrayList<Integer> indices = new ArrayList<Integer>();
			for (int i = 0; i < pow; i++) {
				if ((n & (1 << i)) != 0) indices.add(i);
			}
			int a = 0, b = 0;
			for (int i = 0; i < indices.size(); i++) {
				if (i % 2 == 1) a |= 1 << indices.get(i);
				else b |= 1 << indices.get(i);
			}
			System.out.println(b + " " + a);
			n = Long.parseLong(in.nextLine());
		}
		in.close();
	}

}
