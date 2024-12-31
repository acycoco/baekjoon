import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static long[] gitars;
    static int maxSong = 0;
    static int minGuitar = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gitars = new long[n];
        for (int i = 0; i < n; i++) {
            String gitar = br.readLine().split(" ")[1];
            gitars[i] = toBit(gitar);
        }

        for (int i = 0; i < (1 << n); i++) {
            long comb = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) continue;
                comb |= gitars[j];
            }

            int songCount = Long.bitCount(comb);
            int guitarCount = Integer.bitCount(i);
            if (maxSong < songCount) {
                maxSong = songCount;
                minGuitar = guitarCount;
            } else if (maxSong == songCount && minGuitar > guitarCount) {
                minGuitar = guitarCount;
            }
        }
        System.out.println(minGuitar);

    }


    public static long toBit(String gitar) {
        long bit = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (gitar.charAt(i) == 'Y') {
                bit |= (1L << i);
            }
        }
        return bit;
    }
}
