import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[] stairs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stairs = new int[n + 1];//0제외
        for (int i = 1; i < n + 1; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n + 1][2];
        dp[1][0] = stairs[1];
        if (n >= 2) {
            dp[2][0] = stairs[2];
            dp[2][1] = stairs[1] + stairs[2];
        }
        if (n >= 3) {
            for(int i = 3; i < n + 1; i++) {
                dp[i][0] = Math.max(dp[i - 2][1], dp[i - 2][0]) + stairs[i];
                dp[i][1] = dp[i - 1][0] + stairs[i];
            }
        }


        System.out.println(Math.max(dp[n][0], dp[n][1]));
    }
}
