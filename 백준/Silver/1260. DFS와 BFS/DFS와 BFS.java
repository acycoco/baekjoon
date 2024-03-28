import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();
    static int nodeNum;
    static int edgeNum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodeNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        graph = new ArrayList[nodeNum + 1];

        for (int i = 1; i < nodeNum + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[nodeNum + 1];
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        for (int i = 1; i < nodeNum + 1; i++) {
            Collections.sort(graph[i]);
        }

        dfs(startNode);
        sb.append("\n");

        for (int i = 1; i < nodeNum + 1; i++) {
            visited[i] = false;
        }

        bfs(startNode);
        System.out.println(sb);
    }

    private static void dfs(int now) {
        if (visited[now]) return;
        visited[now] = true;
        sb.append(now).append(" ");
        //이웃조회
        for (int next : graph[now]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
    private static void bfs(int now) {
        queue.offer(now);
        visited[now] = true;

        while(!queue.isEmpty()) {
            int node = queue.poll();
            sb.append(node).append(" ");

            for (int next: graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}


