/*
ID: awesome25
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class personGift1 {
	
	int money;
	String name;
	
	public personGift1(String name2, int money2) {
		name = name2;
		money = money2;
	}
}

class gift1 {
	
	static personGift1[] people;
	
	public static int findPerson(String name, personGift1[] people) {
		int index = 0;
		for (int i = 0; i < people.length; i++) {
			if (people[i].name.equals(name)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int num = Integer.parseInt(in.nextLine());
		people = new personGift1[num];
    	for (int i = 0; i < num; i++) {
    		people[i] = new personGift1(in.nextLine(), 0);
    	}
    	for (int i = 0; i < num; i++) {
    		String name2 = in.nextLine(), numbers = in.nextLine();
    		Scanner inFile = new Scanner(numbers);
    		int money = 0, num2 = 0;
    		money = inFile.nextInt();
    		num2 = inFile.nextInt();
    		if (num2 != 0) {
    			people[findPerson(name2, people)].money += money % num2 - money;
    			money -= money % num2;
    		}
    		for (int x = 0; x < num2; x++) {
    			people[findPerson(in.nextLine(), people)].money += money / num2;
    		}
    	}
    	for (int i = 0; i < num; i++) {
    		out.println(people[i].name + " " + people[i].money);
    	}
    	out.close();
	}
}