import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class googol {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<BigInteger> a = new ArrayList<BigInteger>();
		BigInteger zero = new BigInteger("0"), curr = new BigInteger("1"), one = new BigInteger("1"), two = new BigInteger("2");
		while (curr.compareTo(zero) != 0) {
			a.add(curr);
			System.out.println(curr);
			StringTokenizer st = new StringTokenizer(in.readLine());
			curr = new BigInteger(st.nextToken());
		}
		BigInteger[] pow2 = new BigInteger[105];
		pow2[0] = one;
		for (int i = 1; i < 105; i++) {
			pow2[i] = pow2[i - 1].multiply(two);
		}
		BigInteger size = new BigInteger("1");
		for (int i = a.size() - 1; i > 0; i--) {
			size = size.multiply(two);
			size = size.add(one);
			BigInteger root = a.get(i - 1);
			int depth = a.size() - i;
			BigInteger leaves = size.subtract(pow2[depth].subtract(one));
			leaves = leaves.subtract(one);
			ArrayList<Integer> binary = new ArrayList<Integer>();
			while (leaves.compareTo(zero) != 0) {
				BigInteger m = leaves.mod(two);
				binary.add(m.intValue());
				leaves = leaves.divide(two);
			}
			while (binary.size() < depth) {
				binary.add(0, 0);
			}
			for (int j = binary.size() - 1; j >= 0; j--) {
				System.out.println(root);
				StringTokenizer st = new StringTokenizer(in.readLine());
				BigInteger left = new BigInteger(st.nextToken()), right = new BigInteger(st.nextToken());
				if (binary.get(j) == 0) {
					root = left;
				} else {
					root = right;
				}
			}
			if (root.compareTo(zero) == 0) {
				size = size.subtract(one);
			}
		}
		System.out.println("Answer " + size.toString());
		in.close();
	}
	
}