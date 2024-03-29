import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        //정렬을 먼저해야됨
        Arrays.sort(num);
        int m = Integer.parseInt(br.readLine());
        int[] target = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        //찾아야 될 애 for문
        for (int i = 0; i < m; i++) {
            boolean result = binarySearch(target[i], 0, n - 1);
            if (result) System.out.println(1);
            else System.out.println(0);
        }
    }

//    private static boolean binarySearch(int target, int start, int end) {
//        while (start <= end) {
//            int mid = (start + end) / 2;
//            if (target == num[mid]) {
//                return true;
//            } else if (target < num[mid]) {
//                end = mid - 1;
//            } else {
//                start = mid + 1;
//            }
//        }
//        return false;
//    }

    private static boolean binarySearch(int target, int start, int end) {

        if (start > end) return false;
        int mid = (start + end) / 2;
        if (target == num[mid]) {
            return true;
        } else if (target < num[mid]) {
            return binarySearch(target, start, mid - 1);
        } else {
            return binarySearch(target, mid + 1, end);
        }
    }
}


