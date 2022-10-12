import java.util.*;
import java.io.*;

public class DigitReassembly {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/digitreassembly"));
		for (int e = 0; e < 5; e++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			String num = st.nextToken();
			int len = Integer.parseInt(st.nextToken());
			while (num.length() % len != 0) num += "0";
			int sum = 0;
			for (int i = 0; i < num.length(); i += len) {
				sum += Long.parseLong(num.substring(i, i + len));
			}
			System.out.println(sum);
		}
		in.close();
	}

}
