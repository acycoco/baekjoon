
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cityN = Integer.parseInt(st.nextToken());
        int busN = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < busN; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, weight));
        }

        int start = 1;
        //초기화
        long[] distance = new long[cityN + 1];
        for (int i = 1; i < cityN + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        //출발은 1번도시
        distance[start] = 0;

        //음수사이클이 있다면 -1 출력
        //아니면 각도시마다 최단거리 출력

        for (int i = 0; i < cityN - 1; i++) { //n-1번 실행
            for (Edge edge : edges) { //엣지리스트 모두 돌면서
                if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.weight) {
                    distance[edge.end] = distance[edge.start] + edge.weight;
                }
            }
        }

        boolean cycle = false;
        for (Edge edge : edges) { //엣지리스트 모두 돌면서
            if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.weight) {
                cycle = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();


        if (cycle) {
            sb.append("-1");
        } else {
            for (int i = 2; i < cityN + 1; i++) { //1번 도시 제외, 0번 idx제외
                if (distance[i] == Integer.MAX_VALUE) {
                    sb.append("-1\n");
                } else {
                    sb.append(distance[i] + "\n");
                }
            }
        }

        System.out.println(sb);

    }

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
