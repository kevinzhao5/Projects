import java.util.*;
import java.io.*;

public class MailDelivery {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/mail"));
		in.useDelimiter("\\s");
		int temp = in.nextInt();
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		ArrayList<Integer> d = new ArrayList<Integer>();
		for (int i = 0; i < temp; i++) {
			String str = in.next();
			switch(str.charAt(0)){
			case('A'):a.add(Integer.parseInt(str.substring(1, str.length()))); break;
			case('B'):b.add(Integer.parseInt(str.substring(1, str.length()))); break;
			case('C'):c.add(Integer.parseInt(str.substring(1, str.length()))); break;
			case('D'):d.add(Integer.parseInt(str.substring(1, str.length()))); break;
			}
		}
		for (int f = 0; f < 5; f++) {
			temp = in.nextInt();
			for (int i = 0; i < temp; i++) {
				String str = in.next();
				switch(str.charAt(0)){
				case('A'):a.add(Integer.parseInt(str.substring(1, str.length()))); break;
				case('B'):b.add(Integer.parseInt(str.substring(1, str.length()))); break;
				case('C'):c.add(Integer.parseInt(str.substring(1, str.length()))); break;
				case('D'):d.add(Integer.parseInt(str.substring(1, str.length()))); break;
				}
			}
			char gate = in.next().charAt(0);
			int index = in.nextInt();
			in.nextLine();
			Collections.sort(a);
			Collections.sort(b);
			Collections.sort(c);
			Collections.sort(d);
			ArrayList<String> ans = new ArrayList<String>();
			for (int i = 0; i < 4; i++) {
				switch(gate) {
				case('A'):
					for (int x = 0; x < a.size(); x++) {
						if (a.get(x) % 2 == 1) ans.add("A" + a.get(x));
					}
					break;
				case('B'):
					for (int x = 0; x < b.size(); x++) {
						if (b.get(x) % 2 == 1) ans.add("B" + b.get(x));
					}
					break;
				case('C'):
					for (int x = 0; x < c.size(); x++) {
						if (c.get(x) % 2 == 1) ans.add("C" + c.get(x));
					}
					break;
				case('D'):
					for (int x = 0; x < d.size(); x++) {
						if (d.get(x) % 2 == 1) ans.add("D" + d.get(x));
					}
					break;
				}
				switch(gate) {
				case('A'):gate = 'B'; break;
				case('B'):gate = 'C'; break;
				case('C'):gate = 'D'; break;
				case('D'):gate = 'A'; break;
				}
			}
			for (int i = 0; i < 4; i++) {
				switch(gate) {
				case('A'):
					for (int x = 0; x < a.size(); x++) {
						if (a.get(x) % 2 == 0) ans.add("A" + a.get(x));
					}
					break;
				case('B'):
					for (int x = 0; x < b.size(); x++) {
						if (b.get(x) % 2 == 0) ans.add("B" + b.get(x));
					}
					break;
				case('C'):
					for (int x = 0; x < c.size(); x++) {
						if (c.get(x) % 2 == 0) ans.add("C" + c.get(x));
					}
					break;
				case('D'):
					for (int x = 0; x < d.size(); x++) {
						if (d.get(x) % 2 == 0) ans.add("D" + d.get(x));
					}
					break;
				}
				switch(gate) {
				case('A'):gate = 'B'; break;
				case('B'):gate = 'C'; break;
				case('C'):gate = 'D'; break;
				case('D'):gate = 'A'; break;
				}
			}
			System.out.println(ans.get(index - 1));
		}
		in.close();
	}

}