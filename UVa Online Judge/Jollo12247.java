import java.util.*;

class Jollo12247 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			ArrayList<Integer> princess = new ArrayList<Integer>();
			boolean[] cards = new boolean[53];
			princess.add(Integer.parseInt(in.next()));
			if (princess.get(0) == 0) break;
			cards[princess.get(0)] = true;
			princess.add(Integer.parseInt(in.next()));
			cards[princess.get(1)] = true;
			princess.add(Integer.parseInt(in.next()));
			cards[princess.get(2)] = true;
			ArrayList<Integer> prince = new ArrayList<Integer>();
			prince.add(Integer.parseInt(in.next()));
			cards[prince.get(0)] = true;
			prince.add(Integer.parseInt(in.next()));
			cards[prince.get(1)] = true;
			Collections.sort(princess);
			Collections.sort(prince);
			Collections.reverse(prince);
			for (int i = 0; i < prince.size(); i++) {
				for (int x = 0; x < princess.size(); x++) {
					if (prince.get(i) < princess.get(x)) {
						prince.remove(i);
						princess.remove(x);
						i--;
						x--;
						break;
					}
				}
			}
			if (prince.size() == 0) System.out.println(-1);
			else if (prince.size() == 2) {
				for (int i = 1; i < 53; i++) {
					if (!cards[i]) {
						System.out.println(i);
						break;
					}
				}
			} else if (prince.size() == 1) {
				int max = princess.get(princess.size() - 1), ans = -1;
				for (int i = max; i <= 52; i++) {
					if (!cards[i]) {
						ans = i;
						break;
					}
				}
				System.out.println(ans);
			}
		}
		in.close();
	}

}
