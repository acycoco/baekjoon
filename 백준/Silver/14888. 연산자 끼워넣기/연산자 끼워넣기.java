
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr;
    static int[] operations = new int[4]; // + - x /
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, arr[0]);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int depth, int nowNumber) {
        if (depth == n) {
            max = Math.max(max, nowNumber);
            min = Math.min(min, nowNumber);
        }

        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                operations[i]--;
                dfs(depth + 1, compute(nowNumber, arr[depth], i));
                operations[i]++;
            }
        }
    }
    public static int compute(int a, int b, int operation) {
        switch (operation) {
            case 0 : return a + b;
            case 1 : return a - b;
            case 2 : return a * b;
            case 3 : return a / b;
            default: return 0;
        }
    }
}
