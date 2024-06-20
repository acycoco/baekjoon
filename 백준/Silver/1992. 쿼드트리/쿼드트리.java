import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        recur(n, 0, 0, arr);
        System.out.println(sb);

    }

    static void recur(int n, int x, int y, int[][] arr) {
        if (isSame(n, x, y, arr)) {
            sb.append(arr[x][y]);
        } else {
            sb.append("(");
            for (int i = 0; i < n; i += (n / 2)) {
                for (int j = 0; j < n; j+= (n / 2)) {
                    recur(n / 2, x + i, y + j, arr);

                }
            }
            sb.append(")");
        }


    }

    static boolean isSame(int n, int x, int y, int[][] arr) {
        int first = arr[x][y];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (first != arr[x + i][y + j])
                    return false;
            }
        }
        return true;
    }
}
