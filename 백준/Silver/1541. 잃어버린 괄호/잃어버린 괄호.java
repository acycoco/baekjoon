import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        long sum = 0;

        boolean plus = true; //더해야되는지 빼야되는지
        boolean minus = false; //마이너스가 등장했는지
        for(Character c: str.toCharArray()) {
            if (!Character.isDigit(c)) {
                //minus가 true면 마이너스 //plus에 따라서 해야됨 (minus일 경우는 당연히
                if (minus || !plus) { //마이너스가 등장했으면 당연히 마이너스
                    sum -= Integer.parseInt(sb.toString());
                } else {
                    sum += Integer.parseInt(sb.toString());
                }
                sb = new StringBuilder();
                if (c == '+') {
                    plus = true;
                } else {
                    plus = false;
                    minus = true;
                }
            } else {
                sb.append(c);
            }
        }

        if (minus || !plus) { //마이너스가 등장했으면 당연히 마이너스
            sum -= Integer.parseInt(sb.toString());
        } else {
            sum += Integer.parseInt(sb.toString());
        }


        System.out.println(sum);
    }


}
