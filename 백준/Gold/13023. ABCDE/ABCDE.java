import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
    static ArrayList<Integer>[] graph;
    static boolean result = false;
    static boolean[] visited;
    static int connection = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
       int nodeNum = Integer.parseInt(st.nextToken());
       int edgeNum = Integer.parseInt(st.nextToken());
       graph = new ArrayList[nodeNum];
        for (int i = 0; i < nodeNum; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        visited = new boolean[nodeNum];

        //모든 요소 for문 dfs
        for (int i = 0; i < nodeNum; i++) {
            if (result)
                break;
            if (!visited[i]) {
                dfs(i, 1);
            }
        }
        //방문 안했을 때만 방문
        if (result)
            System.out.println(1);
        else
            System.out.println(0);
    }

    private static void dfs(int nowVertex, int connected) {
        if (connected >= 5) {
            result = true;
            return;
        }
        //방문 했으면 return
        if (visited[nowVertex]) return;
        //방문했다고 표시
        visited[nowVertex] = true;
        //연결 요소 개수 추가

        //인접 노드 중 방문 안한 노드 dfs호출
        for (int i : graph[nowVertex]) {
            if (!visited[i]) {
                dfs(i, connected + 1);
            }
        }
        visited[nowVertex] = false;
    }
}


