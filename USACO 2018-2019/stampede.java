import java.io.*;
import java.util.*;

class e implements Comparable<e> {
	
	long a;
	int b, c;
	
	public e(long d, int e, int f) {
		a = d;
		b = e;
		c = f;
	}

	@Override
	public int compareTo(e o) {
		if (this.a == o.a) {
			if (this.b - o.b > 0) return 1;
			else if (this.b - o.b < 0) return -1;
			else return 0;
		}
		if (this.a - o.a > 0) return 1;
		else if (this.a - o.a < 0) return -1;
		else return 0;
	}
	
}

public class stampede {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader("stampede.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
        int n = Integer.parseInt(in.readLine());
        TreeMap<Integer, Integer> ins = new TreeMap<Integer, Integer>();
        e[] events = new e[2 * n];
        for (int i = 0; i < n; i++) {
        	StringTokenizer st = new StringTokenizer(in.readLine());
        	int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
        	events[i * 2] = new e(-(x + 1) * s, y, i);
        	events[i * 2 + 1] = new e(-x * s, y, i);
        }
        boolean[] seen = new boolean[n];
        Arrays.sort(events);
        for (int i = 0; i < 2 * n; i++) {
        	e eve = events[i];
        	int b = eve.b, c = eve.c;
        	if (!ins.isEmpty() && ins.containsKey(b)) ins.remove(b);
        	else ins.put(b, c);
        	if (!ins.isEmpty() && (i == 2 * n - 1 || eve.a != events[i + 1].a)) seen[ins.get(ins.firstKey())] = true;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) if (seen[i]) ans++;
        out.println(ans);
        out.close();
        in.close();
    }

}