import java.util.*;

public class maxavg {

	public static double average(ArrayList<Integer> marsh, int subtract, int num) {
		double avg = 0;
		for (int i = 0; i < marsh.size(); i++) {
			avg += marsh.get(i);
		}
		avg -= subtract;
		avg /= (marsh.size() - num);
		return avg;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt(), subtract = 0, number = 0, maxSubtract = 0, maxNumber = 0;
		double avg = 0;
		ArrayList<Integer> marsh = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			marsh.add(in.nextInt());
		}
		avg = average(marsh, subtract, number);
		int begin = 0, end = 0, curr = 0, tempNum = 0;
		for (int i = 1; i < marsh.size() - 1; i++) {
			avg = average(marsh, subtract, number);
			if (marsh.get(i) > avg) {
				begin = i;
				while (marsh.get(i) > avg && i < marsh.size() - 1) {
					curr += marsh.get(i);
					i++;
					tempNum++;
				}
				end = i - 1;
				avg = average(marsh, curr, tempNum);
				int temp = 0;
				while (1 == 1) {
					while (marsh.get(begin - 1) > avg && begin - 1 > 0) {
						curr += marsh.get(begin - 1);
						begin--;
						tempNum++;
						temp++;
					}
					while (marsh.get(end + 1) > avg && end + 1 < marsh.size() - 1) {
						curr += marsh.get(end + 1);
						end++;
						tempNum++;
						temp++;
					}
					avg = average(marsh, curr, tempNum);
					if (temp == 0) break;
					temp = 0;
				}
				if (avg < average(marsh, maxSubtract, maxNumber)) {
					maxSubtract = curr;
					maxNumber = tempNum;
				}
			}
			tempNum = 0;
			begin = 0;
			end = 0;
			curr = 0;
		}
		avg = average(marsh, maxSubtract, maxNumber);
		System.out.printf("%.3f", avg);
		in.close();
	}

}