
public class Recurse {

	public static int recur(int x, int y) {
		if (x > y) return recur(x - 2, y + 1) + 3;
		if (x == y) return recur(x + 1, y - 3) - 4;
		return x + 2 * y;
	}
	
	public static int recur2(int x, int y) {
		if (x > y) return recur2(recur2(y, x), (int) (x / y)) - 1;
		if (x == y) return recur2(x + y, x) + 2;
		return y - x;
	}
	
	public static void main(String[] args) {
		System.out.println(recur2(6, 2));
	}

}