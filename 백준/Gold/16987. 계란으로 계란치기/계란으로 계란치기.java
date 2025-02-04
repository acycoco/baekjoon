import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] s;
    static int[] w;
    static int answer;
    static int count;
    public static void main(String[] args) throws IOException {
        //계란의 내구도가 상대 계란의 무게만큼 깎인다.
        //0이하가 되는 순간 계란은 깨진다.
        //왼쪽부터 한번씩 다른 계란을 쳐 최대한 많은 계란
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n];
        w = new int[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            w[i] = Integer.parseInt(st.nextToken());
        }

        comb(0);
        System.out.println(answer);
    }

    public static void comb(int cur) {
        if (cur == n) {
            answer = Math.max(answer, count);
            return;
        }

        if (s[cur] <= 0|| count == n - 1) {
            comb(cur + 1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i == cur || s[i] <= 0) continue;
            s[i] -= w[cur];
            s[cur] -= w[i];
            if (s[i] <= 0) count++;
            if (s[cur] <= 0) count++;
            comb(cur + 1);
            if (s[i] <= 0) count--;
            if (s[cur] <= 0) count--;
            s[i] += w[cur];
            s[cur] += w[i];
        }
    }
}
