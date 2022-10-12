import java.util.*;
import java.io.*;

public class StickerCollectorRobot {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = -1, M = -1, S = -1;
		while (N != 0 || M != 0 || S != 0) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M  = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0 && S == 0) break;
			char[][] map = new char[N][M];
			int direction = 0, x = 0, y = 0, stickers = 0;
			for (int i = 0; i < N; i++) {
				String s = in.readLine();
				for (int j = 0; j < M; j++) {
					if (s.charAt(j) == 'N') direction = 0;
					else if (s.charAt(j) == 'S') direction = 2;
					else if (s.charAt(j) == 'L') direction = 1;
					else if (s.charAt(j) == 'O') direction = 3;
					else map[i][j] = s.charAt(j);
					if (s.charAt(j) == 'N' || s.charAt(j) == 'S' || s.charAt(j) == 'L' || s.charAt(j) == 'O') {
						x = i;
						y = j;
						map[i][j] = '.';
					}
				}
			}
			String input = in.readLine();
			for (int i = 0; i < S; i++) {
				char c = input.charAt(i);
				if (c == 'D') direction = (direction + 1) % 4;
				else if (c == 'E') {
					direction--;
					if (direction == -1) direction = 3;
				} else if (c == 'F') {
					switch(direction) {
					case(0):
						if (x - 1 >= 0 && map[x - 1][y] != '#') {
							x--;
							if (map[x][y] == '*') {
								stickers++;
								map[x][y] = '.';
							}
						}
						break;
					case(1):
						if (y + 1 < M && map[x][y + 1] != '#') {
							y++;
							if (map[x][y] == '*') {
								stickers++;
								map[x][y] = '.';
							}
						}
						break;
					case(2):
						if (x + 1 < N && map[x + 1][y] != '#') {
							x++;
							if (map[x][y] == '*') {
								stickers++;
								map[x][y] = '.';
							}
						}
						break;
					case(3):
						if (y - 1 >= 0 && map[x][y - 1] != '#') {
							y--;
							if (map[x][y] == '*') {
								stickers++;
								map[x][y] = '.';
							}
						}
						break;
					}
				}
			}
			System.out.println(stickers);
		}
		in.close();
	}

}
