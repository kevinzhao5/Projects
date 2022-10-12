import java.util.*;
import java.io.*;

class UnionFind {
  private Vector<Integer> p, rank, setSize;
  private int numSets;

  public UnionFind(int N) {
    p = new Vector<Integer>(N);
    rank = new Vector<Integer>(N);
    setSize = new Vector<Integer>(N);
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
    }
  }

  public int findSet(int i) { 
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i)); p.set(i, ret);
      return ret; } }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { numSets--; 
    int x = findSet(i), y = findSet(j);
    if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
    else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
  public int numDisjointSets() { return numSets; }
  public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}

class triple implements Comparable<triple> {
	
	int w, v, u;
	
	public triple(int w1, int v1, int u1) {
		w = w1;
		v = v1;
		u = u1;
	}

	@Override
	public int compareTo(triple o) {
		return w - o.w;
	}
	
}

class DarkRoads_MST {

	static triple[] edgelist;
	static int numv, numr, totalCost = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (true) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			totalCost = 0;
			numv = Integer.parseInt(st.nextToken());
			numr = Integer.parseInt(st.nextToken());
			if (numv == 0 && numr == 0) break;
			edgelist = new triple[numr];
			for (int i = 0; i < numr; i++) {
				StringTokenizer s = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(s.nextToken()), y = Integer.parseInt(s.nextToken()), w = Integer.parseInt(s.nextToken());
				edgelist[i] = new triple(w, x, y);
				totalCost += w;
			}
			Arrays.sort(edgelist);
			int lowcost = 0;
			UnionFind uf = new UnionFind(numv);
			for (int i = 0; i < numr; i++) {
				triple t = edgelist[i];
				if (!uf.isSameSet(t.v, t.u)) {
					lowcost += t.w;
					uf.unionSet(t.v, t.u);
				}
			}
			out.println(totalCost - lowcost);
		}
		in.close();
		out.close();
	}

}
