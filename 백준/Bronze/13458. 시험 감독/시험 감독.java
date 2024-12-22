import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] room = new int[n];
        String line = br.readLine();
        String[] nums = line.split(" ");
        for (int i = 0; i < n; i++) {
            room[i] = Integer.parseInt(nums[i]);
        }
        line = br.readLine();
        String[] supervisors = line.split(" ");
        int b = Integer.parseInt(supervisors[0]); // 총감독관
        int c = Integer.parseInt(supervisors[1]); // 부감독관

        long answer = 0;
        for (int i = 0; i < n; i++) {
            room[i] -= b;
            answer++;
            if (room[i] > 0) {
                long count = (room[i] / c);
                if (room[i] <= count * c) {
                    answer += count;
                } else {
                    answer += count + 1;
                }
            }
        }

        System.out.println(answer);
    }
}