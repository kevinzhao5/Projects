import java.io.*;
import java.util.*;
public class meeting {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("meeting.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[][] b = new int[n][n];
        int[][] e = new int[n][n];
        for(int i = 0; i < m; i++) {
        	StringTokenizer st1 = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st1.nextToken()) - 1, y = Integer.parseInt(st1.nextToken()) - 1;
            b[x][y] = Integer.parseInt(st1.nextToken());
            e[x][y] = Integer.parseInt(st1.nextToken());
        }
        in.close();
        boolean[][] bCan = new boolean[n][100 * n + 1];
        bCan[0][0] = true;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < bCan[i].length; j++) {
                if(!bCan[i][j]) continue;
                for(int k = i + 1; k < n; k++) {
                	if(b[i][k] > 0) {
                		bCan[k][j + b[i][k]] = true;
                	}
                }
            }
        }
        boolean[][] eCan = new boolean[n][100 * n + 1];
        eCan[0][0] = true;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < eCan[i].length; j++) {
                if(!eCan[i][j]) continue;
                for(int k = i + 1; k < n; k++) {
                	if(e[i][k] > 0) {
                		eCan[k][j + e[i][k]] = true;
                	}
                }
            }
        }
        int sol = -2;
        for(int i = 0; i < bCan[0].length; i++) {
            if(bCan[n - 1][i] && eCan[n - 1][i]) {
                sol = i;
                break;
            }
        }
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
        if(sol == -2) out.println("IMPOSSIBLE");
        else out.println(sol);
        out.close();
    }
}