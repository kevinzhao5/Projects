/*
ID: awesome25
LANG: JAVA
TASK: butter
*/
import java.io.*;
import java.util.*;

class node implements Comparable<node> {
	
	int dist, index;
	boolean visited;
	
	public node(int index1) {
		index = index1;
		dist = 500000;
		visited = false;
	}

	@Override
	public int compareTo(node o) {
		if (this.dist == o.dist) return this.index - o.index;
		else return this.dist - o.dist;
	}
	
}

class butter {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("butter.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
		int[][] dist = new int[p + 1][p + 1];
		ArrayList<ArrayList<Integer>> neigh = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < p + 1; i++) {
			neigh.add(new ArrayList<Integer>());
		}
		int[] cows = new int[n];
		node[] best = new node[p + 1];
		for (int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 1; i <= p; i++) {
			Arrays.fill(dist[i], 500000);
			dist[i][i] = 0;
		}
		for (int i = 0; i < c; i++) {
			StringTokenizer st1 = new StringTokenizer(in.readLine());
			int temp = Integer.parseInt(st1.nextToken()), temp1 = Integer.parseInt(st1.nextToken()), temp2 = Integer.parseInt(st1.nextToken());
			dist[temp1][temp] = temp2;
			dist[temp][temp1] = temp2;
			neigh.get(temp).add(temp1);
			neigh.get(temp1).add(temp);
		}
		int min = Integer.MAX_VALUE;
		for (int q = 1; q <= p; q++) {
			for (int i = 1; i <= p; i++) {
				best[i] = new node(i);
			}
			PriorityQueue<node> queue = new PriorityQueue<node>();
			queue.add(best[q]);
			best[q].dist = 0;
			while (!queue.isEmpty()) {
				node curr = queue.poll();
				if (!curr.visited) {
					best[curr.index].visited = true;
					for (int i = 0; i < neigh.get(curr.index).size(); i++) {
						if (best[neigh.get(curr.index).get(i)].visited) continue;
						if (dist[curr.index][neigh.get(curr.index).get(i)] == 500000) continue;
						if (dist[neigh.get(curr.index).get(i)][curr.index] + curr.dist < best[neigh.get(curr.index).get(i)].dist) {
							best[neigh.get(curr.index).get(i)].dist = dist[neigh.get(curr.index).get(i)][curr.index] + curr.dist;
							queue.add(best[neigh.get(curr.index).get(i)]);
						}
					}
				}
			}
			int temp = 0;
			for (int x = 0; x < n; x++) {
				temp += best[cows[x]].dist;
			}
			min = Math.min(min, temp);
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		out.println(min);
		in.close();
		out.close();
	}
	
}