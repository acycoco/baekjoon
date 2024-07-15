import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());

        int[] dp = new int[12];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 0; i < test; i++) {
            int n = Integer.parseInt(br.readLine());

            System.out.println(count(n, dp, 0));
        }
    }


    public static int count(int n, int[] dp, int sum) {
        if (sum == n) {
            return 1;
        }
        if (sum > n) {
            return 0;
        }
        int total = 0;
        for (int i = 1; i <= 3; i++) {
            total += count(n, dp, sum + i);
        }
        return total;
    }
}
