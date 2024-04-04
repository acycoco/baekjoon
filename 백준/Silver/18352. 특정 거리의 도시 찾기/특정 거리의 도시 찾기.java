import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static ArrayList<Integer>[] graph;
    static int nodeNum;
    static int edgeNum;
    static int k;
    static ArrayList<Integer> result;
    static Queue<Integer> queue;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodeNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        graph = new ArrayList[nodeNum + 1];

        for (int i = 1; i < nodeNum + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            graph[node1].add(node2);
        }

        queue = new LinkedList<>();
        visited = new int[nodeNum + 1];
        for (int i = 0; i < nodeNum + 1; i++) {
            visited[i] = -1;
        }
        bfs(startNode);


        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < nodeNum + 1; i++) {
            if (visited[i] == k) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(result);
            for (int i : result) {
                System.out.println(i);
            }
        }
    }

    private static void bfs(int x) {
        queue.add(x);
        visited[x] = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (visited[now] > k) break;
            for (int next: graph[now]) {
                if (visited[next] == -1) {
                    visited[next] = visited[now] + 1;
                    queue.add(next);
                }
            }
        }
    }
}


