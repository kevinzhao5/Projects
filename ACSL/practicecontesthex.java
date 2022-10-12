
public class practicecontesthex {

	public static void main(String[] args) {
		String start_location = "B2", moves = "22435";
		int x = (int) (start_location.charAt(0)), y = Integer.parseInt(start_location.substring(1));
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            if (i == '1') {
                y++;
            } else if (i == '2') {
                if (x == 90) continue;
                x++;
                if (x % 2 == 0) y++;
            } else if (i == '3') {
                if (x % 2 == 0 && y == 1) continue;
                if (x % 2 == 0) y--;
                x++;
            } else if (i == '4') {
                if (y == 1) continue;
                y--;
            } else if (i == '5') {
                if (x == 65 || (x % 2 == 0 && y == 1)) continue;
                if (x % 2 == 0) y--;
                x--;
            } else {
                if (x == 65) continue;
                x--;
                if (x % 2 == 0) y++;
            }
        }
        char c = (char) (x);
        String res = "" + c + y;
        System.out.println(res);
	}

}
