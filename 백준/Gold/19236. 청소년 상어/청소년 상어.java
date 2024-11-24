import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Arrow[][] arr = new Arrow[4][4];
    static int answer;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static Point[] fish = new Point[17]; //물고기 위치 기록
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int arrow = Integer.parseInt(st.nextToken()) - 1;
                arr[i][j] = new Arrow(num, arrow);
                fish[num] = new Point(i, j);
            }
        }
        //0, 0 에 상어 입장
        int startNum = arr[0][0].num;
        int startDir = arr[0][0].d;
        fish[startNum] = Point.DIE;
        arr[0][0] = new Arrow(0, startDir);

        dfs(arr, fish, 0, 0, startNum);
        System.out.println(answer);
    }


    public static void dfs(Arrow[][] arr, Point[] fish, int sx, int sy, int sum) {
        answer = Math.max(answer, sum);

        Arrow[][] newArr = new Arrow[4][4];
        Point[] newFish = new Point[17];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArr[i][j] = new Arrow(arr[i][j].num, arr[i][j].d);
            }
        }

        for (int i = 1; i < 17; i++) {
            newFish[i] = new Point(fish[i].x, fish[i].y);
        }

        //물고기 이동
        moveFish(newArr, newFish);

        //상어 이동
        int dir = newArr[sx][sy].d;
        for (int i = 1; i <= 3; i++) { //최대 3번의 경우의 수가 있음
            int nx = sx + i * dx[dir];
            int ny = sy + i * dy[dir];

            if (nx < 0 || nx > 3 || ny < 0 || ny > 3) {
                continue;
            }
            Arrow nArr = newArr[nx][ny];
            if (nArr.num == -1) { //물고기가 없으면 스킵
                continue;
            }

            int nNum = nArr.num;
            int nDir = nArr.d;
            newArr[sx][sy] = Arrow.EMPTY;
            newArr[nx][ny] = new Arrow(0, nDir);
            newFish[nNum] = Point.DIE;

            dfs(newArr, newFish, nx, ny, sum + nNum);

            newArr[sx][sy] = new Arrow(0, dir);
            newArr[nx][ny] = new Arrow(nNum, nDir);
            newFish[nNum] = new Point(nx, ny);
        }
    }
    public static void moveFish(Arrow[][] arr, Point[] fish) {
        for (int i = 1; i < 17; i++) {
            if (fish[i].x == -1) { //죽었으면 스킵
                continue;
            }
            int sX = fish[i].x;
            int sY = fish[i].y;
            int sNum = arr[sX][sY].num;
            int sDir = arr[sX][sY].d;

            for (int j = 0; j < 8; j++) { //갈 수 없으면 다른 방향 시도
                int nDir = (sDir + j) % 8;
                int nX = sX + dx[nDir];
                int nY = sY + dy[nDir];
                if (nX < 0 || nX > 3 || nY < 0 || nY > 3) {
                    continue;
                }

                int cNum = arr[nX][nY].num;
                int cDir = arr[nX][nY].d;
                if (cNum == 0) {
                    continue;
                }

                arr[nX][nY] = new Arrow(sNum, nDir);
                arr[sX][sY] = new Arrow(cNum, cDir);
                fish[i] = new Point(nX, nY);
                if (cNum == -1) {
                    break;
                }
                fish[cNum] = new Point(sX, sY);
                break;
            }
        }
    }

    public static class Arrow {
        static Arrow EMPTY = new Arrow(-1, -1);
        int num;
        int d;
        public Arrow(int num, int d) {
            this.num = num;
            this.d = d;
        }
    }

    public static class Point {
        static Point DIE = new Point(-1, -1);
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
