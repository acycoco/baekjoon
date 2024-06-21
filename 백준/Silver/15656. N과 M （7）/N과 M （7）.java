import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[] arr;
    static int[] nums;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        arr = new int[m];
        Arrays.sort(nums);

        makeArr(0);
        bw.close();
    }

    static void makeArr(int k) throws IOException {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                bw.append(nums[arr[i]] + " ");
            }
            bw.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[k] = i;
            makeArr(k + 1);
        }
    }
}
