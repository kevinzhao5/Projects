import java.util.*;
import java.io.*;

public class Solitaire {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/Solitaire"));
		in.useDelimiter("\\s|,\\s|,");
		for (int q = 0; q < 10; q++) {
			int num = in.nextInt();
			ArrayList<String> deck = new ArrayList<String>(), field = new ArrayList<String>();
			for (int i = 0; i < num; i++) {
				deck.add(in.next());
			}
			int x = 0;
			for (int i = 0; i < num; i++) {
				field.add(deck.remove(0));
				while (field.size() > 1) {
					if (field.get(x).charAt(0) == field.get(x - 1).charAt(0) || field.get(x).charAt(1) == field.get(x - 1).charAt(1)) {
						field.remove(x - 1);
						x--;
					} else {
						break;
					}
				}
				x++;
			}
			for (int i = 0; i < field.size(); i++) {
				System.out.print(field.get(i) + ", ");
			}
			System.out.println();
			in.nextLine();
		}
		in.close();
	}
}