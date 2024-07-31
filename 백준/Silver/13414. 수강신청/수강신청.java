import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxStudent = Integer.parseInt(st.nextToken());
        int listN = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < listN; i++) {
            String studentNo = br.readLine();
            if (map.containsKey(studentNo)) {
                map.remove(studentNo);
                map.put(studentNo, 1);
            } else {
                map.put(studentNo, 1);
            }

        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            sb.append(entry.getKey() + "\n");
            idx++;
            if (idx == maxStudent) {
                break;
            }
        }
        System.out.println(sb);
    }
}
