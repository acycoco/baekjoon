import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.add(Long.parseLong(br.readLine()));
        }

        int result = 0;
        while (queue.size() > 1) {
            Long card1 = queue.poll();
            Long card2 = queue.poll();

            Long card3 = card1 + card2;
            result += card3;

            queue.add(card3);
        }


        System.out.println(result);
    }
}