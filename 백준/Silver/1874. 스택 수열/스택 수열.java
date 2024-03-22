import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
       //n입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        //수열만들기
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Stack<Integer> stack = new Stack<>();
        int num = 1;
        boolean success = true;
        //숫자입력받으면서 스택에 넣기
        //n만큼 반복
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            int now = arr[i];
            //읽어온 숫자보다 작을경우 계속 push
            if (now >= num) {
                while (now >= num) {
                    stack.push(num++);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            }

            else {
                int top = stack.pop();
                // 읽어온 값보다 클 경우
                if (top > now) {
                    System.out.println("NO");
                    success = false;
                    break;
                }
                else {
                    sb.append("-\n");
                }
            }


        }

        if (success)
            System.out.println(sb.toString());
    }
}