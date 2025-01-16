import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken());
            int fy = Integer.parseInt(st.nextToken());
            int[][] arr = new int[m][m];
            arr[sx][sy] = 1;

            if (sx == fx && sy == fy) {
                sb.append(0);
            } else {
                sb.append(bfs(arr, m, sx, sy, fx, fy));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }


    public static int bfs(int[][] arr, int m, int sx, int sy, int fx, int fy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            for (int i = 0; i < 8; i++) {
                int nx = dx[i] + curX;
                int ny = dy[i] + curY;

                if (nx < 0 || nx >= m || ny < 0 || ny >= m) continue;
                if (arr[nx][ny] >= 1) continue;
                if (nx == fx && ny == fy) {
                    return arr[curX][curY];
                }
                arr[nx][ny] = arr[curX][curY] + 1;
                queue.add(new int[]{nx, ny});

            }
        }
        return 0;
    }
}
