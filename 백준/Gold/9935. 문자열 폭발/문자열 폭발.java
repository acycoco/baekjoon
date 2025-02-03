import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        // 모든 폭발 문자열이 폭발
        // 폭발 문자열이 없을때까지
        //


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);
           
            if (sb.length() >= bomb.length() && 
                sb.substring(sb.length() - bomb.length()).equals(bomb)
            ) {
                sb.delete(sb.length() - bomb.length(), sb.length());
            }
        }

       

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}
