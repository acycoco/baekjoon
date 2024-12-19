import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static final char RED = 'R';
    static final char BLUE = 'B';
    static final char WALL = '#';
    static final char HOLE = 'O';
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static char[][] arr;
    static boolean[][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        visited = new boolean[n][m][n][m];
        int rX = 0;
        int rY = 0;
        int bX = 0;
        int bY = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            arr[i] = line.toCharArray();

            for (int j = 0; j < m; j++) {
                if (arr[i][j] == RED) {
                    rX = i;
                    rY = j;
                    arr[i][j] = '.';
                }
                if (arr[i][j] == BLUE) {
                    bX = i;
                    bY = j;
                    arr[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(rX, rY, bX, bY));

    }


    public static int bfs(int rX, int rY, int bX, int bY) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(rX, rY, bX, bY, 0));
        visited[rX][rY][bX][bY] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.moves >= 10) return -1;
            for (int i = 0; i < 4; i++) {
                int[] redResult = move(current.redX, current.redY, i);
                int[] blueResult = move(current.blueX, current.blueY, i);

                int newRedX = redResult[0], newRedY = redResult[1];
                int newBlueX = blueResult[0], newBlueY = blueResult[1];

                boolean redInHole = redResult[2] == 1;
                boolean blueInHole = blueResult[2] == 1;

                if (blueInHole) continue;
                if (redInHole) return current.moves + 1;

                if (newRedX == newBlueX && newRedY == newBlueY) {
                    if (redResult[3] > blueResult[3]) {
                        newRedX -= dx[i];
                        newRedY -= dy[i];
                    } else {
                        newBlueX -= dx[i];
                        newBlueY -= dy[i];
                    }
                }

                if (!visited[newRedX][newRedY][newBlueX][newBlueY]) {
                    visited[newRedX][newRedY][newBlueX][newBlueY] = true;
                    queue.add(new State(newRedX, newRedY, newBlueX, newBlueY, current.moves + 1));
                }
            }
        }

        return -1;
    }

    public static int[] move(int x, int y, int dir) {
        int[] result = new int[4];
        
        int distance = 0;
        while (true) {
            if (arr[x][y] == HOLE) break; // 구멍에 도달하면 즉시 중단
            if (arr[x + dx[dir]][y + dy[dir]] == WALL) break; // 벽에 막히면 중단
            x += dx[dir];
            y += dy[dir];
            distance++;
        }
        return new int[]{x, y, arr[x][y] == HOLE ? 1 : 0, distance};
    }

    static class State {
        int redX, redY, blueX, blueY, moves;

        State(int redX, int redY, int blueX, int blueY, int moves) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.moves = moves;
        }
    }


}
