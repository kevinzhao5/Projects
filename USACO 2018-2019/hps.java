import java.io.*;

public class hps {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		int N = Integer.parseInt(in.readLine());
		int[] P = new int[N];
		int[] H = new int[N];
		int[] S = new int[N];
		for (int i = 0; i < N; i++) {
			char c = in.readLine().charAt(0);
			if (c == 'P') P[i]++;
			else if (c == 'H') H[i]++;
			else S[i]++;
			if (i > 0){
				P[i] += P[i - 1];
				H[i] += H[i - 1];
				S[i] += S[i - 1];
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, P[i] + Math.max(H[N - 1] - H[i], S[N - 1] - S[i]));
			max = Math.max(max, S[i] + Math.max(P[N - 1] - P[i], H[N - 1] - H[i]));
			max = Math.max(max, H[i] + Math.max(S[N - 1] - S[i], P[N - 1] - P[i]));
		}
		out.println(max);
		in.close();
		out.close();
	}
	
}