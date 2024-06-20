import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], ' ');
        }
        recur(n, 0, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void recur(int n, int x, int y) {
        if (n == 1) {
            arr[x][y] = '*';
            return;
        }
        for (int k = 0; k < n; k += (n / 3)) {
            for (int l = 0; l < n; l += (n / 3)) {
                //중간만 빼고
                if (k == n / 3 && l == n / 3) {
                    continue;
                }

                recur(n / 3, x + k, y + l);
            }
        }
    }
}
