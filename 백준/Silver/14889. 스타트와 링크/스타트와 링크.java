import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] s;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n][n];
        visited = new boolean[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(min);
    }

    public static void dfs(int selectedCount, int nowIndex) {
        if (selectedCount == n / 2) {
            calculateDifference();
            return;
        }

        if (nowIndex >= n) {
            return;
        }
        visited[nowIndex] = true;
        dfs(selectedCount + 1, nowIndex + 1);

        visited[nowIndex] = false;
        dfs(selectedCount, nowIndex + 1);
    }

    public static void calculateDifference() {
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (visited[i] && visited[j]) {
                    s1 += s[i][j];
                } else if (!visited[i] && !visited[j]) {
                    s2 += s[i][j];
                }
            }
        }
        min = Math.min(min, Math.abs(s1 - s2));
    }

}
