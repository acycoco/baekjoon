import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] result = new int[2];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(n, 0, 0, arr);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    static void recur(int n, int x, int y, int[][] arr) {
        if(isSame(n, x, y, arr)) {
            result[arr[x][y]]++;
        } else {
            for(int i = 0; i < n; i+= (n / 2)) {
                for(int j = 0; j < n; j += (n / 2)) {
                    recur(n / 2, x + i, y + j, arr);
                }
            }
        }
    }

    static boolean isSame(int n, int x, int y, int[][] arr) {
        int first = arr[x][y];
        for(int i = x; i < x + n; i++) {
            for(int j = y; j < y + n; j++) {
                if(first != arr[i][j])
                    return false;
            }
        }
        return true;
    }
}
