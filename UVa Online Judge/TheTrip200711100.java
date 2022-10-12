import java.util.*;
import java.io.*;

class bag {
	
	ArrayList<Integer> bags = new ArrayList<Integer>();
	int max = 0;
	
	public bag(int n) {
		bags.add(n);
		max = n;
	}
	
	public void addNum(int n) {
		bags.add(n);
		max = n;
	}
	
}

class TheTrip200711100 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n = Integer.parseInt(in.readLine());
		while (n != 0) {
			PriorityQueue<bag> pq = new PriorityQueue<bag>(n, new Comparator<bag>() {
			    public int compare(bag o1, bag o2) {
			        if (o1.max < o2.max) return -1;
			        if (o1.max > o2.max) return 1;
			        if (o1.bags.size() < o2.bags.size()) return -1;
			        if (o1.bags.size() > o2.bags.size()) return 1;
			        return 0;
			    }
			});
			int t = n;
			while (t > 0) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				while (st.hasMoreTokens()) {
					bag temp = new bag(Integer.parseInt(st.nextToken()));
					pq.add(temp);
					t--;
				}
			}
			PriorityQueue<bag> inQueue = new PriorityQueue<bag>(n, new Comparator<bag>() {
			    public int compare(bag o1, bag o2) {
			        if (o1.max < o2.max) return -1;
			        if (o1.max > o2.max) return 1;
			        if (o1.bags.size() < o2.bags.size()) return -1;
			        if (o1.bags.size() > o2.bags.size()) return 1;
			        return 0;
			    }
			});
			while (!pq.isEmpty()) {
				bag curr = pq.poll();
				if (inQueue.size() == 0) inQueue.offer(curr);
				else if (curr.max > inQueue.peek().max) {
					bag temp = inQueue.poll();
					temp.addNum(curr.max);
					inQueue.offer(temp);
				} else inQueue.offer(curr);
			}
			out.println(inQueue.size());
			while (!inQueue.isEmpty()) {
				bag curr = inQueue.poll();
				for (int i = 0; i < curr.bags.size() - 1; i++) {
					out.print(curr.bags.get(i) + " ");
				}
				out.println(curr.bags.get(curr.bags.size() - 1));
			}
			out.println();
			n = Integer.parseInt(in.readLine());
		}
		in.close();
		out.close();
	}

}
