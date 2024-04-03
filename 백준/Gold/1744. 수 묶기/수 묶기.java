
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int zeroCount = 0;
        int oneCount = 0;
        int result = 0;
        PriorityQueue<Integer> plusQ = new PriorityQueue<>((o1, o2) -> {return (o2 - o1);}); //내림차순
        PriorityQueue<Integer> minusQ = new PriorityQueue<>();
        for (int i = 0; i < n; i++) { //입력을 받음
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                zeroCount++;
            } else if (x == 1) {
                oneCount++;
            } else if (x > 0) {
                plusQ.add(x);
            } else {
                minusQ.add(x);
            }
        }
        //0은 - 중 절댓값이 가장 큰 마이너스랑 묶는 게좋음
        //1은 안묶이는 게 제일 좋음
        //양수는 내림차순 순으로 두개씩 묶는 게 좋음
       //양수 처리
        while (plusQ.size() > 1) {
            int plus1 = plusQ.poll();
            int plus2 = plusQ.poll();
            result += (plus1 * plus2);
        }
        if (plusQ.size() == 1) result += plusQ.poll();

        //1처리
        result += oneCount;

        //음수 처리
        while (minusQ.size() > 1) {
            int minus1 = minusQ.poll();
            int minus2 = minusQ.poll();
            result += (minus1 * minus2);
        }

        if (minusQ.size() % 2 != 0) { //음수개수가 홀수고, 0이 있을때 상쇄시킴
            if (zeroCount >= 1) {
                minusQ.poll(); //하나빼서 버려
            } else {
                result += minusQ.poll();
            }
        }
        //음수가 짝수면 절대값 내림차순으로 두개씩 묶는 게 좋음 // 0은 따로
            //음수개수가 1개일 때는 0이 있으면 0이랑 없으면 그냥
            //1개 초과의 홀수면 0이 있으면 내림차순으로 나머지

        System.out.println(result);
    }
}

