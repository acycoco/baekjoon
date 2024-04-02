
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Integer.parseInt(br.readLine());
        long k = Integer.parseInt(br.readLine());

        binarySearch(1, n * n, n, k);
        System.out.println(answer);

    }

    private static void binarySearch(long start, long end, long n, long k) {
        while (start <= end) {
            long mid = (start + end) / 2;
            long minCount = countOfLess(n, mid);
            if (minCount <= k - 1) {
                answer = Math.max(answer, mid);
                start = mid + 1;
            } else if (minCount > k - 1) {
                end = mid - 1;
            }
        }
    }

    private static long countOfLess(long n, long x) {
        //x보다 작은 개수
        long count = 0;
        for (int i = 1; i <= n; i++) {
            count +=Math.min(n, ((x - 1) / i));
        }
        return count;
    }
}


