import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public void solution() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int subjects = Integer.parseInt(infoToken.nextToken());
        int compares = Integer.parseInt(infoToken.nextToken());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < subjects + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < compares; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken());
            int to = Integer.parseInt(edgeToken.nextToken());
            adjList.get(from).add(to);
        }


        Queue<int[]> queue = new LinkedList<>();
        int[] semesters = new int[subjects + 1];

        int[] indegrees = new int[subjects + 1];
        for (List<Integer> neighbors: adjList){
            for (int neighbor: neighbors){
                indegrees[neighbor]++;
            }
        }

        for (int i = 1; i < subjects + 1; i++) {
            if (indegrees[i] == 0) queue.offer(new int[]{i, 1});
        }

        while (!queue.isEmpty()){
            int[] node = queue.poll();
            semesters[node[0]] = node[1];

            for (int neighbor: adjList.get(node[0])){
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0) queue.offer(new int[]{neighbor, node[1] + 1});
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < subjects + 1; i++) {
            sb.append(semesters[i]).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}