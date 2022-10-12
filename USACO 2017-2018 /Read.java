import java.util.*;
import java.io.*;

public class Read {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/dict.txt"));
		int count = 0;
		while (1 == 1) {
			count++;
			System.out.println(count);
			in.nextLine();
		}
	}

}
