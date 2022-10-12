/*
ID: awesome25
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
class preface {
	
	static int I = 0, V = 0, X = 0, L = 0, C = 0, D = 0, M = 0;
	
	public static void increment(int n) {
		while (n >= 1000) {
	        M++;
	        n -= 1000;
	    }
	    while (n >= 900) {
	        M++;
	        C++;
	        n -= 900;
	    }
	    while (n >= 500) {
	        D++;
	        n -= 500;
	    }
	    while (n >= 400) {
	        C++;
	        D++;
	        n -= 400;
	    }
	    while (n >= 100) {
	        C++;
	        n -= 100;
	    }
	    while (n >= 90) {
	        X++;
	        C++;
	        n -= 90;
	    }
	    while (n >= 50) {
	        L++;
	        n -= 50;
	    }
	    while (n >= 40) {
	        X++;
	        L++;
	        n -= 40;
	    }
	    while (n >= 10) {
	        X++;
	        n -= 10;
	    }
	    while (n >= 9) {
	        X++;
	        I++;
	        n -= 9;
	    }
	    while (n >= 5) {
	        V++;
	        n -= 5;
	    }
	    while (n >= 4) {
	        I++;
	        V++;
	        n -= 4;
	    }
	    while (n >= 1) {
	        I++;
	        n -= 1;
	    }
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			increment(i + 1);
		}
		if (I > 0) out.println("I " + I);
		if (V > 0) out.println("V " + V);
		if (X > 0) out.println("X " + X);
		if (L > 0) out.println("L " + L);
		if (C > 0) out.println("C " + C);
		if (D > 0) out.println("D " + D);
		if (M > 0) out.println("M " + M);
		in.close();
		out.close();
	}
}