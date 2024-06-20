import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] result = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
               arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        a4(n, 0, 0, arr);
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }

    static void a4(int n, int x, int y, int[][] arr) {
        int first = arr[x][y];
        boolean isSame = true;
        for (int i = x; i < n + x; i++) {
            for (int j = y; j < n + y; j++) {
                if (first != arr[i][j]) {
                   isSame = false;
                   break;
                }
            }
        }

        if (!isSame) {
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    a4(n / 3, x + k * n / 3, y + l * n / 3, arr);
                }
            }
        }

        else {
            if (first == -1) result[0]++;
            else if (first == 0) {
                result[1]++;
            } else {
                result[2]++;
            }
        }
    }
}
