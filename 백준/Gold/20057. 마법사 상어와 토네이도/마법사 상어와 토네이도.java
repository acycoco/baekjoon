import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int x;
    static int y;
    static int[] dx = {0, 1, 0, -1}; //서, 남, 동, 북
    static int[] dy= {-1, 0, 1, 0};
    static int answer;
    public static void main(String[] args) throws IOException {
        //토네이도는 한번에 한칸 이동
        //토네이도 도착하는 y의 모든 모래가
        //비율에는 y * 비율만큼 이동(소수점 아래 버림), a에는 나머지
        //모래의 양은 더해진다
        //1,1에서 소멸
        //격자밖으로 나갈때 나간 모래의 양

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        x = (n - 1) / 2;
        y = x;
        int distance = 1;
        int direction = 0;
        while(true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < distance; j++) {
                    if (!move(direction)) {
                        System.out.println(answer);
                        return;
                    }
                }
                direction = (direction + 1) % 4;
            }
            distance++;
        }
    }

    static boolean move(int direction) {
        x += dx[direction];
        y += dy[direction];

        if (x >= 0 && x < n && y >= 0 && y < n) {
            moveSand(direction, x, y);
        } else {
            return false;
        }
        return true;
    }

    static void moveSand(int direction, int i, int j) {
        int amount = arr[i][j];
        int west = (direction + 1) % 4;
        int east = (direction + 3) % 4;
        int behind = (direction + 2) % 4;

        int sum = 0;
        if (amount * 0.1 > 0) { //10%
            sum += moveSandAmount(i + dx[direction] + dx[west], j + dy[direction] + dy[west], (int) (0.1 * amount));
            sum += moveSandAmount(i + dx[direction] + dx[east], j + dy[direction] + dy[east], (int) (0.1 * amount));
        }

        if (amount * 0.05 > 0) { //5%
            sum += moveSandAmount(i + dx[direction] * 2, j + dy[direction] * 2, (int) (0.05 * amount));
        }

        if (amount * 0.07 > 0) { //7%
            sum += moveSandAmount(i + dx[west], j + dy[west], (int) (0.07 * amount));
            sum += moveSandAmount(i + dx[east], j + dy[east], (int) (0.07 * amount));
        }


        if (amount * 0.02 > 0) { //2%
            sum += moveSandAmount(i + dx[west] * 2, j + dy[west] * 2, (int) (0.02 * amount));
            sum += moveSandAmount(i + dx[east] * 2, j + dy[east] * 2, (int) (0.02 * amount));
        }


        if (amount * 0.01 > 0) { //1%
            sum += moveSandAmount(i + dx[behind] + dx[west], j + dy[behind] + dy[west], (int) (0.01 * amount));
            sum += moveSandAmount(i + dx[behind] + dx[east], j + dy[behind] + dy[east], (int) (0.01 * amount));
        }

        moveSandAmount(i + dx[direction], j + dy[direction], amount - sum);
    }

    static int moveSandAmount(int i, int j, int amount) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            answer += amount;
            return amount;
        }
        arr[i][j] += amount;
        return amount;
    }

}
