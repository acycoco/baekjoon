
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] flowers = new int[n][2]; //i,0 start, i,1 end
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            flowers[i][0] = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            flowers[i][1] = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
        }
        Arrays.sort(flowers, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        int result = 0;
        int curE = 301;
        int end = 1130;
        int maxEnd = 0;
        int i = 0;
        while (curE <= end) {
            boolean found = false;
            while (i < n && flowers[i][0] <= curE) {
                if (flowers[i][1] > maxEnd) {
                    maxEnd = flowers[i][1];
                    found = true;
                }
                i++;
            }
            if (!found) {
                System.out.println(0);
                return;
            }
            curE = maxEnd;
            result++;
            if (curE > end) {
                System.out.println(result);
                return;
            }

        }
        System.out.println(0);

    }

}
