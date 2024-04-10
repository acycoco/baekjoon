import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Edge>> graph;
    static int cityN;
    static int roadN;
    static int k;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    static ArrayList<PriorityQueue<Integer>> kDistance; //모든 distance 추가
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        cityN = Integer.parseInt(st.nextToken());
        roadN = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        kDistance = new ArrayList<>();
        visited = new boolean[cityN + 1];
        //초기화
        for (int i = 0; i < cityN + 1; i++) {
            graph.add(new ArrayList<>());
            kDistance.add(new PriorityQueue<>(Collections.reverseOrder())); //내림차순
        }
        pq = new PriorityQueue<>();

        //인접리스트
        for (int i = 0; i < roadN; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, weight)); //이래도 되나?
        }
        dijkstra();
        for (int i = 1; i < cityN + 1; i++) {
            if (kDistance.get(i).size() == k) {
                System.out.println(kDistance.get(i).poll());
            } else {
                System.out.println(-1);
            }
        }

    }

    private static void dijkstra() {
        pq.add(new Edge(1, 0));
        kDistance.get(1).add(0);
        while (!pq.isEmpty()) {
            Edge nowNode = pq.poll();
            int now = nowNode.node;
            for (Edge nextNode : graph.get(now)) {
                int next = nextNode.node;
                int newWeight = nowNode.weight + nextNode.weight;
                if (kDistance.get(next).size() < k) {
                    kDistance.get(next).add(newWeight);
                    pq.add(new Edge(next, newWeight));
                } else if (kDistance.get(next).peek() > newWeight) {
                    kDistance.get(next).poll();
                    kDistance.get(next).add(newWeight);
                    pq.add(new Edge(next, newWeight));
                }
                //k번째를 구하기 위해 방문한적이 있어도, 최단거리가 아니어도 저장해줌
            }
        }
    }
    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
