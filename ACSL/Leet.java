import java.util.*;
import java.io.*;

public class Leet {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/leet"));
		for (int w = 0; w < 5; w++) {
			String input = in.nextLine();
			for (int i = 0; i < input.length(); i++) {
				if(input.charAt(i) == 'A'){
					System.out.print("4");
				}
				else if(input.charAt(i) == 'B'){
					System.out.print("8");
				}
				else if(input.charAt(i) == 'C'){
					System.out.print("[");
				}
				else if(input.charAt(i) == 'D'){
					boolean check = true;
					if (i == input.length() - 2) {
						if (input.length() > 2) if (input.charAt(i - 1) == 'N' && input.charAt(i - 2) == 'A') {
							check = false;
							System.out.print("&");
						}
						else if (input.length() > 3) if (input.charAt(i - 1) == 'E' && input.charAt(i - 2) == 'N' && input.charAt(i - 3) == 'N' && input.charAt(i - 4) == 'A') {
							check = false;
							System.out.print("&");
						}
						else if (input.charAt(i - 1) == 'E') {
							check = false;
							System.out.print("*D");
						}
					} else if (i != input.length() - 1) {
						if (input.charAt(i + 1) == ' ') {
							if (input.length() > 2) if (input.charAt(i - 1) == 'N' && input.charAt(i - 2) == 'A') {
								check = false;
								System.out.print("&");
							}
							else if (input.length() > 3) if (input.charAt(i - 1) == 'E' && input.charAt(i - 2) == 'N' && input.charAt(i - 3) == 'N' && input.charAt(i - 4) == 'A') {
								check = false;
								System.out.print("&");
							}
							else if (input.charAt(i - 1) == 'E') {
								check = false;
								System.out.print("*D");
							}
						}
					}
					if (check) System.out.print(")");
				}
				else if(input.charAt(i) == 'E'){
					boolean check = true;
					if (i == input.length() - 2) {
						if (input.charAt(i + 1) == 'R' || input.charAt(i + 1) == 'D') check = false;
					} else if (i < input.length() - 1) {
						if ((input.charAt(i + 1) == 'R' || input.charAt(i + 1) == 'D') && input.charAt(i + 2) == ' ') {
							check = false;
							System.out.print("LKLKLK");
						}
					}
					if (check) System.out.print("3");
				}
				else if(input.charAt(i) == 'F'){
					System.out.print("|=");
				}
				else if(input.charAt(i) == 'G'){
					System.out.print("6");
				}
				else if(input.charAt(i) == 'H'){
					System.out.print("#");
				}
				else if(input.charAt(i) == 'I'){
					System.out.print("|");
				}
				else if(input.charAt(i) == 'J'){
					System.out.print("]");
				}
				else if(input.charAt(i) == 'K'){
					System.out.print("|<");
				}
				else if(input.charAt(i) == 'L'){
					System.out.print("I");
				}
				else if(input.charAt(i) == 'M'){
					System.out.print("^^");
				}
				else if(input.charAt(i) == 'N'){
					System.out.print("^");
				}
				else if(input.charAt(i) == 'O'){
					System.out.print("0");
				}
				else if(input.charAt(i) == 'P'){
					System.out.print("|o");
				}
				else if(input.charAt(i) == 'Q'){
					System.out.print("9");
				}
				else if(input.charAt(i) == 'R'){
					boolean check = true;
					if (i == input.length() - 2) {
						if (input.charAt(i - 1) == 'E') {
							System.out.print("XOR");
							check = false;
						}
						else if (input.charAt(i - 1) == 'O') {
							check = false;
							System.out.print("ZOR");
						}
					} else if (i != input.length() - 1) {
						if (input.charAt(i + 1) == ' ') {
							if (input.charAt(i - 1) == 'E') {
								check = false;
								System.out.print("XOR");
							}
							else if (input.charAt(i - 1) == 'O') {
								check = false;
								System.out.print("ZOR");
							}
						}
					}
					if (check) System.out.print("2");
				}
				else if(input.charAt(i) == 'S'){
					System.out.print("5");
				}
				else if(input.charAt(i) == 'T'){
					boolean check = true;
					if (i == input.length() - 2) {
						if (input.length() > 2) if (input.charAt(i - 1) == 'N' && input.charAt(i - 2) == 'A') {
							check = false;
							System.out.print("&");
						}
					} else if (i != input.length() - 1) {
						if (input.charAt(i + 1) == ' ') {
							if (input.length() > 2) if (input.charAt(i - 1) == 'N' && input.charAt(i - 2) == 'A') {
								check = false;
								System.out.print("&");
							}
						}
					}
					if (check) System.out.print("7");
				}
				else if(input.charAt(i) == 'U'){
					System.out.print("(_)");
				}
				else if(input.charAt(i) == 'V'){
					System.out.print("\\/");
				}
				else if(input.charAt(i) == 'W'){
					System.out.print("vv");
				}
				else if(input.charAt(i) == 'X'){
					System.out.print("><");
				}
				else if(input.charAt(i) == 'Y'){
					System.out.print("'/");
				}
				else if(input.charAt(i) == 'Z'){
					System.out.print("%");
				}
				else{
					System.out.print(input.charAt(i));
				}
			}
			/*String bagels = in.nextLine();
			String bagels1 = bagels;
			String[] mongoose=new String[bagels.length()];
			mongoose=bagels.split("");
			int spooces=0;
			for(int i=0;i<mongoose.length;i++){
				if(mongoose[i].equals(" ")){
					spooces++;
				}
			}
			// spooces=amount of spaces;
			String[] Cheese=new String[spooces+1];
			Cheese=bagels1.split(" ");
			for(int i=0;i<spooces;i++){
				String okay=Cheese[i];
				String[] huh= new String[Cheese[i].length()];
				huh=okay.split("");
				for(int j=0; j<huh.length;j++){
					if(input.charAt(i).equals("A")){
						System.out.print("4");
					}
					if(input.charAt(i).equals("B")){
						System.out.print("8");
					}
					if(input.charAt(i).equals("C")){
						System.out.print("[");
					}
					if(input.charAt(i).equals("D")){
						System.out.print(")");
					}
					if(input.charAt(i).equals("E")){
						System.out.print("3");
					}
					if(input.charAt(i).equals("F")){
						System.out.print("|=");
					}
					if(input.charAt(i).equals("G")){
						System.out.print("6");
					}
					if(input.charAt(i).equals("H")){
						System.out.print("#");
					}
					if(input.charAt(i).equals("I")){
						System.out.print("|");
					}
					if(input.charAt(i).equals("J")){
						System.out.print("]");
					}
					if(input.charAt(i).equals("K")){
						System.out.print("|<");
					}
					if(input.charAt(i).equals("L")){
						System.out.print("I");
					}
					if(input.charAt(i).equals("M")){
						System.out.print("^^");
					}
					if(input.charAt(i).equals("N")){
						System.out.print("^");
					}
					if(input.charAt(i).equals("O")){
						System.out.print("0");
					}
					if(input.charAt(i).equals("P")){
						System.out.print("|o");
					}
					if(input.charAt(i).equals("Q")){
						System.out.print("9");
					}
					if(input.charAt(i).equals("R")){
						System.out.print("2");
					}
					if(input.charAt(i).equals("S")){
						System.out.print("5");
					}
					if(input.charAt(i).equals("T")){
						System.out.print("7");
					}
					if(input.charAt(i).equals("U")){
						System.out.print("(_)");
					}
					if(input.charAt(i).equals("V")){
						System.out.print("\\/");
					}
					if(input.charAt(i).equals("W")){
						System.out.print("vv");
					}
					if(input.charAt(i).equals("X")){
						System.out.print("><");
					}
					if(input.charAt(i).equals("Y")){
						System.out.print("'/");
					}
					if(input.charAt(i).equals("Z")){
						System.out.print("%");
					}
					else{
						System.out.print(input.charAt(i));
					}
					//insert special cases
				}
			}
			
			/*for (int i = 0; i < bagels.length(); i++) {
				if (bagels.charAt(i) == ' ' || i == bagels.length() - 1) {
					if (i > 1) {
						String temp = bagels.substring(i - 2, i);
						
					}
				}
			}
			*/
			System.out.println();
		}
		in.close();
	}
}