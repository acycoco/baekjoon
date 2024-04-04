import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static ArrayList<Integer>[] graph;
    static int testCase;
    static int nodeNum;
    static int edgeNum;
    static Queue<Integer> queue;
    static boolean[] visited;
    static boolean bipartite;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodeNum = Integer.parseInt(st.nextToken());
            edgeNum = Integer.parseInt(st.nextToken());
            graph = new ArrayList[nodeNum + 1];

            for (int j = 1; j < nodeNum + 1; j++) {
                graph[j] = new ArrayList<>();
            }
            bipartite = true;
            for (int j = 0; j < edgeNum; j++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                graph[node1].add(node2);
                graph[node2].add(node1);
            }


            check = new int[nodeNum + 1];
            visited = new boolean[nodeNum + 1];
            for (int j = 1; j < nodeNum + 1; j++) {
                if (!visited[j])
                    dfs(j);
                if (!bipartite) {
                    break;
                }
            }

            if (bipartite) System.out.println("YES");
            else System.out.println("NO");
        }


    }

    private static void dfs(int x) {
        visited[x] = true;
        for(int next: graph[x]) {
            if (!visited[next]) {
                check[next] = (check[x] + 1) % 2; //1이나 0으로 저장하기 위해서 (집합)
                dfs(next);
            } else {
                if (check[next] == check[x]) {
                    bipartite = false;
                    return;
                }
            }
        }
    }
}