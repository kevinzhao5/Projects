import java.io.*;
import java.util.*;

public class diamond {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] sizes = new int[N];
		for (int i = 0; i < N; i++) sizes[i] = Integer.parseInt(in.readLine());
		Arrays.sort(sizes);
		int[] maxSize = new int[N];
		int[] minSize = new int[N];
		int[] maxIndex = new int[N];
		int[] minIndex = new int[N];
		int c = 0;
		for (int i = 0; i < N; i++) {
			while (c < N && sizes[c] < sizes[i] - K) c++;
			maxIndex[i] = c;
		}
		c = N - 1;
		for (int i = N - 1; i >= 0; i--) {
			while (c > -1 && sizes[c] > sizes[i] + K) c--;
			minIndex[i] = c;
		}
		maxSize[0] = 1;
		for (int i = 1; i < N; i++) {
			maxSize[i] = i - maxIndex[i] + 1;
			maxSize[i] = Math.max(maxSize[i], maxSize[i - 1]);
		}
		minSize[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			minSize[i] = minIndex[i] - i + 1;
			minSize[i] = Math.max(minSize[i], minSize[i + 1]);
		}
		int max = 0;
		for (int i = 0; i < N - 1; i++) max = Math.max(maxSize[i] + minSize[i + 1], max);
		out.println(max);
		in.close();
		out.close();
	}
	
}