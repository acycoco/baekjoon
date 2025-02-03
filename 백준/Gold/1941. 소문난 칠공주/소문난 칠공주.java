import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] arr = new char[5][5];
    static int answer;
    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우 이동
    static int[] dy = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {
        //7명의 여학생
        //가로나 세로로 인접
        //이다솜파(S)가 적어도 4명이상
        //모든 경우의 수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        fill(0, 0, 0, new ArrayList<>());
        System.out.println(answer);

    }

    public static void fill(int start, int cur, int countS, List<int[]> selected) {
        if (cur == 7) {
            if (countS >= 4 && isConneted(selected)) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;

            selected.add(new int[]{x, y});
            int nextCountS = countS;
            if (arr[x][y] == 'S') nextCountS = countS + 1;
            fill(i + 1, cur + 1, nextCountS, selected);
            selected.remove(selected.size() - 1);
        }
    }

    public static boolean isConneted(List<int[]> selected) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        queue.add(selected.get(0));
        visited[selected.get(0)[0]][selected.get(0)[1]] = true;
        int connectedCount = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                for (int[] next : selected) {
                    if (next[0] == nx && next[1] == ny && !visited[nx][ny]) {
                        queue.add(next);
                        visited[nx][ny] = true;
                        connectedCount++;
                    }
                }
            }
        }

        return connectedCount == 7;
    }
}
