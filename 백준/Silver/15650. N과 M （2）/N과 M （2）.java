import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static boolean[] used;
    static int[] now;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        now = new int[m];
        used = new boolean[n + 1];
        fill(0);
        bw.close();
    }

    static void fill(int next) throws IOException {
        if (next == m) {
            for (int i = 0; i < m; i++) {
                bw.append(now[i] + " ");
            }
            bw.append("\n");
            return;
        }

        int start = 1;
        if (next != 0) {
            start = now[next - 1] + 1;
        }
        for (int i = start; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                now[next] = i;
                fill(next + 1);
                used[i] = false;
            }
        }
    }
}
