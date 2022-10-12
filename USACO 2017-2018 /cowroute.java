/*
ID: awesome25
LANG: JAVA
TASK: cowroute
*/
import java.io.*;
import java.math.BigInteger;
import java.util.*;

class cowroute {
	
	public static int min(BigInteger one, BigInteger two) {
		return one.compareTo(two);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowroute.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
		BigInteger[][] cheap = new BigInteger[1001][1001];
		int[][] length = new int[1001][1001];
		boolean[] v = new boolean[1001];
		Arrays.fill(v, false);
		int graphSize = 0;
		BigInteger r = BigInteger.valueOf(Long.MAX_VALUE);
		r.multiply(BigInteger.valueOf(10000));
		for (int i = 0; i < cheap.length; i++) {
			Arrays.fill(cheap[i], r);
			Arrays.fill(length[i], Integer.MAX_VALUE);
			cheap[i][i] = BigInteger.valueOf(0);
			length[i][i] = 0;
		}
		for (int i = 0; i < n; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			BigInteger cost = BigInteger.valueOf(Long.parseLong(st1.nextToken()));
			int stops = Integer.parseInt(st1.nextToken());
			int[] stop = new int[stops];
			StringTokenizer st2 = new StringTokenizer(in.readLine());
			for (int x = 0; x < stops; x++) {
				int te = Integer.parseInt(st2.nextToken());
				if (!v[te]) {
					v[te] = true;
					graphSize++;
				}
				stop[x] = te;
				for (int s = 0; s < x; s++) {
					int t = min(cheap[stop[s]][stop[x]], cost);
					if (t > 0) {
						cheap[stop[s]][stop[x]] = cost;
						length[stop[s]][stop[x]] = x - s;
					} else if (t == 0) length[stop[s]][stop[x]] = Math.min(length[stop[s]][stop[x]], x - s);
				}
			}
		}
		BigInteger[] distance = new BigInteger[1001];
		int[] flights = length[start];
		flights[start] = 0;
		Arrays.fill(distance, r);
		Arrays.fill(v, false);
		distance[start] = BigInteger.valueOf(0l);
		for (int e = 0; e < graphSize; e++) {
			int index = -1;
			for (int i = 1; i <= 1000; i++) {
				if (v[i]) continue;
				if (index == -1 || distance[i].compareTo(distance[index]) == -1) {
					index = i;
				}
			}
			v[index] = true;
			for (int i = 1; i <= 1000; i++) {
				if ((distance[index].add(cheap[index][i])).compareTo(distance[i]) == -1) {
					distance[i] = distance[index].add(cheap[index][i]);
					flights[i] = flights[index] + length[index][i];
				} else if ((distance[index].add(cheap[index][i])).compareTo(distance[i]) == 0) {
					flights[i] = Math.min(flights[i], flights[index] + length[index][i]);
				}
			}
		}
		if (distance[end].equals(r)) out.println("-1 -1");
		else out.println(distance[end] + " " + flights[end]);
		in.close();
		out.close();
	}
	
}