import java.util.*;
import java.io.*;

public class FlashTest {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/Test"));
		String x = in.nextLine(), y = in.nextLine();
		System.out.println(x + " " + y);
		in.close();
	}

}