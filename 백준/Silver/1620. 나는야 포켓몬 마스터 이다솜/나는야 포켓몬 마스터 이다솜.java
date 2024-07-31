
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> alpabet = new HashMap<>();
        Map<Integer, String> number = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            number.put(i, name);
            alpabet.put(name, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String input = br.readLine();
            if (input.matches("\\d+")) {
                sb.append(number.get(Integer.parseInt(input)));
            } else {
                sb.append(alpabet.get(input));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
