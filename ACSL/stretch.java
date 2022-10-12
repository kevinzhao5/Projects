import java.util.*;
import java.io.*;

class q implements Comparable<q> {
	
	int a, b;
	
	public q(int aa, int bb) {
		a = aa;
		b = bb;
	}

	@Override
	public int compareTo(q o) {
		if (this.a == o.a) return this.b - o.b;
		return this.a - o.a;
	}
	
}

public class stretch {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for (int q = 0; q < 5; q++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
			TreeSet<q> blocked = new TreeSet<q>();
			for (int i = 0; i < n; i++) {
				int temp = Integer.parseInt(st.nextToken());
				blocked.add(new q((temp - 1) / c, (temp - 1) % c));
			}			
			if (s % c == 1) {								
				q[][] pieces = {
						{ new q(0, 1), new q(0, 2), new q(0, 1) },
						{ new q(1, 0), new q(2, 0), new q(1, 0) },
						{ new q(-1, 0), new q(-2, 0), new q(-1, 0) },
						{ new q(1, 0), new q(1, 1), new q(1, 0) },
						{ new q(0, 1), new q(1, 1), new q(2, 1) },
						{ new q(0, 1), new q(1, 1), new q(1, 2) }
				};				
				q[][] nextPos = {
						{ new q(0, 3), new q(0, 3) },
						{ new q(0, 1), new q(2, 1) },
						{ new q(0, 1), new q(-2, 1) },
						{ new q(1, 2), new q(1, 2) },
						{ new q(2, 2), new q(2, 2) },
						{ new q(1, 3), new q(1, 3) }
				};				
				int[] lengths = { 3, 1, 1, 2, 2, 3 };				
				String[] id = { "A", "B", "B", "C", "D", "E" };				
				int curr = 0, col = 0;
				int stop = 0;				
				String ans = "";				
				boolean[][] nextPlace = new boolean[r][c];
				nextPlace[(s - 1) / c][0] = true;				
				while (col != c && stop < 100000) { //placing pieces					
					stop++;					
					if (col == c - 1 && (curr == 1 || curr == 2)) {
						curr++;
						continue;
					}					
					if (col == c - 2 && curr == 4) {
						curr++;
						continue;
					}					
					if (col == 0 && (curr == 1 || curr == 2 || curr == 3)) {
						curr++;
						continue;
					}										
					boolean placed = false;															
					for (int i = 0; i < r; i++) {												
						if (nextPlace[i][col]) {							
							boolean possible = !blocked.contains(new q(i, col)); //can u place it or not							
							for (int j = 0; j < 3; j++) {								
								int a = pieces[curr][j].a, b = pieces[curr][j].b;																
								if (i + a < 0 || i + a >= r || col + b >= c) {
									possible = false;
									break; //out of bounds
								}																
								if (blocked.contains(new q(i + a, col + b))) {
									possible = false;
									break; //can't put it there
								}																
							}														
							if (possible) {																
								placed = true;								
								for (int j = 0; j < r; j++) {
									Arrays.fill(nextPlace[j], false);
								}																
								if (col + lengths[curr] == c) {
									col += lengths[curr];
									break;
								}							
								for (int j = 0; j < 2; j++) {									
									nextPlace[i + nextPos[curr][j].a][col + nextPos[curr][j].b] = true;									
								}																
								if (curr == 1) {
									blocked.add(new q(i + 1, col + 1));
								} else if (curr == 2) {
									blocked.add(new q(i - 1, col + 1));
								} else if (curr == 4) {
									blocked.add(new q(i, col + 2));
									blocked.add(new q(i + 1, col + 2));
								}							
								col += lengths[curr];
								break;								
							}													
						}						
					}					
					if (placed) ans += id[curr];
					if (placed && curr == 1) curr += 2; //special case
					else curr++;					
					curr %= 6;										
				}				
				out.println(ans);				
			} else {				
				q[][] pieces = {
						{ new q(0, -1), new q(0, -2), new q(0, -1) },
						{ new q(-1, 0), new q(-2, 0), new q(-1, 0) },
						{ new q(1, 0), new q(2, 0), new q(1, 0) },
						{ new q(0, -1), new q(-1, -1), new q(0, -1) },
						{ new q(-1, 0), new q(-2, 0), new q(-2, -1) },
						{ new q(0, -1), new q(-1, -1), new q(-1, -2) }
				};			
				q[][] nextPos = {
						{ new q(0, -3), new q(0, -3) },
						{ new q(0, -1), new q(-2, -1) },
						{ new q(0, -1), new q(2, -1) },
						{ new q(-1, -2), new q(-1, -2) },
						{ new q(-2, -2), new q(-2, -2) },
						{ new q(-1, -3), new q(-1, -3) }
				};				
				int[] lengths = { 3, 1, 1, 2, 2, 3 };				
				String[] id = { "A", "B", "B", "C", "D", "E" };				
				int curr = 0, col = c - 1;
				int stop = 0;				
				String ans = "";				
				boolean[][] nextPlace = new boolean[r][c];
				nextPlace[(s - 1) / c][c - 1] = true;				
				while (col >= 0 && stop < 100000) { //placing pieces					
					stop++;					
					if (col == 0 && (curr == 1 || curr == 2)) {
						curr++;
						continue;
					}					
					if (col == 1 && curr == 3) {
						curr++;
						continue;
					}					
					if (col == c - 1 && (curr == 1 || curr == 2 || curr == 4)) {
						curr++;
						continue;
					}										
					boolean placed = false;										
					for (int i = 0; i < r; i++) {											
						if (nextPlace[i][col]) {						
							boolean possible = !blocked.contains(new q(i, col)); //can u place it or not							
							for (int j = 0; j < 3; j++) {								
								int a = pieces[curr][j].a, b = pieces[curr][j].b;																
								if (i + a < 0 || i + a >= r || col + b < 0) {
									possible = false;
									break; //out of bounds
								}								
								if (blocked.contains(new q(i + a, col + b))) {
									possible = false;
									break; //can't put it there
								}																
							}														
							if (possible) {																
								placed = true;								
								for (int j = 0; j < r; j++) {
									Arrays.fill(nextPlace[j], false);
								}																
								if (col - lengths[curr] < 0) {
									col -= lengths[curr];
									break;
								}								
								for (int j = 0; j < 2; j++) {									
									nextPlace[i + nextPos[curr][j].a][col + nextPos[curr][j].b] = true;									
								}																
								if (curr == 1) {
									blocked.add(new q(i - 1, col - 1));
								} else if (curr == 2) {
									blocked.add(new q(i + 1, col - 1));
								} else if (curr == 3) {
									blocked.add(new q(i, col - 2));
								}								
								col -= lengths[curr];								
								break;								
							}														
						}						
					}					
					if (placed) ans += id[curr];
					if (placed && curr == 1) curr += 2; //special case
					else curr++;					
					curr %= 6;																				
				}				
				out.println(ans);				
			}
		}
		in.close();
		out.close();
	}
	
}
