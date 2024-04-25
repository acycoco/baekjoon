
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] parent;
    static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        bfs();

        for (int i = 2; i < n + 1; i++) {
            System.out.println(parent[i]);
        }
    }

    public static void bfs() {
        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    parent[next] = now;
                }
            }
        }
    }
}
