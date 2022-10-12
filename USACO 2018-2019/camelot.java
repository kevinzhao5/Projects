/*
ID: awesome25
LANG: JAVA
TASK: camelot
*/
import java.io.*;
import java.util.*;

class pos {
	
	int x, y;
	
	public pos(int x2, int y2) {
		y = x2;
		x = y2;
	}
	
}

class triple {
	
	int a, b, c;
	
	public triple(int a1, int b1, int c1) {
		a = a1;
		b = b1;
		c = c1;
	}
	
}

class camelot {
	
	static int[] r = {-2, 2, -1, 1, -2, 2, -1, 1};
	static int[] c = {-1, -1, -2, -2, 1, 1, 2, 2};
	
	public static int kingDist(int x, int y, int i, int j) {
		int xd = Math.abs(x - i), yd = Math.abs(y - j);
		return Math.min(xd, yd) + Math.abs(xd - yd);
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("src/camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/camelot.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		int kx = 0, ky = 0;
		ArrayList<pos> kn = new ArrayList<pos>();
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		ky = (int)st1.nextToken().charAt(0) - 65;
		kx = R - Integer.parseInt(st1.nextToken());
		while (st1.hasMoreTokens()) {
			kn.add(new pos((int)st1.nextToken().charAt(0) - 65, R - Integer.parseInt(st1.nextToken())));
		}
		while (in.ready()) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			while (s.hasMoreTokens()) {
				kn.add(new pos((int)s.nextToken().charAt(0) - 65, R - Integer.parseInt(s.nextToken())));
			}
		}
		int[][][][] dist = new int[30][30][30][30];
		for (int a = 0; a < 30; a++) {
			for (int b = 0; b < 30; b++) {
				for (int c = 0; c < 30; c++) {
					Arrays.fill(dist[a][b][c], 1000000);
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				Queue<pos> q = new LinkedList<pos>();
				q.add(new pos(j, i));
				dist[i][j][i][j] = 0;
				while (!q.isEmpty()) {
					pos p = q.poll();
					for (int x = 0; x < 8; x++) {
						if (p.x + r[x] >= 0 && p.x + r[x] < R && p.y + c[x] >= 0 && p.y + c[x] < C && dist[i][j][p.x + r[x]][p.y + c[x]] == 1000000) {
							dist[i][j][p.x + r[x]][p.y + c[x]] = dist[i][j][p.x][p.y] + 1;
							q.add(new pos(p.y + c[x], p.x + r[x]));
							//System.out.println(i + " " + j + " " + (p.x + r[x]) + " " + (p.y + c[x]) + " " + dist[i][j][p.x + r[x]][p.y + c[x]]);
						}
					}
				}
			}
		}
		triple[] kmoves = new triple[kn.size()];
		for (int x = 0; x < kn.size(); x++) {
			pos p = kn.get(x);
			int min = Integer.MAX_VALUE, finx = 0, finy = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int cost = kingDist(kx, ky, i, j) + dist[p.x][p.y][i][j];
					if (cost <= min) {
						min = cost;
						finx = i;
						finy = j;
					}
				}
			}
			kmoves[x] = new triple(finx, finy, min);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int sum = 0;
				for (int x = 0; x < kn.size(); x++) {
					pos p = kn.get(x);
					sum += dist[p.x][p.y][i][j];
					//System.out.println(i + " " + j + " " + sum + " " + dist[p.x][p.y][i][j] + " " + p.x + " " + p.y);
				}
				int sum1 = sum;
				sum1 += kingDist(kx, ky, i, j);
				for (int x = 0; x < kn.size(); x++) {
					pos p = kn.get(x);
					sum1 = Math.min(sum1, sum - dist[p.x][p.y][i][j] + kmoves[x].c + dist[kmoves[x].a][kmoves[x].b][i][j]);
				}
				min = Math.min(min, sum1);
			}
		}
		out.println(min);
		in.close();
		out.close();
	}
	
}