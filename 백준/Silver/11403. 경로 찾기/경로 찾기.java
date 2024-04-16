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

        graph = new long[cityN][cityN];

        for (int i = 0; i < cityN; i++) {
            for (int j = 0; j < cityN; j++) {
               //자기 자신으로 가는 엣지도 max로 표시
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < cityN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cityN; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if (weight == 0L) { //0인경우는 못감
                    graph[i][j] = Integer.MAX_VALUE;
                } else {
                    graph[i][j] = weight;
                }
            }
        }

        floyd();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cityN; i++) {
            for (int j = 0; j < cityN; j++) {
                if (graph[i][j] == Integer.MAX_VALUE) {
                    sb.append("0").append(" ");
                } else {
                    sb.append("1").append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void floyd() {
        for (int k = 0; k < cityN; k++) {
            for (int start = 0; start < cityN; start++) {
                for (int end = 0; end < cityN; end++) {
                    graph[start][end] = Math.min(graph[start][end], graph[start][k] + graph[k][end]);
                }
            }
        }
    }
}
