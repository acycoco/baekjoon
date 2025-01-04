import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>(); //인덱스를 저장
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append(0);
            } else {
                sb.append(stack.peek() + 1);
            }
            sb.append(" ");
            stack.push(i);
        }

        System.out.println(sb);
    }
}
