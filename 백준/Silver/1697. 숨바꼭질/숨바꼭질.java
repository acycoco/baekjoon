import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int syster = Integer.parseInt(st.nextToken());

        int max = 0;
        if (syster > subin) {
            max = syster;
        } else {
            max = subin;
        }
        boolean[] visited = new boolean[100001];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{subin, 0});

        if (subin == syster) {
            System.out.println(0);
            return;
        }
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            visited[now[0]] = true;

            int next1 = now[0] - 1;
            if (next1 == syster) {
                System.out.println(now[1] + 1);
                break;
            }
            if (next1 >= 0 && !visited[next1]) {
                queue.add(new int[]{next1, now[1] + 1});
            }
            int next2 = now[0] + 1;
            if (next2 == syster) {
                System.out.println(now[1] + 1);
                break;
            }
            if (next2 <= 100000 && !visited[next2]) {
                queue.add(new int[]{next2, now[1] + 1});
            }
            int next3 = now[0] * 2;
            if (next3 == syster ) {
                System.out.println(now[1] + 1);
                break;
            }
            if (next3 <= 100000 && !visited[next3]) {
                queue.add(new int[]{next3, now[1] + 1});
            }
        }
    }

}
