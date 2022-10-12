import java.io.*;
import java.util.*;

class b implements Comparator<a> {

	@Override
	public int compare(a o1, a o2) {
		if (o1.b == o2.b) return o1.a - o2.a;
		return o1.b - o2.b;
	}
	
}

class a implements Comparable<a> {
	
	int a, b;
	
	public a(int aa, int bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(a o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class split {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("split.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		int N = Integer.parseInt(in.readLine());
		a[] cows = new a[N];
		long xLow = Integer.MAX_VALUE, xHi = 0, yLow = Integer.MAX_VALUE, yHi = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			cows[i] = new a(x, y);
			xLow = Math.min(xLow, x);
			xHi = Math.max(xHi, x);
			yLow = Math.min(yLow, y);
			yHi = Math.max(yHi, y);
		}
		Arrays.sort(cows);
		long minArea = Long.MAX_VALUE;
		int[] tempHi = new int[N];
		Arrays.fill(tempHi, 0);
		tempHi[0] = cows[0].b;
		int[] tempLow = new int[N];
		Arrays.fill(tempLow, Integer.MAX_VALUE);
		tempLow[0] = cows[0].b;
		int[] tempHi2 = new int[N];
		Arrays.fill(tempHi2, 0);
		tempHi2[N - 1] = cows[N - 1].b;
		int[] tempLow2 = new int[N];
		Arrays.fill(tempLow2, Integer.MAX_VALUE);
		tempLow2[N - 1] = cows[N - 1].b;
		for (int i = 1; i < N; i++) {
			tempHi[i] = Math.max(tempHi[i - 1], cows[i].b);
			tempLow[i] = Math.min(tempLow[i - 1], cows[i].b);
		}
		for (int i = N - 2; i >= 0; i--) {
			tempHi2[i] = Math.max(tempHi2[i + 1], cows[i].b);
			tempLow2[i] = Math.min(tempLow2[i + 1], cows[i].b);
		}
		for (int i = 0; i < N - 1; i++) {
			if (cows[i + 1].a != cows[i].a) minArea = Math.min(minArea, (tempHi[i] - tempLow[i]) * (cows[i].a - xLow) + (tempHi2[i + 1] - tempLow2[i + 1]) * (xHi - cows[i + 1].a));
		}
		/*for (int i = N - 1; i > 0; i--) {
			if (cows[i - 1].a != cows[i].a) minArea = Math.min(minArea, (tempHi2[i] - tempLow2[i]) * (xHi - cows[i].a) + (tempHi[i - 1] - tempLow[i - 1]) * (cows[i - 1].a - xLow));
		}*/
		Arrays.sort(cows, new b());
		tempHi = new int[N];
		Arrays.fill(tempHi, 0);
		tempHi[0] = cows[0].a;
		tempLow = new int[N];
		Arrays.fill(tempLow, Integer.MAX_VALUE);
		tempLow[0] = cows[0].a;
		tempHi2 = new int[N];
		Arrays.fill(tempHi2, 0);
		tempHi2[N - 1] = cows[N - 1].a;
		tempLow2 = new int[N];
		Arrays.fill(tempLow2, Integer.MAX_VALUE);
		tempLow2[N - 1] = cows[N - 1].a;
		for (int i = 1; i < N; i++) {
			tempHi[i] = Math.max(tempHi[i - 1], cows[i].a);
			tempLow[i] = Math.min(tempLow[i - 1], cows[i].a);
		}
		for (int i = N - 2; i >= 0; i--) {
			tempHi2[i] = Math.max(tempHi2[i + 1], cows[i].a);
			tempLow2[i] = Math.min(tempLow2[i + 1], cows[i].a);
		}
		for (int i = 0; i < N - 1; i++) {
			if (cows[i + 1].b != cows[i].b) minArea = Math.min(minArea, (tempHi[i] - tempLow[i]) * (cows[i].b - yLow) + (tempHi2[i + 1] - tempLow2[i + 1]) * (yHi - cows[i + 1].b));
		}
		/*for (int i = N - 1; i > 0; i--) {
			if (cows[i - 1].b != cows[i].b) minArea = Math.min(minArea, (tempHi2[i] - tempLow2[i]) * (yHi - cows[i].b) + (tempHi[i - 1] - tempLow[i - 1]) * (cows[i - 1].b - yLow));
		}*/
		out.println(Math.max((xHi - xLow) * (yHi - yLow) - minArea, 0));
		in.close();
		out.close();
	}
	
}