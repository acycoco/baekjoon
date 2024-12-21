import java.io.*;
import java.util.*;
public class Main {

	    public static void main(String[] args) throws Exception {
	    	
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    	StringTokenizer st = new StringTokenizer(br.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        int m = Integer.parseInt(st.nextToken());
	        int x = Integer.parseInt(st.nextToken());
	        int y = Integer.parseInt(st.nextToken());
	        int k = Integer.parseInt(st.nextToken());

	        int[][] map = new int[n][m];
	        for (int i = 0; i < n; i++) {
	        	st = new StringTokenizer(br.readLine());
	            for (int j = 0; j < m; j++) {
	                map[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }

	        int[] commands = new int[k];
	        st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < k; i++) {
	            commands[i] = Integer.parseInt(st.nextToken());
	        }

	        //이동    동, 서, 북, 남
	        int[] dx = {0, 0, -1, 1};
	        int[] dy = {1, -1, 0, 0};

	        int[] dice = new int[6]; //윗면, 북, 동, 서, 남, 아랫면

	        for (int command : commands) {
	            int dir = command - 1; 
	            int nx = x + dx[dir];
	            int ny = y + dy[dir];

	 
	            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

	          
	            x = nx;
	            y = ny;

	     
	            rotateDice(dice, dir);

	         
	            if (map[x][y] == 0) {
	                map[x][y] = dice[5];
	            } else {
	                dice[5] = map[x][y]; 
	                map[x][y] = 0;
	            }

	       
	            System.out.println(dice[0]);
	        }

	    }

	    static void rotateDice(int[] dice, int dir) {
	        int[] temp = dice.clone();
	        switch (dir) {
	            case 0: //동
	                dice[0] = temp[3];
	                dice[2] = temp[0];
	                dice[5] = temp[2];
	                dice[3] = temp[5];
	                break;
	            case 1: //서
	                dice[0] = temp[2];
	                dice[2] = temp[5];
	                dice[5] = temp[3];
	                dice[3] = temp[0];
	                break;
	            case 2: //북
	                dice[0] = temp[4];
	                dice[1] = temp[0];
	                dice[5] = temp[1];
	                dice[4] = temp[5];
	                break;
	            case 3: //남
	                dice[0] = temp[1];
	                dice[1] = temp[5];
	                dice[5] = temp[4];
	                dice[4] = temp[0];
	                break;
	        }
	    }
	

}