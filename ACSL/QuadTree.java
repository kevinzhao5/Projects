import java.io.*;
import java.util.*;

public class QuadTree {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/as10-test.txt"));
		for (int i = 0; i < 10; i++) {
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		for (int j = 0; j < N; j++) {
			System.out.print((int) (Math.random() * N));
		}
		System.out.println();
		}
		in.close();
	}

}
