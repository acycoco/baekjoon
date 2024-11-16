import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxSafe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        buildWall(0);
        System.out.println(maxSafe);
    }

    public static void buildWall(int count) {
        if(count == 3) {
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(arr[i][j] == 2) {
                        bfs(i, j, visited);
                    }
                }
            }
            maxSafe = Math.max(maxSafe, countSafe(visited));
            return;
        }

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    buildWall(count + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    public static void bfs(int x, int y, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];
                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m &&
                        !visited[nextX][nextY] && arr[nextX][nextY] == 0
                ) {
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    public static int countSafe(boolean[][] visited) {
        int count = 0;
        for (int i = 0; i< n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && arr[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
