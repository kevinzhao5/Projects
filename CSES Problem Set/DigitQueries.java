import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class DigitQueries {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int q = Integer.parseInt(in.readLine());
		for (int e = 0; e < q; e++) {
			long k = Long.parseLong(in.readLine());
			long accSum = 0;
			long prevSum = 0;
			long amt = 9;
			long mult = 1;
			while (prevSum + amt * mult < k) {
				prevSum += amt * mult;
				accSum += amt;
				amt *= 10l;
				mult++;
			}
			long index = k - prevSum;
			long num = accSum + index / mult;
			if (index % mult > 0) num++;
			//System.out.println(prevSum + " " + amt + " " + mult + " " + index + " " + num + " " + ((index + mult - 1l) % mult));
			System.out.println(Long.toString(num).charAt((int) ((index + mult - 1l) % mult)));
		}
		in.close();
		out.close();
	}
	
}
