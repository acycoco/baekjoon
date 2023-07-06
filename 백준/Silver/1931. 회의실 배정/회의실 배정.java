import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = Integer.parseInt(reader.readLine());
        int[][] meetings = new int[meetingCount][2];

        for (int i = 0; i < meetingCount; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            meetings[i][0] = Integer.parseInt(tokenizer.nextToken());
            meetings[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(
                meetings,
//                Comparator.comparingInt(o -> o[1])
                (o1, o2) -> {
                    // o1은 {시작시간, 종료시간}
                    // o2도 {시작시간, 종료시간}
                    // 종료 시간이 다르면 종료시간 기준 비교
                    if (o1[1] != o2[1]) return o1[1] - o2[1];
                    // 아니라면 시작 시간 기준 비교
                    return o1[0] - o2[0];
                }
        );

        


        //함수형 프로그래밍 -> 인자를 함수로 전달할 수 있다

        int answer = 0;
        int lastEnd = 0;
        for (int i = 0; i < meetingCount; i++) {
            //i번째 미팅의 시작시간과 현재의 lastEnd와 비교한다.
            if (lastEnd <= meetings[i][0]){
                lastEnd = meetings[i][1];
                answer++;
            }

        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }
    //2
    //4 4
    //1 4        ->2
}
