import java.util.*;
import java.io.*;

public class Main {
    static List<int[]>[] graph;
    static long[] minDistanceOfFox;
    static long[][] minDistanceOfWolf;
    static int n, m;
    static final long INF = Long.MAX_VALUE / 4;   // 오버플로 방지용 충분히 큰 값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        minDistanceOfFox = new long[n + 1];
        minDistanceOfWolf = new long[2][n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            minDistanceOfFox[i] = INF;
            minDistanceOfWolf[0][i] = INF;
            minDistanceOfWolf[1][i] = INF;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] { b, d });
            graph[b].add(new int[] { a, d });  
        }

        getMinDistancesOfFox();
        getMinDistancesOfWolf();

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            long wolfBest = Math.min(minDistanceOfWolf[0][i], minDistanceOfWolf[1][i]);
            if (minDistanceOfFox[i] < wolfBest) answer++;
        }
        System.out.println(answer);
    }

    // 여우: 모든 간선 비용을 2×d 로 사용
    static void getMinDistancesOfFox() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        minDistanceOfFox[1] = 0;
        pq.add(new Node(0, 1));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.distance != minDistanceOfFox[cur.node]) continue;

            for (int[] e : graph[cur.node]) {
                int nxt = e[0];
                long nd = cur.distance + (long) e[1] * 2;   // 2·d
                if (nd < minDistanceOfFox[nxt]) {
                    minDistanceOfFox[nxt] = nd;
                    pq.add(new Node(nd, nxt));
                }
            }
        }
    }

    //늑대: state 0 = fast, 1 = slow 
    static void getMinDistancesOfWolf() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        minDistanceOfWolf[0][1] = 0;           // 시작은 빠름
        pq.add(new Node(0, 1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.distance != minDistanceOfWolf[cur.state][cur.node]) continue;

            for (int[] e : graph[cur.node]) {
                int nxt = e[0];
                long cost = (cur.state == 0) ? e[1]          // fast: d
                                             : (long) e[1] * 4; // slow: 4·d
                long nd = cur.distance + cost;
                int nextState = 1 - cur.state;

                if (nd < minDistanceOfWolf[nextState][nxt]) {
                    minDistanceOfWolf[nextState][nxt] = nd;
                    pq.add(new Node(nd, nxt, nextState));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        long distance;
        int node;
        int state;               // 0: fast, 1: slow

        Node(long distance, int node) {
            this.distance = distance;
            this.node = node;
            this.state = 0;
        }

        Node(long distance, int node, int state) {
            this.distance = distance;
            this.node = node;
            this.state = state;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }
    }
}
