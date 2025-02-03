import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[] bomb;
    public static void main(String[] args) throws IOException {
        // 모든 폭발 문자열이 폭발
        // 폭발 문자열이 없을때까지
        //


        Stack<Character> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        bomb = br.readLine().toCharArray();

        for (int i = 0; i < input.length; i++) {
            stack.push(input[i]);

            if (stack.size() >= bomb.length) {
                boolean matched = true;
                for (int j = 0; j < bomb.length; j++) {
                    if (stack.get(stack.size() - bomb.length + j) != bomb[j]) {
                        matched = false;
                        break;
                    }
                }

                if (matched) {
                    for (int j = 0; j < bomb.length; j++) {
                        stack.pop();
                    }
                }
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char ch :  stack) {
            stringBuilder.append(ch);
        }

        if (stringBuilder.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(stringBuilder);
        }
    }
}
