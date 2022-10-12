import java.util.*;
import java.io.*;

public class Pick {

	public static boolean isIn(char[] cols, int n) {
		for (int i = 0; i < cols.length; i++) {
			if ((int) (cols[i]) - 64 == n) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/pick"));
		in.useDelimiter("\\s");
		for (int q = 0; q < 5; q++) {
			int max = in.nextInt(), num = in.nextInt();
			char[] cols = new char[num];
			for (int i = 0; i < num; i++) {
				cols[i] = in.next().charAt(0);
			}
			in.nextLine();
			int firstCol = (int) (cols[0]) - 64;
			Arrays.sort(cols);
			String ans = "";
			for (int i = (int) (cols[num - 1]) - 64; i > 0; i--) {
				if (isIn(cols, i)) ans += 1;
				else ans += 0;
			}
			System.out.print(Integer.parseInt(ans, 2) + ", ");
			int count = 0;
			for (int i = 1; i <= max; i++) {
				String str = Integer.toBinaryString(i);
				if (str.length() >= firstCol && str.charAt(str.length() - firstCol) == '1') count++;
			}
			System.out.println(count);
		}
		in.close();
	}

}