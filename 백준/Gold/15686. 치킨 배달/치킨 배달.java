import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int[][] city;
    static List<int[]> chicken;
    static List<int[]> house;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        city = new int[n][n];
        chicken = new ArrayList<>();
        house = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 2) {
                    chicken.add(new int[]{i + 1, j + 1});
                } else if (city[i][j] == 1) {
                    house.add(new int[]{i + 1, j + 1});
                }
            }
        }
        //빈 칸 0, 치킨집 2, 집 1
        //1 based
        //치킨거리는 집과 가장 가까운 치킨집 사이의 거리이다.
        //각자의 집은 치킨 거리를 가지고 있다.
        //도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
        //최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다.
        //어떻게 고르면 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램
        comb(0,0, new boolean[chicken.size()]);
        System.out.println(answer);
    }

    public static void comb(int depth, int start, boolean[] selected) {
        if (depth == m) {
            int sum = 0;
            for(int[] h: house) {
                sum += calcDistance(selected, h[0], h[1]);
            }
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            selected[i] = true;
            comb(depth + 1, i + 1, selected);
            selected[i] = false;
        }
    }

    public static int calcDistance(boolean[] selected, int x, int y) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chicken.size(); i++) {
            if (selected[i]) {
                min = Math.min(Math.abs(x - chicken.get(i)[0]) + Math.abs(y - chicken.get(i)[1]), min);
            }
        }
        return min;
    }
}
