import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer info = new StringTokenizer(reader.readLine());
        int coinKinds = Integer.parseInt(info.nextToken());
        int targetAmount = Integer.parseInt(info.nextToken());

        int[] coins = new int[coinKinds];
        //오름차순 정렬이 되어있음
        for (int i = 0; i < coinKinds; i++) {
            coins[coinKinds - i - 1] = Integer.parseInt(reader.readLine());
        }

        int idx = 0;
        int answer = 0;
        //거슬러 줄 금액이 남아있는 동안 반복한다.
        while(targetAmount > 0){
            answer += targetAmount / coins[idx];
            targetAmount %= coins[idx];
            idx++;
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }
}
