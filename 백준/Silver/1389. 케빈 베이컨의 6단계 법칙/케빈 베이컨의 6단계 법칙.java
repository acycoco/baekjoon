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
        StringTokenizer st = new StringTokenizer(br.readLine());
        cityN = Integer.parseInt(st.nextToken());
        busN = Integer.parseInt(st.nextToken());

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

        for (int i = 0; i < busN; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start][end] = 1;
            graph[end][start] = 1;
        }

        floyd();

        long sum;
        int idx = 1;
        long min = Integer.MAX_VALUE;
        for (int i = 1; i < cityN + 1; i++) {
            sum = 0;
            for (int j = 1; j < cityN + 1; j++) {
                sum += graph[i][j];
            }
            if (sum < min) {
                idx = i;
                min = sum;
            }
        }

        System.out.println(idx);
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
