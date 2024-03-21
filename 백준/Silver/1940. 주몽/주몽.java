
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int ingNum = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[ingNum + 1];
        for (int i = 1; i < ingNum + 1; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        int startIdx = 1;
        int endIdx = ingNum;
        int sum = num[startIdx] + num[endIdx];
        int cnt = 0;
        while (startIdx < endIdx) {
            if (num[startIdx] + num[endIdx] == target) {
                startIdx++;
                endIdx--;
                cnt++;
            } else if (num[startIdx] + num[endIdx] > target) {
                endIdx--;
            } else {
                startIdx++;
            }
        }
        System.out.println(cnt);
    }
}