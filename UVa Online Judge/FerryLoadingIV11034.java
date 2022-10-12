import java.util.*;
import java.io.*;

class FerryLoadingIV11034 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int u = Integer.parseInt(in.readLine());
		for (int o = 0; o < u; o++) {
			Queue<Integer> right = new LinkedList<Integer>();
			Queue<Integer> left = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(in.readLine());
			int l = Integer.parseInt(st.nextToken()) * 100, m = Integer.parseInt(st.nextToken());
			for (int i = 0; i < m; i++) {
				StringTokenizer st1 = new StringTokenizer(in.readLine());
				int len = Integer.parseInt(st1.nextToken());
				String temp = st1.nextToken();
				if (temp.equals("right")) right.offer(len);
				else left.offer(len);
			}
			int bank = 0, times = 0;
			while (!left.isEmpty() || !right.isEmpty()) {
				if (bank == 0) {
					int size = l;
					while (size > 0) {
						if (left.isEmpty()) break;
						if (size - left.peek() >= 0) {
							size -= left.poll();
						} else break;
					}
					bank = 1;
					times++;
				} else {
					int size = l;
					while (size > 0) {
						if (right.isEmpty()) break;
						if (size - right.peek() >= 0) {
							size -= right.poll();
						} else break;
					}
					bank = 0;
					times++;
				}
			}
			System.out.println(times);
		}
		in.close();
	}

}
