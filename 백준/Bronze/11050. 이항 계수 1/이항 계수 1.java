import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //초기화
        int[][] arr = new int[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (i == j || j == 0) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = -1;
                }
            }
        }
        //반복문 점화식
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i < j) {
                    break;
                }
                if (arr[i][j] == -1) {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }
        }

        System.out.println(arr[n][k]);
    }
}
