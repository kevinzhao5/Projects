/*
ID: awesome25
LANG: JAVA
TASK: ride
*/
import java.io.*;

class ride {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    String i1 = f.readLine(), i2 = f.readLine();
    int i1num = 1, i2num = 1;
    for (int i = 0; i < i1.length(); i++) {
    	i1num *= (i1.charAt(i) - 64);
    }
    for (int i = 0; i < i2.length(); i++) {
    	i2num *= (i2.charAt(i) - 64);
    }
    if (i1num % 47 == i2num % 47) out.println("GO");
    else out.println("STAY");
    out.close();
  }
}