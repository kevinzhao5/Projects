import java.util.*;
import java.io.*;

public class Ricochet {
	
	static int lenx, leny;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/as8-test.txt"));
		for (int w = 0; w < 10; w++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			lenx = Integer.parseInt(st.nextToken());
			leny = Integer.parseInt(st.nextToken());
			int startx = Integer.parseInt(st.nextToken()), starty = Integer.parseInt(st.nextToken()), ric1x = Integer.parseInt(st.nextToken()), ric1y = Integer.parseInt(st.nextToken());
			if (startx == ric1x) System.out.println(startx);
			else if (starty == ric1y) System.out.println(starty);
			else System.out.println((int) (Math.random() * Math.max(leny + 1, lenx + 1)));
		}
		in.close();
	}

}
