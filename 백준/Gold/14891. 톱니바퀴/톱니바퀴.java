import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] wheel = new int[4][8];
    static int n;
    static int[] turn = new int[4];
    static int[] score = new int[]{1, 2, 4, 8};
    public static void main(String[] args) throws IOException {
        //시계 1 반시계 -1
        //N 0 S 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            turn[target] = d;
            leftChange(target);
            rightChange(target);
            finishTurn();
//            System.out.println(i);
//            System.out.println(Arrays.toString(turn));
            for (int j = 0; j < 4; j++) {
//                System.out.println(Arrays.toString(wheel[j]));
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (wheel[i][0] == 1) {
                answer += score[i];
            }
        }

        System.out.println(answer);
    }

    public static void leftChange(int target) {
        if (target < 0) {
            return;
        }
        int startIdx = 6;
        for (int i = target - 1; i >= 0; i--) {
            turn[i] = touched(turn[i + 1], wheel[i][(startIdx + 4) % 8] == wheel[i + 1][startIdx]);
        }
    }

    public static void rightChange(int target) {
        if (target + 1 >= 4) {
            return;
        }
        int startIdx = 2;
        for (int i = target; i <= 2; i++) {
            turn[i + 1] = touched(turn[i], wheel[i][startIdx] == wheel[i + 1][(startIdx + 4) % 8]);
        }
    }
    //맞닿은 극이 같으면 회전하지 않는다.
    //다르면 다른 방향으로 회전한다.
    //회전하지 않으면 회전하지 않는다.
    public static int touched(int turn, boolean same) {
        if (turn == 0) {
            return 0;
        } else {
            if (same) {
                return 0;
            } else {
                return -(turn);
            }
        }
    }

    public static void finishTurn() {
        for (int i = 0; i < 4; i++) {
            int[] newWheel = new int[8];
            if (turn[i] == 1) {
                for (int j = 0; j < 8; j++) {
                    newWheel[j] = wheel[i][(j + 7) % 8];
                }
            } else if (turn[i] == -1) {
                for (int j = 0; j < 8; j++) {
                    newWheel[j] = wheel[i][(j + 1) % 8];
                }
            } else {
                continue;
            }
            System.arraycopy(newWheel, 0, wheel[i], 0, 8);
        }
    }
}
