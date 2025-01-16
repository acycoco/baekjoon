import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        //빈 공간, 벽으로 이루어져있음 벽에는 불이 붙지 않음
        //1초마다 동서남북으로 이동
        //벽이 있는 칸은 갈 수 ㅇ벗고
        //불이 옮겨진 칸, 이제 붙으려는 칸 이동할 수 없음
        //이미 있는 경우에는 괜찮다.
        //얼마나 빨리 탈출할 수 있는지
        //h w
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            char[][] arr = new char[h][w];
            Queue<int[]> sangQueue = new LinkedList<>();
            Queue<int[]> fireQueue = new LinkedList<>();
            boolean[][] visited = new boolean[h][w];
            for (int j = 0; j < h; j++) {
                String line = br.readLine();
                arr[j] = line.toCharArray();
                for (int k = 0; k < w; k++) {
                    if (arr[j][k] == '*') {
                        fireQueue.add(new int[]{j, k});
                    } else if (arr[j][k] == '@') {
                        sangQueue.add(new int[]{j, k, 0});
                        visited[j][k] = true;
                    }
                }
            }

            int result = bfs(arr, fireQueue, sangQueue, visited, h, w);
            if (result == -1) {
                sb.append("IMPOSSIBLE");
            } else {
                sb.append(result);
            }
            sb.append("\n");
        }

        System.out.println(sb);


    }

    public static int bfs(char[][] arr, Queue<int[]> fireQueue, Queue<int[]> sangQueue, boolean[][] visited, int h, int w) {
        while(!sangQueue.isEmpty()) {
            int fireSize = fireQueue.size();
            for (int i = 0; i < fireSize; i++) {
                int[] cur = fireQueue.poll();
                int curX = cur[0];
                int curY = cur[1];
                for (int j = 0; j < 4; j++) {
                    int nx = curX + dx[j];
                    int ny = curY + dy[j];
                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                    if (arr[nx][ny] == '#' || arr[nx][ny] == '*') continue;
                    arr[nx][ny] = '*';
                    fireQueue.add(new int[]{nx, ny});
                }
            }
            int sangSize = sangQueue.size();
            for (int i = 0; i < sangSize; i++) {
                int[] cur = sangQueue.poll();
                int curX = cur[0];
                int curY = cur[1];
                int dist = cur[2];
                for (int j = 0; j < 4; j++) {
                    int nx = curX + dx[j];
                    int ny = curY + dy[j];
                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) return dist + 1;
                    if (visited[nx][ny] || arr[nx][ny] == '#' || arr[nx][ny] == '*') continue;
                    visited[nx][ny] = true;
                    sangQueue.add(new int[]{nx, ny, dist + 1});
                }
            }

        }
        return -1;
    }
}
