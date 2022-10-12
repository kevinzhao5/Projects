/*
ID: awesome25
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class time {
	
	int time, q;
	
	public time(int start2, int end2) {
		time = start2;
		q = end2;
	}
	
}

class milk2 {
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		time[] times = new time[in.nextInt() * 2];
		for (int i = 0; i < times.length; i += 2) {
			in.nextLine();
			times[i] = new time(in.nextInt(), 1);
			times[i + 1] = new time(in.nextInt(), -1);
		}
		int count = 0;
		for (int i = 0; i < times.length; i++) {
			count = 0;
			for (int x = 0; x < times.length - i; x++) {
				if (times[count].time < times[x].time) {
					count = x;
				}
			}
			time temp = times[count];
			times[count] = times[times.length - i - 1];
			times[times.length - i - 1] = temp;
		}
		int startMilk = times[0].time, counter = 1, startIdle = 0, maxMilk = 0, maxIdle = 0;
		boolean milk = true;
		for (int i = 1; i < times.length; i++) {
			counter += times[i].q;
			if (counter > 0 && !milk && times[i].time != times[i - 1].time) {
				if (i == times.length - 1) {
					//System.out.println("MILKING " + times[i].time + " " + startIdle);
					milk = true;
					if (times[i].time - startIdle > maxIdle && i != 0) maxIdle = times[i].time - startIdle;
					startMilk = times[i].time;
				} else if (times[i].time != times[i + 1].time) {
					milk = true;
					if (times[i].time - startIdle > maxIdle && i != 0) maxIdle = times[i].time - startIdle;
					startMilk = times[i].time;
				}
			}
			else if (counter == 0 && milk && times[i].time != times[i - 1].time) {
				if (i == times.length - 1) {
					//System.out.println("IDLE " + times[i].time + " " + startMilk);
					milk = false;
					if (times[i].time - startMilk > maxMilk) maxMilk = times[i].time - startMilk;
					startIdle = times[i].time;
				} else if (times[i].time != times[i + 1].time) {
					milk = false;
					if (times[i].time - startMilk > maxMilk) maxMilk = times[i].time - startMilk;
					startIdle = times[i].time;
				}
			}
		}
		out.println(maxMilk + " " + maxIdle);
		in.close();
		out.close();
	}
}