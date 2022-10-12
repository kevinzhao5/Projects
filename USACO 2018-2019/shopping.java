/*
ID: awesome25
LANG: JAVA
TASK: shopping
*/
import java.io.*;
import java.util.*;

class offer {
	
	int[] items;
	int price;
	
	public offer(int[] items1, int price1) {
		items = items1;
		price = price1;
	}
	
}

class shopping {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		int numOffer = Integer.parseInt(in.readLine());
		String[] offers = new String[numOffer];
		for (int i = 0; i < numOffer; i++) {
			offers[i] = in.readLine();
		}
		int numPro = Integer.parseInt(in.readLine());
		int[] codes = new int[5];
		int[] items = new int[5];
		int[] prices = new int[5];
		for (int i = 0; i < numPro; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			codes[i] = Integer.parseInt(st.nextToken());
			items[i] = Integer.parseInt(st.nextToken());
			prices[i] = Integer.parseInt(st.nextToken());
		}
		offer[] special = new offer[numOffer + 5];
		for (int i = 0; i < numOffer; i++) {
			StringTokenizer st = new StringTokenizer(offers[i]);
			int num = Integer.parseInt(st.nextToken());
			int[] products = new int[5];
			for (int j = 0; j < num; j++) {
				int code = Integer.parseInt(st.nextToken());
				for (int x = 0; x < numPro; x++) {
					if (code == codes[x]) {
						products[x] = Integer.parseInt(st.nextToken());
						break;
					}
				}
			}
			special[i] = new offer(products, Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < 5; i++) {
			int[] products = new int[5];
			products[i] = 1;
			special[numOffer + i] = new offer(products, prices[i]);
		}
		int[][][][][] dp = new int[6][6][6][6][6];
		for (int a = 0; a <= 5; a++) {
			for (int b = 0; b <= 5; b++) {
				for (int c = 0; c <= 5; c++) {
					for (int d = 0; d <= 5; d++) {
						for (int e = 0; e <= 5; e++) {
							dp[a][b][c][d][e] = 1000000;
						}
					}
				}
			}
		}
		dp[0][0][0][0][0] = 0;
		for (int a = 0; a <= 5; a++) {
			for (int b = 0; b <= 5; b++) {
				for (int c = 0; c <= 5; c++) {
					for (int d = 0; d <= 5; d++) {
						for (int e = 0; e <= 5; e++) {
							for (int i = 0; i < numOffer + 5; i++) {
								if (special[i].items[0] <= a && special[i].items[1] <= b && special[i].items[2] <= c && special[i].items[3] <= d && special[i].items[4] <= e) {
									//System.out.println(dp[a][b][c][d][e] + " " + a + " " + b + " " + c + " " + d + " " + e);
									dp[a][b][c][d][e] = Math.min(dp[a][b][c][d][e], dp[a - special[i].items[0]][b - special[i].items[1]][c - special[i].items[2]][d - special[i].items[3]][e - special[i].items[4]] + special[i].price);
								}
							}
						}
					}
				}
			}
		}
		out.println(dp[items[0]][items[1]][items[2]][items[3]][items[4]]);
		in.close();
		out.close();
	}
	
}