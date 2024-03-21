import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] score = new int[n];
        //입력받기
        for (int i = 0; i < n; i++) {
            score[i] = sc.nextInt();
        }
        double[] dScore = new double[n];
        //최고점 M을 구하기
        int M = score[0];
        for (int i = 0; i < n; i++) {
            if (M < score[i]) {
                M = score[i];
            }
        }
        //새로운 성적을 구하기 /M*100
        for (int i = 0; i < n; i++) {
            dScore[i] = score[i] * 1.0 / M * 100;
        }
        //새로운 평균 구하기
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += dScore[i];
        }
        System.out.println(sum / n);
    }
}
