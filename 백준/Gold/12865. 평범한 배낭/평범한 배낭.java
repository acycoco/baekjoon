import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static int[] v;
    static int[] w;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        w = new int[n + 1];
        v = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i < n + 1; i++) {
            for(int j = 1; j < k + 1; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j - w[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - w[i]] + v[i], dp[i][j]);
                }
            }

        }

        System.out.println(dp[n][k]);
    }
}
