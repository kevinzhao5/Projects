import java.util.*;
import java.io.*;
public class Truth_tables {
	

	 public static void printLabels(String Statement) {
		//Paremeters as String Statement which is the boolean statement
		   ArrayList<Character> Statement_fragments=new ArrayList<Character>();
		   ArrayList<Character> var = new ArrayList<Character>();
		   boolean parenthesis = false;
		   for (int x = 0; x < Statement.length(); x++) {
			   if (Statement.charAt(x) != ' ') {
				   Statement_fragments.add(Statement.charAt(x));
			   }
		   }
		   for (int x = 0; x < Statement_fragments.size(); x++) {
			   if ((int)Statement_fragments.get(x) >= 65 && (int)Statement_fragments.get(x) <= 90) {
				   boolean check = true;
				   for (int q = 0; q < var.size(); q++) {
					   if (Statement_fragments.get(x) == var.get(q)) {
						   check = false;
					   }
				   }
				   if (check) {
					   var.add(Statement_fragments.get(x));
				   	   System.out.print(Statement_fragments.get(x) + " ");
			   	   }
			   }
		   }
		   for (int x = 0; x < Statement_fragments.size(); x++) {
			   if (Statement_fragments.get(x) == '(') {
				   int q = x;
				   parenthesis = true;
				   while (Statement_fragments.get(q + 1) != ')') {
					   q++;
					   System.out.print(Statement_fragments.get(q));
				   }
				   System.out.print(" ");
			   }
		   }
		   if (parenthesis) {
			   parenthesis = false;
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '(') {
					   parenthesis = true;
				   } else if (Statement_fragments.get(x) == ')') {
					   parenthesis = false;
				   }
				   if ((Statement_fragments.get(x) == '@') && !parenthesis) {
					   System.out.print(Statement_fragments.get(x) + " ");
				   }
			   }
			   parenthesis = false;
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '(') {
					   parenthesis = true;
				   } else if (Statement_fragments.get(x) == ')') {
					   parenthesis = false;
				   }
				   if (Statement_fragments.get(x) == '*' && !parenthesis) {
					   System.out.print(Statement_fragments.get(x) + " ");
				   }
			   }
			   parenthesis = false;
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '(') {
					   parenthesis = true;
				   } else if (Statement_fragments.get(x) == ')') {
					   parenthesis = false;
				   }
				   if ((Statement_fragments.get(x) == '+') && !parenthesis) {
					   System.out.print(Statement_fragments.get(x) + " ");
				   }
			   }
			   parenthesis = false;
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '(') {
					   parenthesis = true;
				   } else if (Statement_fragments.get(x) == ')') {
					   parenthesis = false;
				   }
				   if ((Statement_fragments.get(x) == '#') && !parenthesis) {
					   System.out.print(Statement_fragments.get(x) + " ");
				   }
			   }
		   } else {
			   boolean[] checks = new boolean[Statement_fragments.size()];
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '@') {
					   System.out.print("@" + Statement_fragments.get(x + 1) + " ");
					   checks[x + 1] = true;
				   }
			   }
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '*') {
					   if (!checks[x - 1] && !checks[x + 1]) {
						   checks[x - 1] = true;
						   checks[x + 1] = true;
						   System.out.print(Statement_fragments.get(x - 1) + "*" + Statement_fragments.get(x + 1) + " ");
					   } else {
						   System.out.print(Statement_fragments.get(x));
						   if (!checks[x - 1]) {
							   System.out.print(Statement_fragments.get(x - 1));
							   checks[x - 1] = true;
						   } else if (!checks[x + 1]) {
							   System.out.print(Statement_fragments.get(x + 1));
							   checks[x + 1] = true;
						   }
						   System.out.print(" ");
					   }
				   }
			   }
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '+') {
					   if (!checks[x - 1] && !checks[x + 1]) {
						   checks[x - 1] = true;
						   checks[x + 1] = true;
						   System.out.print(Statement_fragments.get(x - 1) + "+" + Statement_fragments.get(x + 1) + " ");
					   } else {
						   System.out.print(Statement_fragments.get(x));
						   if (!checks[x - 1]) {
							   System.out.print(Statement_fragments.get(x - 1));
							   checks[x - 1] = true;
						   } else if (!checks[x + 1]) {
							   System.out.print(Statement_fragments.get(x + 1));
							   checks[x + 1] = true;
						   }
						   System.out.print(" ");
					   }
				   }
			   }
			   for (int x = 0; x < Statement_fragments.size(); x++) {
				   if (Statement_fragments.get(x) == '#') {
					   if (!checks[x - 1] && !checks[x + 1]) {
						   checks[x - 1] = true;
						   checks[x + 1] = true;
						   System.out.print(Statement_fragments.get(x - 1) + "#" + Statement_fragments.get(x + 1) + " ");
					   } else {
						   System.out.print(Statement_fragments.get(x));
						   if (!checks[x - 1]) {
							   System.out.print(Statement_fragments.get(x - 1));
							   checks[x - 1] = true;
						   } else if (!checks[x + 1]) {
							   System.out.print(Statement_fragments.get(x + 1));
							   checks[x + 1] = true;
						   }
						   System.out.print(" ");
					   }
				   }
			   }
		   }
		   System.out.println();
	 }
	
 public static void main(String[]args) throws IOException{
	 
  Scanner infile = new Scanner(new FileReader("src/anything that end with"));
     infile.useDelimiter(",|\\s");
  for(int i=0;i<10;i++){
   String Statement=infile.nextLine();
   printLabels(Statement);
  }
  infile.close();
 }
}
