import java.util.*;

public class stringBuffer {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		//while (str.length() < 10) str += 0;
		str = str + "00000000".substring(str.length());
		System.out.println(str);
		ArrayList<Integer> ints = new ArrayList<Integer>(); ints.add(0); ints.add(1); ints.add(2);
		int[] f = new int[8];
		Arrays.fill(f, 3);
		Arrays.sort(f);
	}

}