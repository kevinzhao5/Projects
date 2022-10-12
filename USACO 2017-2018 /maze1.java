/*
ID: awesome25
LANG: JAVA
TASK: maze1
*/
import java.io.*;
import java.util.*;

class point1 {
	
	int x, y, dist;
	
	public point1(int x2, int y2) {
		x = x2;
		y = y2;
	}
	
}

class maze1 {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int W = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken()), width = 2 * W + 1, height = 2 * H + 1, x1 = -1, x2 = -1, y1 = -1, y2 = -1;
		char[][] maze = new char[height][width];
		for (int i = 0; i < height; i++) {
			String str = in.readLine();
			for (int x = 0; x < width; x++) {
				if (str.charAt(x) == ' ') {
					maze[i][x] = ' ';
					if (x == 0) {
						if (x1 == -1) {
							x1 = i;
							y1 = x + 1;
						} else {
							x2 = i;
							y2 = x + 1;
						}
					} else if (i == 0) {
						if (x1 == -1) {
							x1 = i + 1;
							y1 = x;
						} else {
							x2 = i + 1;
							y2 = x;
						}
					} else if (x == width - 1) {
						if (x1 == -1) {
							x1 = i;
							y1 = x - 1;
						} else {
							x2 = i;
							y2 = x - 1;
						}
					} else if (i == height - 1) {
						if (x1 == -1) {
							x1 = i - 1;
							y1 = x;
						} else {
							x2 = i - 1;
							y2 = x;
						}
					}
				}
			}
		}
		boolean[][] v = new boolean[height][width];
		int[][] dist = new int[height][width];
		ArrayList<point1> pts = new ArrayList<point1>();
		pts.add(new point1(x1, y1));
		v[x1][y1] = true;
		int distance = 0;
		while (!pts.isEmpty()) {
			int size = pts.size();
			for (int i = 0; i < size; i++) {
				point1 pt = pts.remove(0);
				dist[pt.x][pt.y] = distance;
				//System.out.println(dist[pt.x][pt.y] + " " + pt.x + " " + pt.y + " " + height + " " + width);
				if (pt.x > 1) {
					if (maze[pt.x - 1][pt.y] == ' ' && maze[pt.x - 2][pt.y] == ' ' && !v[pt.x - 2][pt.y]){
						pts.add(new point1(pt.x - 2, pt.y));
						v[pt.x - 2][pt.y] = true;
					}
				}
				if (pt.x < height - 2) {
					if (maze[pt.x + 1][pt.y] == ' ' && maze[pt.x + 2][pt.y] == ' ' && !v[pt.x + 2][pt.y]) {
						pts.add(new point1(pt.x + 2, pt.y));
						v[pt.x + 2][pt.y] = true;
					}
				}
				if (pt.y < width - 2) {
					if (maze[pt.x][pt.y + 1] == ' ' && maze[pt.x][pt.y + 2] == ' ' && !v[pt.x][pt.y + 2]) {
						pts.add(new point1(pt.x, pt.y + 2));
						v[pt.x][pt.y + 2] = true;
					}
				}
				if (pt.y > 1) {
					if (maze[pt.x][pt.y - 1] == ' ' && maze[pt.x][pt.y - 2] == ' ' && !v[pt.x][pt.y - 2]) {
						pts.add(new point1(pt.x, pt.y - 2));
						v[pt.x][pt.y - 2] = true;
					}
				}
			}
			distance++;
		}
		pts.add(new point1(x2, y2));
		v = new boolean[height][width];
		v[x2][y2] = true;
		distance = 0;
		while (!pts.isEmpty()) {
			int size = pts.size();
			for (int i = 0; i < size; i++) {
				point1 pt = pts.remove(0);
				dist[pt.x][pt.y] = Math.min(dist[pt.x][pt.y], distance);
				System.out.println(dist[pt.x][pt.y] + " " + pt.x + " " + pt.y + " " + height + " " + width);
				if (pt.x > 1) {
					if (maze[pt.x - 1][pt.y] == ' ' && maze[pt.x - 2][pt.y] == ' ' && !v[pt.x - 2][pt.y]){
						pts.add(new point1(pt.x - 2, pt.y));
						v[pt.x - 2][pt.y] = true;
					}
				}
				if (pt.x < height - 2) {
					if (maze[pt.x + 1][pt.y] == ' ' && maze[pt.x + 2][pt.y] == ' ' && !v[pt.x + 2][pt.y]) {
						pts.add(new point1(pt.x + 2, pt.y));
						v[pt.x + 2][pt.y] = true;
					}
				}
				if (pt.y < width - 2) {
					if (maze[pt.x][pt.y + 1] == ' ' && maze[pt.x][pt.y + 2] == ' ' && !v[pt.x][pt.y + 2]) {
						pts.add(new point1(pt.x, pt.y + 2));
						v[pt.x][pt.y + 2] = true;
					}
				}
				if (pt.y > 1) {
					if (maze[pt.x][pt.y - 1] == ' ' && maze[pt.x][pt.y - 2] == ' ' && !v[pt.x][pt.y - 2]) {
						pts.add(new point1(pt.x, pt.y - 2));
						v[pt.x][pt.y - 2] = true;
					}
				}
			}
			distance++;
		}
		int max = 0;
		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				if (dist[i][x] > max) max = dist[i][x];
			}
		}
		out.println(max + 1);
		in.close();
		out.close();
	}
	
}