
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
       //n입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        //큐에 순서대로 넣어줌 (작은수부터)
        Queue<Integer> queue = new LinkedList<>();
        //하나를 poll함
        for (int i = 1; i < n + 1; i++) {
            queue.add(i);
        }
        //하나를 poll해서 넣음
        while (queue.size() > 1) {
            queue.poll();
            if (!queue.isEmpty()) {
                queue.add(queue.poll());
            }
        }
        System.out.println(queue.peek());
        //empty일때까지 반복한다
    }
}