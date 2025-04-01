import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 줄은 누적
        dp[0][0] = arr[0][0];
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + arr[0][j];
        }

        // 두 번째 줄부터 처리
        for (int i = 1; i < n; i++) {
            int[] left = new int[m];
            int[] right = new int[m];

            // 왼쪽에서 오른쪽으로 누적
            left[0] = dp[i - 1][0] + arr[i][0];
            for (int j = 1; j < m; j++) {
                left[j] = Math.max(left[j - 1], dp[i - 1][j]) + arr[i][j];
            }

            // 오른쪽에서 왼쪽으로 누적
            right[m - 1] = dp[i - 1][m - 1] + arr[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1], dp[i - 1][j]) + arr[i][j];
            }

            // 둘 중 큰 값으로 dp 갱신
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
