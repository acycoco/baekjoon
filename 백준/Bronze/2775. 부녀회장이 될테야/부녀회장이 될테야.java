
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testN = Integer.parseInt(br.readLine());
        int[][] dp = new int[15][15]; //0층부터 1호부터

        for (int i = 1; i < 15; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < 15; i++) {
            dp[i][1] = 1;
        }


        for (int i = 0; i < testN; i++) {
            int floorN = Integer.parseInt(br.readLine());
            int roomN = Integer.parseInt(br.readLine());

            for (int j = 1; j <= floorN; j++) {
                for (int k = 2; k <= roomN; k++) { //2호부터 계산
                    if (dp[j][k] != 0) { //계산했으면 스킵
                        continue;
                    }
                    dp[j][k] = dp[j][k - 1] + dp[j - 1][k];
                }
            }


            System.out.println(dp[floorN][roomN]);
        }
    }
}
