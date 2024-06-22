import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int s;
    static int[] nums;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        select(0, 0, 0);
        System.out.println(result);
    }

    static void select(int idx, int sum, int selected) {
        if (idx == n) {
            if (sum == s && selected != 0) { //공집합 제외
                result++;
            }
            return;
        }
        sum += nums[idx];
        select(idx + 1, sum, ++selected);
        sum -= nums[idx];
        select(idx + 1, sum, --selected);
    }
}
