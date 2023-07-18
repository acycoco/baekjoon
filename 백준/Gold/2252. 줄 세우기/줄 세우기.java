import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int students = Integer.parseInt(infoToken.nextToken());
        int compares = Integer.parseInt(infoToken.nextToken());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < students + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < compares; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken());
            int to = Integer.parseInt(edgeToken.nextToken());
            adjList.get(from).add(to);
        }


        int[] indegrees = new int[students + 1];

        //1.진입 차수 구하기
        for (List<Integer> neighbors: adjList) {
            for (int neighbor: neighbors){
                indegrees[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= students; i++) {
            if (indegrees[i] == 0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            int node = queue.poll();
            result.add(node);

            for (int neighbor: adjList.get(node)){
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int node: result){
            sb.append(node).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {

        new Main().solution();
    }
}
