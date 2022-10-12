import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class ContestTemplate {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("ADD TASK NAME HERE.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ADD TASK NAME HERE.out")));

StringTokenizer st = new StringTokenizer(in.readLine());
int x = Integer.parseInt(st.nextToken());

		in.close();
		out.close();
	}
	
}