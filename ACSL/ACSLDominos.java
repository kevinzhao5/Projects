import java.util.*;
import java.io.*;

class domino {
	
	int start, end;
	
	public domino(int start2, int end2) {
		start = start2;
		end = end2;
	}
	
}

public class ACSLDominos {
	
	static int[] nums;
	static boolean done;
	static int w;
	
	public static boolean isIn(int x) {
		for (int i = 0; i < 5; i++) {
			if (x == nums[i]) return true;
		}
		return false;
	}
	
	public static void print(domino[] dominos) {
		for (int i = 0; i < 10; i++) {
			if (!isIn(i + 1)) System.out.println(dominos[i].start + " " + dominos[i].end + " " + (i + 1));
		}
		System.out.println();
	}
	
	public static int solve(domino[] dominos, ArrayList<domino> hand, int depth) {
		/*if (w == 6) {
			for (int i = 0; i < 10; i++) {
				System.out.println(dominos[i].start + " " + dominos[i].end + " " + (i + 1));
			}
			System.out.println();
		}*/
		if (depth == 0) {
			//System.out.println(dominos[9].start + " " + dominos[9].end);
			print(dominos);
			done = true;
			return 1;
		} else {
			depth--;
			for (int i = 0; i < 10; i++) {
				if (i == 9) {
					if (dominos[i].start == -1) {
						for (int x = 0; x < hand.size(); x++) {
							if (hand.get(x).start == dominos[i - 1].end) {
								domino temp = hand.remove(x);
								dominos[i] = temp;
								//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
								if (solve(dominos, hand, depth) == 1) return 1;
								dominos[i] = new domino(-1, -1);
								hand.add(x, temp);
							} else if (hand.get(x).end == dominos[i - 1].end) {
								domino temp = hand.remove(x);
								int e = 0;
								e = temp.end;
								temp.end = temp.start;
								temp.start = e;
								dominos[i] = temp;
								//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
								if (solve(dominos, hand, depth) == 1) return 1;
								dominos[i] = new domino(-1, -1);
								hand.add(x, temp);
							}
						}
					}
				} else if (i == 0) {
					if (dominos[i].start == -1) {
						for (int x = 0; x < hand.size(); x++) {
							if (hand.get(x).end == dominos[i + 1].start) {
								domino temp = hand.remove(x);
								dominos[i] = temp;
								//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
								if (solve(dominos, hand, depth) == 1) return 1;
								dominos[i] = new domino(-1, -1);
								hand.add(x, temp);
							} else if (hand.get(x).start == dominos[i + 1].start) {
								domino temp = hand.remove(x);
								int e = 0;
								e = temp.end;
								temp.end = temp.start;
								temp.start = e;
								dominos[i] = temp;
								//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
								if (solve(dominos, hand, depth) == 1) return 1;
								dominos[i] = new domino(-1, -1);
								hand.add(x, temp);
							}
						}
					}
				} else {
					if (dominos[i].start == -1) {
						if (dominos[i + 1].start == -1) {
							for (int x = 0; x < hand.size(); x++) {
								if (hand.get(x).start == dominos[i - 1].end) {
									domino temp = hand.remove(x);
									dominos[i] = temp;
									//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
									if (solve(dominos, hand, depth) == 1) return 1;
									dominos[i] = new domino(-1, -1);
									hand.add(x, temp);
								} else if (hand.get(x).end == dominos[i - 1].end) {
									domino temp = hand.remove(x);
									int e = 0;
									e = temp.end;
									temp.end = temp.start;
									temp.start = e;
									dominos[i] = temp;
									//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
									if (solve(dominos, hand, depth) == 1) return 1;
									dominos[i] = new domino(-1, -1);
									hand.add(x, temp);
								}
							}
						} else if (dominos[i - 1].start == -1) {
							//if (w == 6 && depth == 3) System.out.println(hand.get(3).start + " " + hand.get(3).end);
							for (int x = 0; x < hand.size(); x++) {
								if (hand.get(x).end == dominos[i + 1].start) {
									domino temp = hand.remove(x);
									dominos[i] = temp;
									//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
									if (solve(dominos, hand, depth) == 1) return 1;
									dominos[i] = new domino(-1, -1);
									hand.add(x, temp);
								} else if (hand.get(x).start == dominos[i + 1].start) {
									domino temp = hand.remove(x);
									int e = 0;
									e = temp.end;
									temp.end = temp.start;
									temp.start = e;
									dominos[i] = temp;
									//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
									if (solve(dominos, hand, depth) == 1) return 1;
									dominos[i] = new domino(-1, -1);
									hand.add(x, temp);
								}
							}
						} else if (dominos[i - 1].start == -1 && dominos[i + 1].start == -1) ;
						else {
							for (int x = 0; x < hand.size(); x++) {
								if (hand.get(x).start == dominos[i - 1].end && hand.get(x).end == dominos[i + 1].start) {
									domino temp = hand.remove(x);
									dominos[i] = temp;
									//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
									if (solve(dominos, hand, depth) == 1) return 1;
									dominos[i] = new domino(-1, -1);
									hand.add(x, temp);
								} else if (hand.get(x).end == dominos[i - 1].end && hand.get(x).start == dominos[i + 1].start) {
									domino temp = hand.remove(x);
									int e = 0;
									e = temp.end;
									temp.end = temp.start;
									temp.start = e;
									dominos[i] = temp;
									//System.out.println(temp.start + " " + temp.end + " " + (i + 1));
									if (solve(dominos, hand, depth) == 1) return 1;
									dominos[i] = new domino(-1, -1);
									hand.add(x, temp);
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("src/dominos"));
		in.useDelimiter("\\s");
		for (w = 0; w < 10; w++) {
			done = false;
			nums = new int[5];
			for (int i = 0; i < 5; i++) {
				nums[i] = in.nextInt();
			}
			domino[] dominos = new domino[10];
			for (int i = 0; i < 10; i++) {
				dominos[i] = new domino(-1, -1);
			}
			for (int i = 0; i < 5; i++) {
				dominos[nums[i] - 1] = new domino(in.nextInt(), in.nextInt());
			}
			ArrayList<domino> hand = new ArrayList<domino>();
			for (int i = 0; i < 5; i++) {
				hand.add(new domino(in.nextInt(), in.nextInt()));
			}
			in.nextLine();
			solve(dominos, hand, 5);
			if (!done) {
				System.out.println("CAN'T BE DONE");
				System.out.println();
			}
		}
		in.close();
	}

}