import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int[][] roadMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                roadMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int[] road = new int[n];
            System.arraycopy(roadMap[i], 0, road, 0, n);
            if (canBeRoad(road)) {
                answer++;
            }
        }

        for (int i = 0; i < n; i++) {
            int[] road = new int[n];
            for (int j = 0; j < n; j++) {
                road[j] = roadMap[j][i];
            }
            if (canBeRoad(road)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static boolean canBeRoad(int[] road) {
        boolean[] visited = new boolean[n];
        int index = 0;
        while(index < n - 1) {
            if(road[index] + 1 == road[index + 1]) {
                if (!surroundL(index - l + 1, index, road[index], road, visited)) {
                    return false;
                }
                index = index + 1;
            } else if (road[index] - 1 == road[index + 1]) {
                if (!surroundL(index + 1, index + l, road[index + 1], road, visited)) {
                    return false;
                }
                index = index + l;
            } else if (road[index] == road[index + 1]) {
                index++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean surroundL(int start, int end, int target, int[] road, boolean[] visited) {
        if (start < 0 || end >= n) {
            return false;
        }
        for (int i = start; i <= end; i++) {
            if (road[i] != target || visited[i]) {
                return false;
            }
            visited[i] = true;
        }
        return true;
    }
}
