import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int k;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static List<int[]> rotations = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = 1; //사과 1 뱀 2
        }
        int rotation = Integer.parseInt(br.readLine());
        int d = 1;
        Queue<int[]> snake = new LinkedList<>();
        for (int i = 0; i < rotation; i++) {
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            rotations.add(new int[]{sec, d});
            char dir = st.nextToken().charAt(0);
            if (dir == 'L') {
                d = (d + 3) % 4;
            } else {
                d = (d + 1) % 4;
            }
        }
        rotations.add(new int[]{Integer.MAX_VALUE, d});
        snake.offer(new int[]{1, 1});
        visited[1][1] = true;
        System.out.println(simulate(0, 0, 1, 1, snake));
    }

    public static int simulate(int depth, int now, int x, int y, Queue<int[]> snake) {
        int[] rotation = rotations.get(depth);
        for (int sec = now; sec < rotation[0]; sec++) {
            int nx = (x + dx[rotation[1]]);
            int ny = (y + dy[rotation[1]]);
            if (nx > n || ny > n || nx < 1 || ny < 1 || visited[nx][ny]) { //뱀의 몸을 만난다면
                return sec + 1;
            }
            if (arr[nx][ny] == 0) { //사과가 없다면
                int[] end = snake.poll();
                visited[end[0]][end[1]] = false;
                arr[end[0]][end[1]] = 0;
            }
            arr[nx][ny] = 2;
            visited[nx][ny] = true;
            snake.offer(new int[]{nx, ny});
            x = nx;
            y = ny;
        }
        return simulate(depth + 1, rotation[0], x, y, snake);
    }
}
