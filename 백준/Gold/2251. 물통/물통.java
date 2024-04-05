
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
    static int[] capa;
    static int[] sender = {0, 0, 1, 1, 2, 2};
    static int[] receiver = {1, 2, 0, 2, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        capaA = Integer.parseInt(st.nextToken());
        capaB = Integer.parseInt(st.nextToken());
        capaC = Integer.parseInt(st.nextToken());
        visited = new boolean[capaA + 1][capaB + 1]; //a, b의 물의 양 상태가 같은 경우를 방문했는지를 체크한다.(c는 자동으로 알 수 잇으므로)

        capa = new int[]{capaA, capaB, capaC};
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

            for (int i = 0; i < 6; i++) {
                int[] next = new int[]{a, b, c};
                int s = sender[i];
                int r = receiver[i];
                if (next[s] > 0 && next[r] < capa[r]) {
                    water = Math.min(next[s], capa[r] - next[r]);
                    next[s] = next[s] - water;
                    next[r] = next[r] + water;
                    if (!visited[next[0]][next[1]]) {
                        visited[next[0]][next[1]] = true;
                        queue.add(new int[]{next[0], next[1]});
                    }
                }
            }


        }
    }

    private static void moveWater(int a, int b) {
        if (!visited[a][b]) {
            visited[a][b] = true;
            queue.add(new int[]{a, b});
        }
    }
}


