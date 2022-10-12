import java.io.*;
import java.util.*;

class cow2 {
	
	int index, num;
	
	public cow2(int index2, int num2) {
		index = index2;
		num = num2;
	}
	
}

class op {
	
	int num, direction;
	
	public op(String num2, String direction2) {
		switch(num2) {
		case("A"):num = 1; break;
		case("B"):num = 2; break;
		case("C"):num = 3; break;
		}
		switch(direction2) {
		case("UP"):direction = 1; break;
		case("DOWN"):direction = 2; break;
		}
	}
	
}

public class cowsort {
	
	public static int nextLowest(cow2[] cows, int index) {
		for (int i = 1; i < cows.length; i++) {
			if (cows[i].index == index) {
				index = cows[i - 1].index;
				break;
			}
		}
		return index;
	}
	
	public static int nextHighest(cow2[] cows, int index) {
		for (int i = 0; i < cows.length - 1; i++) {
			if (cows[i].index == index) {
				index = cows[i + 1].index;
				break;
			}
		}
		return index;
	}
	
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\\s");
		int numCows = in.nextInt(), numOp = in.nextInt(), index = 0;
		cow2[] a = new cow2[numCows];
		cow2[] b = new cow2[numCows];
		cow2[] c = new cow2[numCows];
		op[] ops = new op[numOp];
		for (int i = 0; i < numCows; i++) {
			a[i] = new cow2(i, in.nextInt());
			b[i] = new cow2(i, in.nextInt());
			c[i] = new cow2(i, in.nextInt());
		}
		for (int i = 0; i < numCows; i++) {
			ops[i] = new op(in.next(), in.next());
		}
		Arrays.sort(a, new Comparator<cow2>() {
	        @Override
	        public int compare(cow2 o1, cow2 o2) {
	            return o1.num - o2.num;
	        }
	    });
		Arrays.sort(b, new Comparator<cow2>() {
	        @Override
	        public int compare(cow2 o1, cow2 o2) {
	            return o1.num - o2.num;
	        }
	    });
		Arrays.sort(c, new Comparator<cow2>() {
	        @Override
	        public int compare(cow2 o1, cow2 o2) {
	            return o1.num - o2.num;
	        }
	    });
		for (int i = 0; i < ops.length; i++) {
			/*System.out.println(index + " " + ops[i].num + " " + ops[i].direction);
			for (int x = 0; x < a.length; x++) {
				System.out.println(a[x].num + " " + a[x].index);
			}*/
			switch(ops[i].num) {
			case(1): if (ops[i].direction == 1) index = nextHighest(a, index); else index = nextLowest(a, index); break;
			case(2): if (ops[i].direction == 1) index = nextHighest(b, index); else index = nextLowest(b, index); break;
			case(3): if (ops[i].direction == 1) index = nextHighest(c, index); else index = nextLowest(c, index); break;
			}
		}
		System.out.println((index + 1));
		in.close();
	}
}