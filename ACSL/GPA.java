import java.util.*;

class student {
	
	double GPA;
	String name;
	
	public student(double gpa2, String name2) {
		GPA = gpa2;
		name = name2;
	}
	
}

public class GPA {
	
	public static void main(String[] args) {
		student[] students = new student[100];
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			String name = "";
			for (int x = 0; x < 5; x++) {
				name += (char) (rand.nextInt(26) + 65);
			}
			students[i] = new student(rand.nextInt(400), name);
		}
		Arrays.sort(students, new Comparator<student>() {
	        @Override
	        public int compare(student o1, student o2) {
	            return (int) (o1.GPA - o2.GPA);
	        }
	    });
		for (int i = 99; i >= 0; i--) {
			System.out.println("Name: " + students[i].name + " GPA: " + (students[i].GPA / 100));
		}
	}

}