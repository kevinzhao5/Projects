/*
ID: awesome25
LANG: JAVA
TASK: cowtour
*/
import java.io.*;
import java.util.*;

class field {
	
	int x, y, ind;
	
	public field(int x2, int y2) {
		x = x2;
		y = y2;
		ind = -1;
	}
	
}

class cowtour {
		
	public static double distance(field f1, field f2) {
		double dist = 0.0;
		dist = Math.sqrt((f1.x - f2.x) * (f1.x - f2.x) + (f1.y - f2.y) * (f1.y - f2.y));
		return dist;
	}
	
	public static void find(field[] fields, int i, int index, double[][] dist, int num) {
		for (int x = 0; x < num; x++) {
			if (dist[i][x] < Integer.MAX_VALUE) fields[x].ind = index;
		}
	}
	
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		int num = Integer.parseInt(in.readLine());
		field[] fields = new field[num];
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			fields[i] = new field(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		boolean[][] matrix = new boolean[num][num];
		for (int i = 0; i < num; i++) {
			String str = in.readLine();
			for (int x = 0; x < num; x++) {
				if (str.charAt(x) == '0') matrix[i][x] = false;
				else matrix[i][x] = true;
			}
		}
		double[][] dist = new double[num][num];
		for (int i = 0; i < num; i++) {
			for (int x = 0; x < num; x++) {
				if (matrix[i][x]) dist[i][x] = distance(fields[i], fields[x]);
				else if (i == x) dist[i][x] = 0;
				else dist[i][x] = Integer.MAX_VALUE;
			}
		}
		for (int k = 0; k < num; k++) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		double[] maxDist = new double[num];
		for (int i = 0; i < num; i++) {
			double max = 0.0;
			for (int x = 0; x < num; x++) {
				if (dist[i][x] > max && dist[i][x] != Integer.MAX_VALUE) {
					max = dist[i][x];
				}
			}
			maxDist[i] = max;
		}
		int counter = 0;
		for (int i = 0; i < num; i++) {
			if (fields[i].ind == -1) {
				fields[i].ind = counter;
				find(fields, i, counter, dist, num);
				counter++;
			}
		}
		double[] diam = new double[counter];
		for (int i = 0; i < counter; i++) {
			for (int x = 0; x < num; x++) {
				if (fields[x].ind == i && maxDist[x] > diam[i]) diam[i] = maxDist[x];
			}
 		}
		double min = Double.MAX_VALUE;
		for (int i = 0; i < num; i++) {
			for (int x = 0; x < num; x++) {
				if (dist[i][x] == Integer.MAX_VALUE) {
					double max = Math.max(diam[fields[i].ind], Math.max(diam[fields[x].ind], maxDist[i] + maxDist[x] + distance(fields[i], fields[x])));
					min = Math.min(min, max);
				}
			}
		}
		out.printf("%.6f", min);
		out.println();
		in.close();
		out.close();
	}
	
}