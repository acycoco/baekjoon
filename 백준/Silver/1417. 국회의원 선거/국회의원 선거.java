import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int candidates = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> otherVotes = new PriorityQueue<>(Collections.reverseOrder());
//        PriorityQueue<Integer> otherVotes = new PriorityQueue<>((th, ot) -> th - ot);
//        PriorityQueue<Integer> otherVotes = new PriorityQueue<>(Comparator.comparingInt(o1 - o2));

        int dasom = Integer.parseInt(reader.readLine());

        for (int i = 1; i < candidates; i++) {
            otherVotes.offer(Integer.parseInt(reader.readLine()));
        }

        int answer = 0;
        if (!otherVotes.isEmpty()){//단일 후보가 아닌 경우에만
            while (dasom <= otherVotes.peek()) {
                int someone = otherVotes.poll();
                otherVotes.offer(--someone);
                dasom++;
                answer++;
            }
        }
        return answer;
    
    }


    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }
}
