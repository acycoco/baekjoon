import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char[] cNums = sc.next().toCharArray();
        int[] nums = new int[cNums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = cNums[i] - '0';
        }
        
        int max;
        int maxIdx;
        //정렬하기
        for (int i = 0; i < nums.length - 1; i++) {
            max = nums[i];
            maxIdx = i;
            //최솟값 찾기
            for (int j = i + 1; j < nums.length; j++) {
                if (max < nums[j]) {
                    max = nums[j];
                    maxIdx = j;
                }
            }
            //최솟값이랑 swap
            int temp = nums[i];
            nums[i] = max;
            nums[maxIdx] = temp;
        }
         for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
    }
}