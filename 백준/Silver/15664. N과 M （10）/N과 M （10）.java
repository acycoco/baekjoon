import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[] arr;
    static int[] num;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        arr = new int[m];
        num = new int[n];
        used = new boolean[10];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        fill(0, sb);
        System.out.println(sb);
    }

    public static void fill(int index, StringBuilder sb) {
        if (index == m) {
            for (int i = 0; i < m; i++) {
                sb.append(num[arr[i]]).append(" ");
            }
            sb.append("\n");
            return;
        }
        int start = 0;
        if (index != 0) start = arr[index - 1] + 1;
        int tmp = 0;
        for (int i = start; i < n; i++) {
            if (tmp == num[i]) continue;
            arr[index] = i;
            tmp = num[i];
            fill(index + 1, sb);
        }

    }

}
