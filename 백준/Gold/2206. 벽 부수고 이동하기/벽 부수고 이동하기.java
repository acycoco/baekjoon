import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        //1 이동불가
        //1-based index
        //시작 , 끝 칸 포함
        //벽은 최대 1개 까지 부술 수 있음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1, 0}); //{x, y, 거리, 부순 여부}
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            int broken = cur[3];

            if (x == n - 1 && y == m - 1) {
                System.out.println(d);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (map[nx][ny] == 0 &&!visited[nx][ny][broken]) {
                    visited[nx][ny][broken] = true;
                    queue.add(new int[]{nx, ny, d + 1, broken});
                } else if (map[nx][ny] == 1 && broken == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    queue.add(new int[]{nx, ny, d + 1, 1});
                }
            }
        }
        System.out.println(-1);
    }
}
