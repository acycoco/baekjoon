
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int h;
    static int[][][] arr;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dy = {1, 0, -1, 0, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        //하루마다 위, 아래, 상하좌우 6방향 토마토 익는다.
        //최소 일수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        arr = new int[h][n][m];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    if (tomato == 1) {
                        queue.add(new int[]{i, j, k});
                    }
                    arr[i][j][k] = tomato;
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    public static int bfs() {
        int day = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nx = dx[i] + cur[1];
                int ny = dy[i] + cur[2];
                int nh = dh[i] + cur[0];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || nh < 0 || nh >= h) {
                    continue;
                }
                if (arr[nh][nx][ny] != 0) { //이미 익었거나 안익은 토마토가 아니면
                    continue;
                }
                arr[nh][nx][ny] = arr[cur[0]][cur[1]][cur[2]] + 1;
                queue.add(new int[]{nh, nx, ny});
            }
        }

        return isFinish();
    }

    public static int isFinish() {
        int anwer = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (arr[i][j][k] == 0) {
                        return -1;
                    }
                    anwer = Math.max(anwer, arr[i][j][k]);
                }
            }
        }
        return anwer - 1;
    }
}
