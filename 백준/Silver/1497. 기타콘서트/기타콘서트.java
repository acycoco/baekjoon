
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static long[] gitars;
    static int maxSong = 0;
    static int minGuitar = Integer.MAX_VALUE;

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

        dfs(0L, 0, 0);
        if (maxSong == 0) {
            System.out.println(-1);
        } else {
            System.out.println(minGuitar);
        }

    }

    public static void dfs(long curSum, int used, int index) {
        if (index == n) {
            int songCount = Long.bitCount(curSum);
            if (songCount > maxSong) {
                maxSong = songCount;
                minGuitar = Integer.bitCount(used);  // 새로운 maxSong에 해당하는 기타 수 갱신
            } else if (songCount == maxSong) {
                minGuitar = Math.min(minGuitar, Integer.bitCount(used));  // 동일한 곡 수일 경우 기타 수 갱신
            }
            return;
        }
        dfs(curSum | gitars[index], used | (1 << index), index + 1);
        dfs(curSum, used, index + 1);
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
