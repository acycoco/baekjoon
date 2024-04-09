
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cityN = Integer.parseInt(br.readLine());
        int busN = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        boolean[] visited = new boolean[cityN + 1];
        int[] distance = new int[cityN + 1];
        for (int i = 0; i < cityN + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }
        StringTokenizer st;
        for (int i = 1; i < busN + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new int[]{end, weight});
        }
        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        distance[startCity] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
            return o1[0] - o2[0];
        })); //weight, node

        pq.add(new int[]{0, startCity});
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int weight = now[0];
            int node = now[1];
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            for (int[] next : graph.get(node)) {
                if (visited[next[0]]) {
                    continue;
                }
                if (distance[node] + next[1] < distance[next[0]]) {
                    distance[next[0]] = distance[node] + next[1];
                    pq.add(new int[]{distance[next[0]], next[0]});
                }
            }
        }

        System.out.println(distance[endCity]);

    }
}
