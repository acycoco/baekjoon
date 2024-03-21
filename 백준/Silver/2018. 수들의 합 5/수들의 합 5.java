
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        //window같이 구한다.
        int[] num = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            num[i] = i;
        }
        int startIdx = 1;
        int endIdx = 1;
        int sum = 1;
        int cnt = 1;
        //end index가 끝 인덱스로 갈때까지 반복한다.
        while (endIdx != n) {
            if (sum < n) {
                endIdx++;
                sum += num[endIdx];
            } else if (sum > n) {
                sum -= num[startIdx];
                startIdx++;
            } else {
                cnt++;
                endIdx++;
                sum += num[endIdx];
            }
        }
        System.out.println(cnt);
    }
}
