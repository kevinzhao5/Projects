
public class Recursion {
	
	public static int recurse(int x, int y) {
		if (x > 1) return recurse(recurse(y - 3, x - 2), recurse(y - 1, x));
		return x - y;
	}

	public static void main(String[] args) {
		System.out.println(recurse(4, 5));
	}

}
