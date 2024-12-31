import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("all")) {
                s = 0xfffff;
            } else if (command.equals("empty")) {
                s = 0;
            } else {
                int number = Integer.parseInt(st.nextToken()) - 1;
                if (command.equals("add")) {
                    s |= (1 << number);
                } else if (command.equals("remove")) {
                    s &= (~(1 << number));
                } else if (command.equals("toggle")) {
                    s ^= (1 << number);
                } else if (command.equals("check")) {
                    sb.append((s >> number) & 1);
                    sb.append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}