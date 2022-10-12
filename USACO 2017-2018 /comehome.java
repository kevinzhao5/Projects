/*
ID: awesome25
LANG: JAVA
TASK: comehome
*/
import java.io.*;
import java.util.*;

class comehome {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		int num = Integer.parseInt(in.readLine());
		char[] chars = new char[52];
		Arrays.fill(chars, ' ');
		int[][] matrix = new int[52][52];
		int[] dist = new int[52];
		boolean[] visited = new boolean[52];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 0; i < 52; i++) {
			Arrays.fill(matrix[i], 1000000);
		}
		int numVisited = 1, size = 0;
		for (int i = 0; i < num; i++) {
			String str = in.readLine();
			char char1 = str.charAt(0), char2 = str.charAt(2);
			if ((int) (char1) >= 97) char1 -= 97;
			else char1 -= 39;
			if ((int) (char2) >= 97) char2 -= 97;
			else char2 -= 39;
			if (chars[char1] == ' ') {
				size++;
				chars[char1] = str.charAt(0);
			}
			if (chars[char2] == ' ') {
				size++;
				chars[char2] = str.charAt(2);
			}
			if (Integer.parseInt(str.substring(4, str.length())) < matrix[char1][char2]) {
				matrix[char1][char2] = Integer.parseInt(str.substring(4, str.length()));
				matrix[char2][char1] = Integer.parseInt(str.substring(4, str.length()));
			}
		}
		dist[51] = 0;
		while (numVisited < size) {
			int min = Integer.MAX_VALUE, index = -1;
			for (int i = 0; i < 52; i++) {
				if (!visited[i] && dist[i] < min) {
					min = dist[i];
					index = i;
				}
			}
			visited[index] = true;
			numVisited++;
			for (int i = 0; i < 52; i++) {
				if (matrix[i][index] > 0) {
					if (dist[i] > matrix[i][index] + dist[index]) {
						dist[i] = matrix[i][index] + dist[index];
					}
				}
			}
		}
		int min = Integer.MAX_VALUE, index = -1;
		for (int i = 26; i < 51; i++) {
			if (dist[i] < min) {
				min = dist[i];
				index = i;
			}
		}
		out.println(chars[index] + " " + min);
		in.close();
		out.close();
	}
	
}