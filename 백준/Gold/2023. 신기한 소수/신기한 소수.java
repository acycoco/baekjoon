import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int answer;
    static StringBuilder sb;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
        System.out.println(sb);
    }

    private static void dfs(int n, int digit) {
        if (digit == answer) {
            if (isPrime(n)) {
                sb.append(n).append("\n");
            }
            return;
        }
        for (int i = 1; i <= 9; i += 2) { //홀수만
            if (isPrime(n * 10 + i)) {
                dfs(n * 10 + i, digit + 1);
            }
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {//1말고 약수가 있으면 소수가 아님
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}


