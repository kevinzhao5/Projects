/*
ID: awesome25
LANG: JAVA
TASK: namenum
*/
import java.io.*;

class namenum {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader inName = new BufferedReader(new FileReader("dict.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		long number = Long.parseLong(in.readLine());
		boolean check = false;
		for (int i = 0; i < 4617; i++) {
			String name = inName.readLine(), translation = "";
			for (int x = 0; x < name.length(); x++) {
				switch(name.charAt(x)) {
				case('A'): case('B'): case('C'): translation += 2; break;
				case('D'): case('E'): case('F'): translation += 3; break;
				case('G'): case('H'): case('I'): translation += 4; break;
				case('J'): case('K'): case('L'): translation += 5; break;
				case('M'): case('N'): case('O'): translation += 6; break;
				case('P'): case('R'): case('S'): translation += 7; break;
				case('T'): case('U'): case('V'): translation += 8; break;
				case('W'): case('X'): case('Y'): translation += 9; break;
				}
			}
			if (Long.parseLong(translation) == number) {
				out.println(name);
				check = true;
			}
		}
		if (!check) out.println("NONE");
		in.close();
		inName.close();
		out.close();
	}
}