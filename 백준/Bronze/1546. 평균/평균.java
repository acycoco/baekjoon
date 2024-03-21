import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int M = sc.nextInt();
        int sum = M;
        for (int i = 1; i < n; i++) {
            int score = sc.nextInt();
            if (M < score) {
                M = score;
            }
            sum += score;
        }
        System.out.println(sum * 1.0 / M * 100 / n);
    }
}
