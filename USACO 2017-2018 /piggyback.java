import java.io.*;
import java.util.*;

class list {
	
	ArrayList<Integer> connects;
	
	public list() {
		connects = new ArrayList<Integer>();
	}
	
}
class piggyback {
	
	static int b, e, p, n, m;
	static int[] bdist;
	static int[] edist;
	static int[] homeDist;
		
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("piggyback.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		b = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		bdist = new int[n + 1];
		edist = new int[n + 1];
		homeDist = new int[n + 1];
		list[] connections = new list[n + 1];
		for (int i = 0; i < connections.length; i++) {
			connections[i] = new list();
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st1.nextToken()), b = Integer.parseInt(st1.nextToken());
			connections[a].connects.add(b);
			connections[b].connects.add(a);
		}
		Arrays.fill(bdist, -1);
		Arrays.fill(edist, -1);
		Arrays.fill(homeDist, -1);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		ArrayList<Integer> dist = new ArrayList<Integer>();
		dist.add(0);
		queue.add(1);
		bdist[1] = 0;
		while (!queue.isEmpty()) {
			int temp = queue.remove(0), currDist = dist.remove(0), size = connections[temp].connects.size();
			for (int i = 0; i < size; i++) {
				if (bdist[connections[temp].connects.get(i)] == -1) {
					queue.add(connections[temp].connects.get(i));
					dist.add(currDist + 1);
					bdist[connections[temp].connects.get(i)] = currDist + 1;
				}
			}
		}
		queue = new ArrayList<Integer>();
		dist = new ArrayList<Integer>();
		dist.add(0);
		queue.add(2);
		edist[2] = 0;
		while (!queue.isEmpty()) {
			int temp = queue.remove(0), currDist = dist.remove(0), size = connections[temp].connects.size();
			edist[temp] = currDist;
			for (int i = 0; i < size; i++) {
				if (edist[connections[temp].connects.get(i)] == -1) {
					queue.add(connections[temp].connects.get(i));
					dist.add(currDist + 1);
					edist[connections[temp].connects.get(i)] = currDist + 1;
				}
			}
		}
		queue = new ArrayList<Integer>();
		dist = new ArrayList<Integer>();
		dist.add(0);
		queue.add(n);
		homeDist[n] = 0;
		while (!queue.isEmpty()) {
			int temp = queue.remove(0), currDist = dist.remove(0), size = connections[temp].connects.size();
			homeDist[temp] = currDist;
			for (int i = 0; i < size; i++) {
				if (homeDist[connections[temp].connects.get(i)] == -1) {
					queue.add(connections[temp].connects.get(i));
					dist.add(currDist + 1);
					homeDist[connections[temp].connects.get(i)] = currDist + 1;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			min = Math.min(min, b * bdist[i] + e * edist[i] + homeDist[i] * p);
		}
		out.println(min);
		in.close();
		out.close();
	}
	
}