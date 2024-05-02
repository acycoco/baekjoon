import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testN = Integer.parseInt(br.readLine());
        int[][] dp = new int[31][31];

        for (int i = 1; i < 31; i++) {
            dp[i][i] = 1;
            dp[1][i] = i;
        }

        for (int j = 2; j <= 30; j++) {
            for (int k = j; k <= 30; k++) {
                dp[j][k] = dp[j - 1][k - 1] + dp[j][k - 1];
            }
        }

        StringTokenizer st;
        for (int i = 0; i < testN; i++) {
            st = new StringTokenizer(br.readLine());
            int westN = Integer.parseInt(st.nextToken());
            int eastN = Integer.parseInt(st.nextToken());

            System.out.println(dp[westN][eastN]);
        }
    }
}
