import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static boolean[] used1;
    static boolean[] used2;
    static boolean[] used3;
    static int result;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        used1 = new boolean[n];
        used2 = new boolean[2 * n];
        used3 = new boolean[2 * n];
        fill(0);
        bw.append(String.valueOf(result));
        bw.close();
    }

    static void fill(int row) throws IOException {
        if (row == n) {
            result++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (used1[i]) continue;
            if (used2[row + i]) continue;
            if (used3[row - i + n - 1]) continue;
            used1[i] = true;
            used2[row + i] = true;
            used3[row - i + n - 1] = true;
            fill(row + 1);
            used1[i] = false;
            used2[row + i] = false;
            used3[row - i + n - 1] = false;
        }
    }
}
