
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        //이분탐색

        Map<String, String> employee = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String log = st.nextToken();
            if (log.equals("enter")) {
                employee.put(name, log);
            } else {
                employee.remove(name);
            }
        }

        List<String> answer = new ArrayList<>(employee.keySet());
        Collections.sort(answer, Collections.reverseOrder());
        for (String name : answer) {
            bw.write(name + "\n");
        }

        bw.flush();
        bw.close();
    }
}
