import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] num;
    static int[] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        num = new int[13];
        arr = new int[6];
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }
            for (int i = 0; i < k; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            fill(0, 0, k);
            sb.append("\n");
        }
        System.out.println(sb);

    }


    public static void fill(int start, int n, int k) {
        if (n == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < k; i++) {
            arr[n] = num[i];
            fill(i + 1, n + 1, k);
        }
    }
}
