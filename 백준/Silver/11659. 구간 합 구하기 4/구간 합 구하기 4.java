
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //입력받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        //숫자 입력받기
        int[] num = new int[n];
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
            //합배열만들기
            if (i == 0) sum[i] = num[i];
            else sum[i] = sum[i-1] + num[i];
        }
        //for m만큼 구간합구하기
        for (int i = 0; i < m; i++) {
            //i랑 j 에 -1 해준 범위의 구간합구하기 s[j] - s[i-1]
            int start = sc.nextInt() - 1;
            int end = sc.nextInt() - 1;
            int minus;
            if (start == 0) minus = 0;
            else minus = sum[start - 1];
            System.out.println(sum[end] - minus);
        }
    }
}
