import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[][] graph;
    static int cityN;
    static int busN;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityN = Integer.parseInt(br.readLine());
        busN = Integer.parseInt(br.readLine());

        graph = new long[cityN + 1][cityN + 1];

        for (int i = 1; i < cityN + 1; i++) {
            for (int j = 1; j < cityN + 1; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                    continue;
                }
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < busN; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (weight < graph[start][end]) { //시작도시와 도착 도시를 연결하는 노선이 여러개인 경우 더 작은 가중치를 저장한다.
                graph[start][end] = weight;
            }
        }

        floyd();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < cityN + 1; i++) {
            for (int j = 1; j < cityN + 1; j++) {
                if (graph[i][j] == Integer.MAX_VALUE) {
                    sb.append("0").append(" ");
                } else {
                    sb.append(graph[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void floyd() {
        for (int k = 1; k < cityN + 1; k++) {
            for (int start = 1; start < cityN + 1; start++) {
                for (int end = 1; end < cityN + 1; end++) {
                    graph[start][end] = Math.min(graph[start][end], graph[start][k] + graph[k][end]);
                }
            }
        }
    }
}
