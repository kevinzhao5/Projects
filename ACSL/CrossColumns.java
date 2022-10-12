import java.util.*;
import java.io.*;

public class CrossColumns {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/cross"));
		for (int w = 0; w < 10; w++) {
			int wallWidth = in.nextInt(), middle = in.nextInt(), height = in.nextInt();
			in.nextLine();
			char[][] design = new char[height][wallWidth + middle + wallWidth];
			for (int i = 0; i < height; i++) {
				for (int x = 0; x < design[i].length; x++) {
					design[i][x] = ' ';
				}
			}
			for (int i = 0; i < height; i++) {
				for (int x = 0; x < wallWidth; x++) {
					design[i][x] = 'I';
				}
			}
			for (int x = 0; x < height; x++) {
				for (int i = 1; i < wallWidth - 1; i++) {
					design[x][i] = 'X';
				}
			}
			for (int x = 0; x < height; x++) {
				for (int i = design[x].length - wallWidth; i < design[x].length; i++) {
					design[x][i] = 'I';
				}
			}
			for (int x = 0; x < height; x++) {
				for (int i = design[x].length - wallWidth + 1; i < design[x].length - 1; i++) {
					design[x][i] = 'X';
				}
			}
			int right = wallWidth, left = design[0].length - wallWidth - 1;
			boolean rC = false, lC = false;
			for (int i = 0; i < height; i++) {
				for (int x = wallWidth; x < design[i].length - wallWidth; x++) {
					if (design[i][x] == ' ' && !rC && x == right) {
						rC = true;
						design[i][x] = 'R';
						right++;
					} else if (design[i][x] == 'L' && !rC && x == right) {
						rC = true;
						design[i][x] = 'S';
						right++;
					}
					if (design[i][design[i].length - 1 - x] == ' ' && !lC && design[i].length - 1 - x == left) {
						lC = true;
						design[i][design[i].length - 1 - x] = 'L';
						left--;
					} else if (design[i][design[i].length - 1 - x] == 'R' && !lC && design[i].length - 1 - x == left) {
						lC = true;
						design[i][design[i].length - 1 - x] = 'S';
						left--;
					}
				}
				rC = false;
				lC = false;
			}
			for (int i = 0; i < height; i++) {
				for (int x = 0; x < design[i].length; x++) {
					System.out.print(design[i][x]);
				}
				System.out.println();
			}
			System.out.println();
		}
		in.close();
	}

}