import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};

    static int[] squareX = new int[]{1, 1, 0};
    static int[] squareY = new int[]{0, 1, 1};
    static int[][] arr = new int[101][101];
    static boolean[][] visited = new boolean[101][101];
    static int n;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < 101; i++) {
            Arrays.fill(arr[i], -1);
        }
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(x, y, d, g);
        }

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (!visited[i][j] && arr[i][j] != -1) {
                    visit(i, j);
                }
            }
        }
        System.out.println(answer);
    }

    public static void visit(int x, int y) {
        for (int i = 0; i < 3; i++) {
            int nX = x + squareX[i];
            int nY = y + squareY[i];
            if (!Point.isIn(nX, nY) || arr[nX][nY] == -1) {
                return;
            }
        }
        answer++;
    }
    public static void draw(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for (int i = 0; i < g; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }
        arr[x][y] = d;
        for(int dir: directions) {
            x += dx[dir];
            y += dy[dir];
            arr[x][y] = dir;
        }
    }

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static boolean isIn(int x, int y) {
            return x >= 0 && x <= 100 && y >= 0 && y <= 100;
        }
    }
}
