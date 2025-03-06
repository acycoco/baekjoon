import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        if (s.charAt(0) == '0') { // 0으로 시작하면 해석 불가
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + oneDigit;

            if (oneDigit >= 1) { // 한 자리 숫자 해석 가능
                dp[i] = (dp[i] + dp[i - 1]) % 1000000;
            }

            if (twoDigits == 10 || twoDigits == 20) { // 10, 20은 한 가지 방법만 가능
                dp[i] = dp[i - 2];
            } else if (twoDigits >= 10 && twoDigits <= 26) { // 두 자리 숫자로 해석 가능
                dp[i] = (dp[i] + dp[i - 2]) % 1000000;
            }
        }

        System.out.println(dp[n]);
    }
}
