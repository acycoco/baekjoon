import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cityN = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
        int busN = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < busN; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken()) * -1; //비용
            edges.add(new Edge(start, end, weight));
        }

        //초기화
        long[] distance = new long[cityN];
        for (int i = 0; i < cityN; i++) {
            distance[i] = Integer.MIN_VALUE;
        }


        int[] money = new int[cityN];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cityN; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }
        distance[startNode] = money[startNode];

        //가중치를 money값을 더해줌
        for (Edge edge : edges) {
            edge.weight += money[edge.end];
        }
        //양수사이클이 있다면 Gee 출력
        //아니면 각도시마다 최장거리 출력

        for (int i = 0; i < 2 * cityN; i++) { //n-1번 실행x -> 양수사이클이랑 연결된(하지만 양수사이클안에는 있지 않은) 노드에 양수사이클을 전파시켜야됨
            for (Edge edge : edges) { //엣지리스트 모두 돌면서
                if (distance[edge.start] != Integer.MIN_VALUE && distance[edge.end] < distance[edge.start] + edge.weight) {
                    distance[edge.end] = distance[edge.start] + edge.weight;
                    if (i > cityN - 1) { //n-1번보다 더 큰 경우에 업데이트가 되면 양수사이클인거다.
                        distance[edge.end] = Integer.MAX_VALUE;
                    }
                }

                if (distance[edge.start] == Integer.MAX_VALUE) { //start가 max이면(양수사이클에 포함되어있으면) end도 max가 된다.
                    distance[edge.end] = Integer.MAX_VALUE;
                }
            }
        }


        StringBuilder sb = new StringBuilder();


        //도착도시의 거리가 min max일 경우 gg출력

        if (distance[endNode] == Integer.MIN_VALUE) {
            sb.append("gg");
        } else {
            if (distance[endNode] == Integer.MAX_VALUE) {
                sb.append("Gee");
            } else {
                sb.append(distance[endNode]);
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

