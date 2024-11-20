import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] room;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        room = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit(r, c, d);
        System.out.println(answer);
    }

    public static void visit(int x, int y, int d) {
        if(room[x][y] == 0) {
            room[x][y] = 2;
            answer++;
        }

        for (int i = 0; i < 4; i++) {
            d = turn(d);
            int newX = x + dx[d];
            int newY = y + dy[d];
            if (inArea(newX, newY) && room[newX][newY] == 0) {
                visit(newX, newY, d); // 청소 가능하면 이동
                return;
            }
        }
 
            int backD = (d + 2) % 4;
            int backX = x + dx[backD];
            int backY = y + dy[backD];
            if (inArea(backX, backY) && room[backX][backY] == 1) {
                return;
            } else {
                visit(backX, backY, d);
            }
        
    }
    
    public static int turn(int d) {
        return (d + 3) % 4;
    }


    public static boolean inArea(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}