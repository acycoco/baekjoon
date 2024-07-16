import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] time = new int[17];
        int[] price = new int[17];
        long[] dp = new long[17];

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            //i일에 상담을 하지 않은 경우
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            //상담을 하는 경우
            int endDay = time[i] + i;
            if (endDay <= n + 1) {
                dp[endDay] = Math.max(dp[endDay], dp[i] + price[i]);
            }
        }
        long max = dp[0];
        for (int i = 1; i <= n + 1; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
