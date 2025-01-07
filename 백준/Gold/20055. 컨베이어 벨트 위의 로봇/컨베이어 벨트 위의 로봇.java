import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int k;
    static int[] arr;
    static boolean[] robot;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[2 * n];
        robot = new boolean[2 * n];
        //1에서 올리고 n에서 내리고

        //이동하려는 칸에 로봇이 없고, 내구도 >=1
        //내구도가 0이 아니면 올릴 수 있다.
        //0인 칸이 k개 이상이면 종료
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        System.out.println(rotate());

    }

    static int rotate() {
        int step = 0;
        while (true) {
            moveConvelt();
            moveRobot();
            putOnRobot();
            step++;
            if (stopRotate()) {
                return step;
            }
        }
    }

    static void moveConvelt() {
        int temp = arr[2 * n - 1];
        for (int i = 2 * n - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = temp;

        for (int i = n - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false;
        robot[n - 1] = false;//즉시내린다.
    }


    static void moveRobot() {

        for (int i = n - 2; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && arr[i + 1] >= 1) {
                robot[i + 1] = true;
                robot[i] = false;
                arr[i + 1]--;
                if (arr[i + 1] == 0) {
                    cnt++;
                }
            }
        }
        robot[n - 1] = false; // 즉시 내린다.
    }
    //로봇이 올리거나 어떤칸으로 이동 시 내구도 -1

    static boolean stopRotate() {
        
        return cnt >= k;
    }

    static void putOnRobot() {
        //0,0 이 내구도가 0이 아니면 로봇을 올린다.
        if (arr[0] != 0) {
            robot[0] = true;
            arr[0]--;
            if (arr[0] == 0) {
                cnt++;
            }
        }
    }
}
