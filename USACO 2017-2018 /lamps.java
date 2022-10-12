/*
ID: awesome25
LANG: JAVA
TASK: lamps
*/
import java.io.*;
import java.util.*;

class lamps {
	
	public static boolean isConsistent(ArrayList<Integer> on, ArrayList<Integer> off, String ans) {
		for (int i = 0; i < on.size(); i++) {
			if (ans.charAt(on.get(i)) != '1') return false;
		}
		for (int i = 0; i < off.size(); i++) {
			if (ans.charAt(off.get(i)) != '0') return false;
		}
		return true;
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		int numLamps = Integer.parseInt(in.readLine()), counter = Integer.parseInt(in.readLine());
		ArrayList<Integer> on = new ArrayList<Integer>();
		ArrayList<Integer> off = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int temp = Integer.parseInt(st.nextToken()) - 1;
		while (temp != -2) {
			on.add(temp);
			temp = Integer.parseInt(st.nextToken()) - 1;
		}
		StringTokenizer st1 = new StringTokenizer(in.readLine());
		temp = Integer.parseInt(st1.nextToken()) - 1;
		while (temp != -2) {
			off.add(temp);
			temp = Integer.parseInt(st1.nextToken()) - 1;
		}
		if (counter == 0) {
			String ans = "";
			for (int i = 0; i < numLamps; i++) {
				ans += "1";
			}
			if (off.size() == 0) out.println(ans);
			else out.println("IMPOSSIBLE");
		} else if (counter == 1) {
			String ans = "";
			boolean check = false;
			for (int i = 0; i < numLamps; i++) {
				ans += "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				if (i % 2 == 1) ans = ans + "1";
				else ans = ans + "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				if (i % 3 == 0) ans = ans + "0";
				else ans = ans + "1";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				if (i % 2 == 0) ans = ans + "1";
				else ans = ans + "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			if (!check) out.println("IMPOSSIBLE");
		} else if (counter >= 2) {
			String ans = "";
			boolean check = false;
			for (int i = 0; i < numLamps; i++) {
				ans += "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				if (i % 3 == 0 ^ i % 2 == 0) ans = ans + "1";
				else ans = ans + "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				if (i % 2 == 1) ans = ans + "1";
				else ans = ans + "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			if (counter > 2) {
				for (int i = 0; i < numLamps; i++) {
					if (i % 3 == 0) ans = ans + "0";
					else ans = ans + "1";
				}
				if (isConsistent(on, off, ans)) {
					out.println(ans);
					check = true;
				}
				ans = "";
			}
			for (int i = 0; i < numLamps; i++) {
				if (i % 3 == 0) ans = ans + "1";
				else ans = ans + "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				if (i % 2 == 0) ans = ans + "1";
				else ans = ans + "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				if (i % 3 == 0 ^ i % 2 == 1) ans = ans + "1";
				else ans = ans + "0";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			for (int i = 0; i < numLamps; i++) {
				ans += "1";
			}
			if (isConsistent(on, off, ans)) {
				out.println(ans);
				check = true;
			}
			ans = "";
			if (!check) out.println("IMPOSSIBLE");
		}
		in.close();
		out.close();
	}
}