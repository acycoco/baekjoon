import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public long solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer info = new StringTokenizer(reader.readLine());
        int cardCnt = Integer.parseInt(info.nextToken());
        int actions = Integer.parseInt(info.nextToken());

        StringTokenizer cardToken = new StringTokenizer(reader.readLine());
        PriorityQueue<Long> cards = new PriorityQueue<>();

        for (int i = 0; i < cardCnt; i++) {
            cards.offer(Long.parseLong(cardToken.nextToken()));
        }

        for (int i = 0; i < actions; i++) {
            long card1 = cards.poll();
            long card2 = cards.poll();
            cards.offer(card1 + card2);
            cards.offer(card1 + card2);
        }

        long answer = 0;
        while(!cards.isEmpty()){
            answer += cards.poll();
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }
}
