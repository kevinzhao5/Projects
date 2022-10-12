/*
ID: awesome25
LANG: JAVA
TASK: ttwo
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")

class pt {
	
	int x, y;
	
	public pt(int x2, int y2) {
		x = x2;
		y = y2;
	}
	
}

class ttwo {
	
	public static void print(boolean[][] map, pt f, pt c) {
		for (int i = 0; i < 10; i++) {
			for (int x = 0; x < 10; x++) {
				if (f.x == i && f.y == x) System.out.print("F");
				else if (c.x == i && c.y == x) System.out.print("C");
				else if (map[i][x]) System.out.print(".");
				else System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		pt[] f = new pt[10000];
		pt[] c = new pt[10000];
		boolean[][] map = new boolean[10][10];
		int counter = 0, fd = 0, cd = 0;
		for (int i = 0; i < 10; i++) {
			String str = in.readLine();
			for (int x = 0; x < 10; x++) {
				if (str.charAt(x) == '.') map[i][x] = true;
				else if (str.charAt(x) == '*') map[i][x] = false;
				else if (str.charAt(x) == 'F') {
					map[i][x] = true;
					f[0] = new pt(i, x);
				} else if (str.charAt(x) == 'C') {
					map[i][x] = true;
					c[0] = new pt(i, x);
				}
			}
		}
		while (counter < 9999) {
			switch(fd) {
			case(0):
				if (f[counter].x > 0 && map[f[counter].x - 1][f[counter].y]) f[counter + 1] = new pt(f[counter].x - 1, f[counter].y);
				else {
					fd = 1;
					f[counter + 1] = f[counter];
				}
				break;
			case(1):
				if (f[counter].y < 9 && map[f[counter].x][f[counter].y + 1]) f[counter + 1] = new pt(f[counter].x, f[counter].y + 1);
				else {
					fd = 2;
					f[counter + 1] = f[counter];
				}
				break;
			case(2):
				if (f[counter].x < 9 && map[f[counter].x + 1][f[counter].y]) f[counter + 1] = new pt(f[counter].x + 1, f[counter].y);
				else {
					fd = 3;
					f[counter + 1] = f[counter];
				}
				break;
			case(3):
				if (f[counter].y > 0 && map[f[counter].x][f[counter].y - 1]) f[counter + 1] = new pt(f[counter].x, f[counter].y - 1);
				else {
					f[counter + 1] = f[counter];
					fd = 0;
				}
				break;
			}
			switch(cd) {
			case(0):
				if (c[counter].x > 0 && map[c[counter].x - 1][c[counter].y]) c[counter + 1] = new pt(c[counter].x - 1, c[counter].y);
				else {
					c[counter + 1] = c[counter];
					cd = 1;
				}
				break;
			case(1):
				if (c[counter].y < 9 && map[c[counter].x][c[counter].y + 1]) c[counter + 1] = new pt(c[counter].x, c[counter].y + 1);
				else {
					c[counter + 1] = c[counter];
					cd = 2;
				}
				break;
			case(2):
				if (c[counter].x < 9 && map[c[counter].x + 1][c[counter].y]) c[counter + 1] = new pt(c[counter].x + 1, c[counter].y);
				else {
					c[counter + 1] = c[counter];
					cd = 3;
				}
				break;
			case(3):
				if (c[counter].y > 0 && map[c[counter].x][c[counter].y - 1]) c[counter + 1] = new pt(c[counter].x, c[counter].y - 1);
				else {
					c[counter + 1] = c[counter];
					cd = 0;
				}
				break;
			}
			counter++;
			if (c[counter].x == f[counter].x && c[counter].y == f[counter].y) break;
		}
		if (counter == 9999) out.println(0);
		else out.println(counter);
		in.close();
		out.close();
	}
	
}