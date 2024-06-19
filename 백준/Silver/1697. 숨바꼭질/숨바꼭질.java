
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int syster = Integer.parseInt(st.nextToken());

        int[] dist = new int[200001];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(subin);
        dist[subin] = 0;

        if (subin == syster) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            int next1 = now - 1;
            if (next1 == syster) {
                System.out.println(dist[now] + 1);
                break;
            }
            if (next1 >= 0 && dist[next1] == -1) {
                dist[next1] = dist[now] + 1;
                queue.add(next1);
            }
            int next2 = now + 1;
            if (next2 == syster) {
                System.out.println(dist[now] + 1);
                break;
            }
            if (next2 <= 200000 && dist[next2] == -1) {
                dist[next2] = dist[now] + 1;
                queue.add(next2);
            }
            int next3 = now * 2;
            if (next3 == syster) {
                System.out.println(dist[now] + 1);
                break;
            }
            if (next3 <= 200000 && dist[next3] == -1) {
                dist[next3] = dist[now] + 1;
                queue.add(next3);
            }
        }
    }

}
