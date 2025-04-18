import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int n;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] nums;
    static int[] oper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
         oper = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, nums[0]);
        System.out.println(max);
        System.out.println(min);

    }

    public static void dfs(int depth, int cur) {
        if (depth == n) {
            max = Math.max(max, cur);
            min = Math.min(min, cur);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (oper[i] <= 0) {
                continue;
            }
            oper[i]--;
            dfs(depth + 1, calc(cur, nums[depth], i));
            oper[i]++;
        }
    }

    public static int calc(int a, int b, int operand) {
        if (operand == 0) {
            return a + b;
        } else if (operand == 1) {
            return a - b;
        } else if (operand == 2) {
            return a * b;
        } else {
            return a / b;
        }
    }

}
