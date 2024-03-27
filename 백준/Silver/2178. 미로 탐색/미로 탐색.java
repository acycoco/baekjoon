import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            st= new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 1; j < m + 1; j++) {
                map[i][j] = Integer.parseInt(line.substring(j - 1, j));
            }
        }

        visited = new boolean[n + 1][m + 1];
        queue = new LinkedList<>();

        bfs(1, 1);

        System.out.println(map[n][m]);
    }

    private static void bfs(int x, int y) {
        queue.offer(new int[] {x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = now[0] + dx[i];
                int newY = now[1] + dy[i];
                //이웃한 애 중 범위를 벗어나지 않고, 1인 애
                if (newX >= 1 && newX <= n && newY >= 1 && newY <= m) {
                    //방문한 적이 없으면 queue에 삽입
                    if (map[newX][newY] != 0 && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        map[newX][newY] = map[now[0]][now[1]] + 1;
                        queue.add(new int[] {newX, newY});
                    }
                }
            }
        }



    }
}


