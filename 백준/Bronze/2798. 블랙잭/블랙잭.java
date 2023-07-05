import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer intfoParser = new StringTokenizer(reader.readLine());
        int cardCount = Integer.parseInt(intfoParser.nextToken());
        int target = Integer.parseInt(intfoParser.nextToken());

        StringTokenizer cardTokens = new StringTokenizer(reader.readLine());
        int[] cards = new int[cardCount];
        for (int i = 0; i < cardCount; i++) {
            cards[i] = Integer.parseInt(cardTokens.nextToken());
        }

        int answer = 0;

        for (int i = 0; i < cardCount - 2; i++) {
            for (int j = i + 1; j < cardCount - 1; j++) {
                for (int k = j + 1; k < cardCount; k++) {
                    int sum = cards[i] + cards[j]+ cards[k];
                    if (target >= sum && answer < sum) answer = sum;

                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }
}
