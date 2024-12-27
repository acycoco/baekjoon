
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;
    static int max = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        maxNumber = getMax(arr);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                List<int[]> selected = new ArrayList<>();
                selected.add(new int[]{i, j});
                dfs(1, selected, arr[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(max);
    }
    public static void dfs(int depth, List<int[]> selected, int sum) {
        if (sum + (4 - depth) * maxNumber <= max) {
            return;
        }
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < selected.size(); i++) {
            for (int j = 0; j < 4; j++) {
                int[] block = selected.get(i);
                int x = dx[j] + block[0];
                int y = dy[j] + block[1];
                if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) {
                    continue;
                }
                selected.add(new int[]{x, y});
                visited[x][y] = true;
                dfs(depth + 1, selected, sum + arr[x][y]);
                selected.remove(selected.size() - 1);
                visited[x][y] = false;
            }
        }

    }
    
    public static int getMax(int[][] arr) {
        int max = arr[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, arr[i][j]);
            }
        }
        return max;
    }
}
