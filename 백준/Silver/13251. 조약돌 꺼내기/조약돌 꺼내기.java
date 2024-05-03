import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int colorN = Integer.parseInt(br.readLine());
        int[] colorStone = new int[colorN];
        long totalStoneN = 0;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < colorN; i++) {
            colorStone[i] = Integer.parseInt(st.nextToken());
            totalStoneN += colorStone[i];
        }
        int pickN = Integer.parseInt(br.readLine());

        double probability = 0.0;
        //경우의 수 구하기
        for (int i = 0; i < colorN; i++) {
            if (colorStone[i] < pickN) {
                continue;
            }
            double probabilityWithColor = 1.0;
            for (int j = 0; j < pickN; j++) {
                probabilityWithColor *= ((double) (colorStone[i] - j) / (totalStoneN - j));
            }
            probability += probabilityWithColor;
        }
        //확률 구하기

        System.out.println(probability);
    }
}
