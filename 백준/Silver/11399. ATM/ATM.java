import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] waitTimes = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            waitTimes[i] = Integer.parseInt(st.nextToken());
        }
        //최댓값이 뒤로 갈 수록 유리함
        //정렬하기
        //삽입정렬
        for (int i = 1; i < n; i++) {
            int target = waitTimes[i];
            int j = i - 1;
            while (j >= 0 && waitTimes[j] > target) {
                waitTimes[j + 1] = waitTimes[j];
                j--;
            }
            waitTimes[j + 1] = target;
        }

        int[] sum = new int[n];
        sum[0] = waitTimes[0];
        int total = sum[0];
        for (int i = 1; i < n; i++) {
            sum[i] = waitTimes[i] + sum[i-1];
            total += sum[i];
        }
        System.out.println(total);
    }
}