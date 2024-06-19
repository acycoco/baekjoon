import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static char[][] arr;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static Queue<int[]> q1 = new LinkedList<>();
    static Queue<int[]> q2 = new LinkedList<>();
    static int[][] fire;
    static int[][] jihun;

    public static void main(String[] args) throws IOException {
        //불을 bfs 불이 여러개인데 불을 다 큐에 넣고, bfs를 해서
        //지훈 bfs  지훈의 숫자가 더 작은 경우 통과가능
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        fire = new int[n][m];
        jihun = new int[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'F') {
                    q1.add(new int[]{i, j});
                    fire[i][j] = 1;
                } else if (arr[i][j] == 'J') {
                    q2.add(new int[]{i, j});
                    jihun[i][j] = 1;
                    if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                        System.out.println("1");
                        return;
                    }
                }
            }
        }



        //불 bfs
        while(!q1.isEmpty()) {
            int[] now = q1.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;
                if (fire[nextX][nextY] > 0) //이미 방문했다면
                    continue;
                if (arr[nextX][nextY] == '#') // 벽이면 못감
                    continue;
                fire[nextX][nextY] = fire[now[0]][now[1]] + 1;
                q1.add(new int[]{nextX, nextY});
            }
        }

        //지훈 bfs
        while(!q2.isEmpty()) {
            int[] now = q2.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;
                if (jihun[nextX][nextY] > 0) //이미 방문했다면
                    continue;
                if (arr[nextX][nextY] == '#') // 벽이면 못감
                    continue;
                if (fire[nextX][nextY] == 0 || jihun[now[0]][now[1]] + 1 < fire[nextX][nextY]) {
                    jihun[nextX][nextY] = jihun[now[0]][now[1]] + 1;
                    q2.add(new int[]{nextX, nextY});
                    if (nextX == 0 || nextY == 0 || nextX == n - 1 || nextY == m - 1) {
                        System.out.println(jihun[nextX][nextY]);
                        return;
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");

    }
}
