
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static boolean[][] visited;
    static Queue<int[]> queue;
    static List<Integer> result;
    static int capaA;
    static int capaB;
    static int capaC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        capaA = Integer.parseInt(st.nextToken());
        capaB = Integer.parseInt(st.nextToken());
        capaC = Integer.parseInt(st.nextToken());
        visited = new boolean[capaA + 1][capaB + 1]; //a, b의 물의 양 상태가 같은 경우를 방문했는지를 체크한다.(c는 자동으로 알 수 잇으므로)

        queue = new LinkedList<>();
        result = new ArrayList<>();
        bfs();
        
        Collections.sort(result);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static void bfs() {
        queue.add(new int[] {0, 0}); //a와 b의 물의 양 초기값
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int a = now[0];
            int b = now[1];
            int c = capaC - (a + b);
            if (a == 0) { //a의 양이 0인 경우
                result.add(c); //답에 c의 양을 추가해준다.
            }
            int water;
            //a->c
            water = Math.min(a, capaC - c);
            // a는 주는 거니까 (현재 양)과 c는 받는 입장이니까 얼마나 받을 수 있을지(용량- 현재양)를 비교해야됨
            moveWater(a - water, b);

            //a->b
            water = Math.min(a, capaB - b);
            moveWater(a - water, b + water);

            //b->a
            water = Math.min(b, capaA - a);
            moveWater(a + water, b - water);

            //b->c
            water = Math.min(b, capaC - c);
            moveWater(a, b - water);

            //c->a
            water = Math.min(c, capaA - a);
            moveWater(a + water, b);

            //c->b
            water = Math.min(c, capaB - b);
            moveWater(a, b + water);

        }
    }

    private static void moveWater(int a, int b) {
        if (!visited[a][b]) {
            visited[a][b] = true;
            queue.add(new int[]{a, b});
        }
    }
}


