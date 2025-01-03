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
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        int rx = 0, ry = 0;
        int bx = 0, by = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            arr[i] = line.toCharArray();
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (arr[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }


    static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(bx, by, rx, ry, 0));
        boolean[][][][] visited = new boolean[n][m][n][m];
        visited[rx][ry][bx][by] = true;
        while (!queue.isEmpty()) {
            State now = queue.poll();
            //10번 넘으면 실패
            if (now.count >= 10) {
                return -1;
            }
            for (int i = 0; i < 4; i++) {
                //움직인 거리가 0이면 다른 방향으로
                int redMove = getDistance(now.rx, now.ry, i);
                int blueMove = getDistance(now.bx, now.by, i);
                if (redMove == 0 && blueMove == 0) {
                    continue;
                }

                int nrx = now.rx + dx[i] * redMove;
                int nry = now.ry + dy[i] * redMove;
                int nbx = now.bx + dx[i] * blueMove;
                int nby = now.by + dy[i] * blueMove;

                //빨간 구슬이 구멍을 지나가면 성공
                //파란 구슬도 구멍에 빠지면 실패

                if (arr[nbx][nby] == 'O') {
                    continue;
                }
                if (arr[nrx][nry] == 'O') {
                    return now.count + 1;
                }
                if (nrx == nbx && nry == nby) {
                    if (redMove > blueMove) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }
                if (visited[nrx][nry][nbx][nby])
                    continue;
                queue.add(new State(nbx, nby, nrx, nry, now.count + 1));
                visited[nrx][nry][nbx][nby] = true;
            }
        }
        return -1;
    }

    //구슬을 중력으로 움직이는 함수   몇개를 움직였는지 반환
    static int getDistance(int x, int y, int d) {
        int count = 0;
        while(arr[x + dx[d]][y + dy[d]] != '#') {
            x += dx[d];
            y += dy[d];
            count++;
            if (arr[x][y] == 'O') {
                return count;
            }
        }
        return count;
    }
    //최소 몇번 만에 니까 bfs가 맞지 않을까 싶음 State에는 빨간 거, 파란 거의 위치를 보관




    static class State {
        int bx;
        int by;
        int rx;
        int ry;
        int count;
        public State(int bx, int by, int rx, int ry, int count) {
            this.bx = bx;
            this.by = by;
            this.rx = rx;
            this.ry = ry;
            this.count = count;
        }
    }
}
