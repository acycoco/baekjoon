
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int edgeNum = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(br.readLine());

        for (int i = 0; i < nodeNum + 1; i++) {
            graph.add(new ArrayList<>());
        }
        //간선 입력받기
        for (int i = 1; i < edgeNum + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new int[]{v, w});
        }

        //다익스트라 알고리즘
        boolean[] visited = new boolean[nodeNum + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1]; //거리 기준으로 정렬
        });
        int[] distance = new int[nodeNum + 1];
        //초기화
        for (int i = 0; i < nodeNum + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[startNode] = 0; //시작노드만 0
        pq.add(new int[]{startNode, distance[startNode]});


        while (!pq.isEmpty()) {
            int[] minNode = pq.poll();
            int minIdx = minNode[0];
            //방문한 노드면 스킵
            if (visited[minIdx]) {
                continue;
            }
            visited[minIdx] = true;
            for (int[] next : graph.get(minIdx)) {
                int nextNode = next[0];
                int weight = next[1];
                //방문한 노드면 스킵
                if (visited[nextNode]) {
                    continue;
                }
                if (distance[minIdx] + weight < distance[nextNode]) {
                    distance[nextNode] = distance[minIdx] + weight;
                    pq.add(new int[]{nextNode, distance[nextNode]});
                }
            }
        }
        for (int i = 1; i < nodeNum + 1; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}
