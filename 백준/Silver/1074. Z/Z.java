import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(z(n, r, c));

    }

    static int z(int n, int r, int c) {
        if (n == 0) return 0;

        int half = 1 << (n - 1);

        if (r < half && c < half) return z(n - 1, r, c);
        else if (r < half && c < 2 * half) return z(n - 1, r, c - half) + half * half;
        else if (r < 2 * half && c < half) return z(n - 1, r - half, c) + half * half * 2;
        else return z(n - 1, r - half, c - half) + half * half * 3;

    }
}
