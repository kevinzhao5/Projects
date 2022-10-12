import java.util.*;
import java.io.*;

public class GoFish {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/fish"));
		in.useDelimiter("\\s");
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();
		ArrayList<Integer> deck = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			A.add(in.nextInt());
		}
		in.nextLine();
		for (int i = 0; i < 7; i++) {
			B.add(in.nextInt());
		}
		in.nextLine();
		for (int i = 0; i < 10; i++) {
			deck.add(in.nextInt());
		}
		in.nextLine();
		for (int e = 0; e < 5; e++) {
			int card = Integer.parseInt(in.nextLine());
			boolean add = false;
			for (int i = 0; i < B.size(); i++) {
				if (B.get(i) == card) {
					B.remove(i);
					A.add(card);
					i--;
					add = true;
				}
			}
			if (!add) A.add(deck.remove(0));
			int num = 0;
			for (int i = 0; i < A.size(); i++) {
				if (A.get(i) == card) num++;
			}
			if (num >= 4) {
				int removed = 0;
				for (int i = 0; i < A.size(); i++) {
					if (A.get(i) == card) {
						removed++;
						A.remove(i);
						i--;
						if (removed == 4) break;
					}
				}
				num -= removed;
			}
			System.out.println(num);
			card = Integer.parseInt(in.nextLine());
			add = false;
			for (int i = 0; i < A.size(); i++) {
				if (A.get(i) == card) {
					A.remove(i);
					B.add(card);
					i--;
					add = true;
				}
			}
			if (!add) B.add(deck.remove(0));
			num = 0;
			for (int i = 0; i < B.size(); i++) {
				if (B.get(i) == card) num++;
			}
			if (num >= 4) {
				int removed = 0;
				for (int i = 0; i < B.size(); i++) {
					if (B.get(i) == card) {
						removed++;
						B.remove(i);
						i--;
						if (removed == 4) break;
					}
				}
				num -= removed;
			}
			System.out.println(num);
		}
		in.close();
	}

}