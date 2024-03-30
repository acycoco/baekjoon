
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] num = new int[n];
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        num[0] = Integer.parseInt(st.nextToken());
        int max = num[0];
        for (int i = 1; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            if (max < num[i])
                max = num[i];
            sum += num[i];
        }

        int start = max;
        int end = sum;
        while (start <= end) {
            int mid = (start + end) / 2;
            sum = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (sum + num[i] > mid) {
                    count++;
                    sum = 0;
                }
                sum = sum + num[i];
            }
            //모든 숫자를 다 돌았는데 sum이 0이 아니면 블루레이가 하나 더 필요하다.
            if (sum != 0) {
                count++;
            }
            if (count > m) { //불가능, 부족하다 더 늘려야한다.
                start = mid + 1;
            } else {//가능, 남아돈다. 더 줄여야한다.
                end = mid - 1;
            }
        }

        System.out.println(start);

    }


}


