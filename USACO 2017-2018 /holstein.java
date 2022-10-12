/*
ID: awesome25
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

class vitamin {
	
	int[] nut;
	int index;
	
	public vitamin(int num, int index2) {
		nut = new int[num];
		index = index2;
	}
	
}

class holstein {
	
	static int[] vitReq;
	static int numVit;
	
	public static void test(int depth, ArrayList<vitamin> feeds, ArrayList<vitamin> used, int start) throws IOException {
		if (depth == 0) {
			boolean check = true;
			for (int i = 0; i < numVit; i++) {
				int temp = 0;
				for (int x = 0; x < used.size(); x++) {
					temp += used.get(x).nut[i];
				}
				if (temp < vitReq[i]) {
					check = false;
					break;
				}
			}
			if (check) {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
				out.print(used.size() + " ");
				Collections.sort(used, 
                        (o1, o2) -> o1.index - o2.index);
				for (int i = 0; i < used.size() - 1; i++) {
					out.print(used.get(i).index + " ");
				}
				out.println(used.get(used.size() - 1).index);
				out.close();
				System.exit(0);
			}
		} else {
			for (int i = start; i < feeds.size(); i++) {
				vitamin temp = feeds.remove(i);
				used.add(temp);
				test(depth - 1, feeds, used, i);
				used.remove(used.size() - 1);
				feeds.add(i, temp);
			}
		}
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
		numVit = Integer.parseInt(in.readLine());
		vitReq = new int[numVit];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < numVit; i++) {
			vitReq[i] = Integer.parseInt(st.nextToken());
		}
		int numFeeds = Integer.parseInt(in.readLine());
		ArrayList<vitamin> feeds = new ArrayList<vitamin>();
		for (int i = 0; i < numFeeds; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			feeds.add(new vitamin(numVit, i + 1));
			for (int x = 0; x < numVit; x++) {
				feeds.get(i).nut[x] = Integer.parseInt(st1.nextToken());
			}
		}
		ArrayList<vitamin> used = new ArrayList<vitamin>();
		for (int i = 1; i <= numFeeds; i++) {
			test(i, feeds, used, 0);
		}
		in.close();
	}
}