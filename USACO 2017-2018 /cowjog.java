import java.io.*;
import java.math.BigInteger;
import java.util.*;

class cow1 {
	
	BigInteger pos, speed;
	
	public cow1(BigInteger pos1, BigInteger speed1) {
		pos = pos1;
		speed = speed1;
	}
	
}

class cowjog {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		BigInteger min = BigInteger.valueOf(Integer.parseInt(st.nextToken()));
		cow1[] cows = new cow1[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			cows[i] = new cow1(BigInteger.valueOf(Integer.parseInt(st1.nextToken())), BigInteger.valueOf(Integer.parseInt(st1.nextToken())));
		}
		int groups = 1;
		BigInteger[] finalPos = new BigInteger[n];
		for (int i = 0; i < n; i++) {
			finalPos[i] = cows[i].pos.add(cows[i].speed.multiply(min));
		}
		BigInteger mini = finalPos[n - 1];
		for (int i = n - 1; i >= 0; i--) {
			if (finalPos[i].compareTo(mini) < 0) groups++;
			mini = mini.min(finalPos[i]);
		}
		out.println(groups);
		in.close();
		out.close();
	}
	
}