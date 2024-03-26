import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int cnt = 0;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertexNum = Integer.parseInt(st.nextToken());
        int edgeNum = Integer.parseInt(st.nextToken());

        graph = new ArrayList[vertexNum + 1];

        for (int i = 1; i <= vertexNum; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            graph[vertex1].add(vertex2);
            graph[vertex2].add(vertex1);
        }

        visited = new boolean[vertexNum + 1];
//        for (int i = 1; i <= vertexNum; i++) {
//            visited[i] = false;
//        }

        for (int i = 1; i <= vertexNum; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(i);
            }
        }

        System.out.println(cnt);
    }

    private static void dfs(int nowVertex) {
        //방문한 노드이면 스킵
        if (visited[nowVertex]) { //이게 왜 필요하지??
            return;
        }
        //방문안했으면 방문했다고 표시
        visited[nowVertex] = true;
        //인접 노드 탐색
        for (int next : graph[nowVertex]) {
            //방문안했으면 탐색
            if (!visited[next]) dfs(next);
        }
    }
}


