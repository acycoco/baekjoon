import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int row;
    static int col;
    static int[][] graph;
    static Queue<int[]> queue = new LinkedList<>();
    static List<int[]> ripeTomatoList = new ArrayList<>();
    static int[] x = new int[]{-1, 1, 0, 0};
    static int[] y = new int[]{0, 0, -1, 1};
    static boolean hasUnripeTomato = false;
    static int result = Integer.MAX_VALUE;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        graph = new int[col][row];
        for (int i = 0; i < col; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < row; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                graph[i][j] = tomato;
                if (tomato == 1) {
                    ripeTomatoList.add(new int[]{i, j});
                }
                if (!hasUnripeTomato && tomato == 0) {
                    hasUnripeTomato = true;
                }
            }
        }

        if (!hasUnripeTomato) { //다 익어있으면
            System.out.println("0");
        } else { //안익은 토마토가 있으면
            if (!ripeTomatoList.isEmpty()) { //익은 토마토가 한개라도 있으면
                for (int[] ripeTomato : ripeTomatoList) {
                    queue.add(new int[]{ripeTomato[0], ripeTomato[1], 0});
                    graph[ripeTomato[0]][ripeTomato[1]] = -1;
                }
                result = bfs();
                for (int i = 0; i < col; i++) {
                    for (int j = 0; j < row; j++) {
                        if (graph[i][j] == 0) {
                            System.out.println("-1");
                            return;
                        }
                    }
                }
                System.out.println(result);
            } else { //익은 토마토가 없으면
                System.out.println("-1");
            }
        }
        //-1이면 탐색불가
        //탐색한 것을 1로
        //다 탐색하고 나서 graph를 돌아서 1과 -1만 남아있으면 0

        //0이 남아있으면 -1 출력

    }

    static int bfs() {
        int lastCount = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + x[i];
                int nextY = now[1] + y[i];

                if (nextX >= 0 && nextX < col && nextY >= 0 && nextY < row && graph[nextX][nextY] == 0) {
                    graph[nextX][nextY] = -1;
                    queue.add(new int[]{nextX, nextY, now[2] + 1});
                }

            }
            lastCount = now[2];
        }
        return lastCount;
    }
}
