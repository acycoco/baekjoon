import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(num, 0, n-1, k-1);
        System.out.println(num[k-1]);
    }

    private static void quickSort(int[] num, int start, int end, int k) {
        if (start < end) {
            int pivot = partition(num, start, end);
            if (pivot == k) return;
            else if (pivot > k) {
                quickSort(num, start, pivot - 1, k);
            } else {
                quickSort(num, pivot + 1, end, k);
            }
        }
    }
    private static int partition(int[] num, int start, int end) {
        if (start + 1 == end) {
            if (num[start] > num[end]) {
                swap(num, start, end);
            }
            return end;
        }

        int mid = (start + end) / 2;
        swap(num, start, mid); //중간값을 앞으로 보내기
        int pivot = num[start];
        int left = start + 1;
        int right = end;
        while (left <= right) {
            while (pivot < num[right] && right > 0) right--;
            while (pivot > num[left] && left < num.length - 1) left++;
            if (left <= right) swap(num, left++, right--);
        }
        num[start] = num[right];
        num[right] = pivot;
        return right;
    }

    public static void swap(int[] num, int start, int end) {
        int temp = num[start];
        num[start] = num[end];
        num[end] = temp;
    }
}
