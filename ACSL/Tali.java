import java.util.*;
import java.io.*;

public class Tali {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/tali"));
		in.useDelimiter("\\s");
		for (int t = 0; t < 5; t++) {
			int[] nums = new int[4];
			int[] ranks = new int[4];
			int[] temps = new int[4];
			for (int e = 0; e < 4; e++) {
				String temp = in.next() + " ";
				int temp1 = 0, index = 0;
				for (int i = 0; i < 4; i++) {
					if (temp.charAt(i) - 48 == 6) {
						temp1 = temp1 * 10 + 6;
						temps[index] = 6;
						index++;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (temp.charAt(i) - 48 == 4) {
						temp1 = temp1 * 10 + 4;
						temps[index] = 4;
						index++;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (temp.charAt(i) - 48 == 3) {
						temp1 = temp1 * 10 + 3;
						temps[index] = 3;
						index++;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (temp.charAt(i) - 48 == 1) {
						temp1 = temp1 * 10 + 1;
						temps[index] = 1;
						index++;
					}
				}
				nums[e] = temp1;
				switch(nums[e]) {
				case(6431):ranks[e] = -10000009; break;
				case(6666):ranks[e] = -10000008; break;
				case(6664):ranks[e] = -10000007; break;
				case(6663):ranks[e] = -10000006; break;
				case(6644):ranks[e] = -10000005; break;
				case(6661):ranks[e] = -10000004; break;
				case(6643):ranks[e] = -10000003; break;
				case(6633):ranks[e] = -10000002; break;
				case(6641):ranks[e] = -10000001; break;
				case(6631):ranks[e] = -10000000; break;
				default:ranks[e] = -1 * (temps[0] + temps[1] + temps[2] + temps[3]);
				}
			}
			int lowest = 0;
			String output = "";
			for (int i = 0; i < 4; i++) {
				if (ranks[i] < lowest) lowest = ranks[i];
			}
			if (lowest > -10000000) {
				ArrayList<Integer> n = new ArrayList<Integer>();
				ArrayList<Integer> ind = new ArrayList<Integer>();
				for (int i = 0; i < 4; i++) {
					if (ranks[i] == lowest) {
						n.add(nums[i]);
						ind.add(i);
					}
				}
				for (int i = 0; i < 4; i++) {
					for (int x = 0; x < n.size(); x++) {
						for (int q = x + 1; q < n.size(); q++) {
							if (Integer.toString(n.get(x)).charAt(i) - 48 > Integer.toString(n.get(q)).charAt(i) - 48) {
								//System.out.println((Integer.toString(n.get(x)).charAt(i) - 48) + " " + (Integer.toString(n.get(q)).charAt(i) - 48));
								n.remove(q);
								ind.remove(q);
							}
							else if (Integer.toString(n.get(x)).charAt(i) - 48 < Integer.toString(n.get(q)).charAt(i) - 48) {
								//System.out.println((Integer.toString(n.get(x)).charAt(i) - 48) + " " + (Integer.toString(n.get(q)).charAt(i) - 48));
								n.remove(x);
								ind.remove(x);
							}
						}
					}
				}
				output = (char)(ind.get(0) + 65) + "";
			} else {
				for (int i = 0; i < 4; i++) {
					if (ranks[i] == lowest) output += (char)(i + 65);
				}
			}
			System.out.println(output);
			in.nextLine();
		}
		in.close();
	}

}
