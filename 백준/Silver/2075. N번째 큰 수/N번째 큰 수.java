import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        //우선순위 큐를 만든다.
        //최솟값이 먼저 나오는 우선순위 큐
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {

                minHeap.offer(Integer.parseInt(tokenizer.nextToken()));
                if (minHeap.size() > n){
                    minHeap.poll();
                }
            }

        }
        return minHeap.peek();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }
}
