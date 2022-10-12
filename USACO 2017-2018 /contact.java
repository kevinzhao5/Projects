/*
ID: awesome25
LANG: JAVA
TASK: contact
*/
import java.io.*;
import java.util.*;

class st {
	
	String str;
	int n;
	
	public st(String str1, int n1) {
		str = str1;
		n = n1;
	}
	
}

class LengthFirstComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {             
        if (o1.length()!=o2.length()) {
            return o1.length()-o2.length();
        }
        return o1.compareTo(o2);
    }
}


class contact {
	
	static HashMap<String, st> map = new HashMap<String, st>();

	public static void fill(int i, String s) {
		if (i == 0) map.put(s, new st(s, i));
		else {
			fill(i - 1, s + "0");
			fill(i - 1, s + "1");
		}
	}
	
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
		StringBuffer str = new StringBuffer("");
		while (in.ready()) str.append(in.readLine());
		for (int i = a; i <= b; i++) {
			fill(i, "");
		}
		int len = str.length();
		for (int i = 0; i < len; i++) {
			for (int x = a; x <= b; x++) {
				if (i + x > len) continue;
				else {
					map.put(str.substring(i, i + x), new st(str.substring(i, i + x), map.get(str.substring(i, i + x)).n + 1));
				}
			}
		}
		ArrayList<st> ints = new ArrayList<st>(map.values());
		Collections.sort(ints, new Comparator<st>() {
	        public int compare(st o1, st o2) {
	            return o2.n - o1.n;
	        }
	    });
		int curr = 0, counter = 0;
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < ints.size(); i++) {
			if (curr != ints.get(i).n) {
				Collections.sort(temp, new LengthFirstComparator());
				for (int x = 1; x <= temp.size(); x++) {
					out.print(temp.get(x - 1));
					if (x % 6 == 0 || x == temp.size()) out.println();
					else out.print(" ");
				}
				curr = ints.get(i).n;
				counter++;
				temp = new ArrayList<String>();
				if (counter > n || curr == 0) break;
				out.println(curr);
			}
			temp.add(ints.get(i).str);
		}
		Collections.sort(temp, new LengthFirstComparator());
		for (int x = 1; x <= temp.size(); x++) {
			out.print(temp.get(x - 1));
			if (x % 6 == 0 || x == temp.size()) out.println();
			else out.print(" ");
		}
		in.close();
		out.close();
	}
	
}