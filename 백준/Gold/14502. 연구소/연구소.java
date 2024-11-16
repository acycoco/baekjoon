import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxSafe;
    static List<Point> emptySpace = new ArrayList<>();

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
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
        calcEmptySpace();
        buildWall(0, 0, new int[3]);
        System.out.println(maxSafe);
    }

    public static void calcEmptySpace() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    emptySpace.add(new Point(i, j));
                }
            }
        }
    }

    public static void buildWall(int depth, int start, int[] selected) {
        if(depth == 3) {
            simulate(selected);
            return;
        }

        for(int i = start; i < emptySpace.size(); i++) {
            selected[depth] = i;
            buildWall(depth + 1,i + 1, selected);
        }
    }

    public static void simulate(int[] selected) {
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, temp[i], 0, m);
        }

        for (int i = 0; i < selected.length; i++) {
            Point seletedPoint = emptySpace.get(selected[i]);
            temp[seletedPoint.x][seletedPoint.y] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 2) {
                    dfs(new Point(i, j), temp);
                }
            }
        }

        maxSafe = Math.max(maxSafe, countSafe(temp));
    }
    public static void dfs(Point point, int[][] temp) {
        for (int i = 0; i < 4; i++) {
            int nextX = point.x + dx[i];
            int nextY = point.y + dy[i];
            if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m &&
                    temp[nextX][nextY] == 0
            ) {
                temp[nextX][nextY] = 2;
                dfs(new Point(nextX, nextY), temp);
            }
        }
    }

    public static int countSafe(int[][] temp) {
        int count = 0;
        for (int i = 0; i< n; i++) {
            for (int j = 0; j < m; j++) {
                if(temp[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
