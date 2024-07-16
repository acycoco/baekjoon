import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] a = new long[n + 1];
        long[] dp = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            dp[i] = a[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) dp[i] = Math.max(dp[i], dp[j] + a[i]);
            }
        }

        long max = dp[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}
