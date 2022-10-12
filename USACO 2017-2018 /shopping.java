/*
ID: awesome25
LANG: JAVA
TASK: shopping
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class shopping {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		int numOffers = Integer.parseInt(in.readLine());
		for (int i = 0; i < numOffers; i++) {
			
		}
		in.close();
		out.close();
	}
	
}