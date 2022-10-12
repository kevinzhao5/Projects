/*
ID: awesome25
LANG: JAVA
TASK: stampede
*/

import java.io.*;
import java.util.*;


class event {
    int time;
    int y;

    public event(int t, int y1) {
        time = t;
        y = y1;
    }

}


class stampede {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader("stampede.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
        int n = Integer.parseInt(in.readLine());
        event[] events = new event[n * 2];
        int x = 0, y = 0, speed = 0;
        for (int i = 0; i < events.length; i += 2) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            speed = Integer.parseInt(st.nextToken());
            x *= -speed;
            events[i] = new event(x - speed, y);
            events[i + 1] = new event(x, -y);
        }
        Arrays.sort(events, new Comparator<event>() {
            @Override
            public int compare(event o1, event o2) {
                return o1.time - o2.time;
            }
        });
        ArrayList<Integer> seen = new ArrayList<Integer>();
        ArrayList<Integer> active = new ArrayList<Integer>();
        for (int i = 0; i < events.length; ) {
        	int j;
            for (j = i; j < events.length && events[i].time == events[j].time; j++) {
            	int y1 = events[j].y;
                if (y1 > 0) {
                    active.add(y1);
                } else {
                    active.remove(new Integer(-1 * y1));
                }
            }
            if (active.size() != 0) {
            	if (!seen.contains(active.get(0))) {
            		seen.add(active.get(0));
            	}
            }
            i = j;
        }
        out.println(seen.size());
        out.close();
    }

}