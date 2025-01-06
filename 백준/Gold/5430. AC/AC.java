import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());

            Deque<Integer> deque = new ArrayDeque<>(n);
            String line = br.readLine();
            if (n > 0) {
                String[] numbers = line.substring(1, line.length() - 1).split(",");
                for (String num : numbers) {
                    deque.add(Integer.parseInt(num));
                }
            }
            if (!processCommands(commands, deque, sb)) {
                sb.append("error");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    static void printDeque(Deque<Integer> deque, boolean first, StringBuilder sb) {
        sb.append("[");
        while (!deque.isEmpty()) {
            if (first) {
                sb.append(deque.pollFirst());
            } else {
                sb.append(deque.pollLast());
            }
            if (!deque.isEmpty()) {
                sb.append(",");
            }
        }
        sb.append("]");
    }

    static boolean processCommands(String commands, Deque<Integer> deque, StringBuilder sb) {
        boolean first = true;
        for (int j = 0; j < commands.length(); j++) {
            char command = commands.charAt(j);
            if (command == 'R') {
                first = !first;
            } else {
                if (deque.isEmpty()) {
                    return false;
                }
                if (first) {
                    deque.pollFirst();
                } else {
                    deque.pollLast();
                }
            }
        }
        printDeque(deque, first, sb);
        return true;
    }
}
