/*
ID: awesome25
LANG: JAVA
TASK: transform
*/
import java.io.*;

class transform {
	
	public static char[][] rotate90(char[][] tiles) {
		char[][] result = new char[tiles.length][tiles.length];
		for (int i = 0; i < tiles.length; i++) {
			for (int x = 0; x < tiles.length; x++) {
				result[x][tiles.length - i - 1] = tiles[i][x];
			}
		}
		return result;
	}
	
	public static boolean equal(char[][] tiles, char[][] newTiles) {
		boolean check = true;
		for (int i = 0; i < tiles.length; i++) {
			for (int x = 0; x < tiles.length; x++) {
				if (tiles[i][x] != newTiles[i][x]) check = false;
			}
		}
		return check;
	}
	
	public static char[][] reflect(char[][] tiles) {
		char[][] result = new char[tiles.length][tiles.length];
		for (int i = 0; i < tiles.length; i++) {
			for (int x = 0; x < tiles.length; x++) {
				result[i][tiles.length - x - 1] = tiles[i][x];
			}
		}
		return result;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		int length = Integer.parseInt(in.readLine()), num = 0;
		char[][] tiles = new char[length][length];
		char[][] newTiles = new char[length][length];
		for (int i = 0; i < length; i++) {
			String temp = in.readLine();
			for (int x = 0; x < length; x++) {
				tiles[i][x] = temp.charAt(x);
			}
		}
		for (int i = 0; i < length; i++) {
			String temp = in.readLine();
			for (int x = 0; x < length; x++) {
				newTiles[i][x] = temp.charAt(x);
			}
		}
		char[][] result = rotate90(tiles);
		if (equal(newTiles, result)) num = 1;
		result = rotate90(result);
		if (equal(newTiles, result) && num == 0) num = 2;
		result = rotate90(result);
		if (equal(newTiles, result) && num == 0) num = 3;
		result = reflect(tiles);
		if (equal(newTiles, result) && num == 0) num = 4;
		for (int i = 0; i < 3; i++) {
			result = rotate90(result);
			if (equal(newTiles, result) && num == 0) num = 5;
			result = rotate90(result);
			if (equal(newTiles, result) && num == 0) num = 5;
			result = rotate90(result);
			if (equal(newTiles, result) && num == 0) num = 5;
		}
		if (equal(tiles, newTiles) && num == 0) num = 6;
		if (num == 0) num = 7;
		out.println(num);
		in.close();
		out.close();
	}
}