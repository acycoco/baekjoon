import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int answer;
    public static void main(String[] args) throws IOException {


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

        dfs(0, arr);
        System.out.println(answer);
    }

    static void dfs(int depth, int[][] arr) {
        if (depth == 5) {
            answer = Math.max(answer, getMax(arr));
            return;
        }

        //4방향으로
        for (int i = 0; i < 4; i++) {
            int[][] newArr = new int[n][n];
            for (int j = 0; j < n; j++) {
                int[] line = getLine(arr, i, j);
                playLine(line);
                writeLine(newArr, line, i, j);
            }
            //한 줄씩
            //바뀐 한줄로 저장

            dfs(depth + 1, newArr);
        }
    }

    static void writeLine(int[][] board, int[] line, int dir, int index) {
        for (int i = 0; i < n; i++) {
            if (dir == 0) {
                board[index][i] = line[i];
            } else if (dir == 1) {
                board[index][n - i - 1] = line[i];
            } else if (dir == 2) {
                board[i][index] = line[i];
            } else {
                board[n - i - 1][index] = line[i];
            }
        }
    }

    static void playLine(int[] line) {
        //line에 있는 수를 queue에 저장하고
        //빼면서 다음 꺼랑 같으면 *2로 저장
        //다음꺼랑 같지 않으면 그대로 저장
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (line[i] != 0) {
                queue.add(line[i]);
            }
        }
        Arrays.fill(line, 0);
        int index = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if (queue.isEmpty() || queue.peek() != cur) {
                line[index++] = cur;
            } else {
                queue.poll();
                line[index++] = cur * 2;
            }
        }

    }
    static int[] getLine(int[][] board, int dir, int index) {
        int[] newLine = new int[n];
        for (int i = 0; i < n; i++) {
            if (dir == 0) {
                newLine[i] = board[index][i];
            } else if (dir == 1) {
                newLine[i] = board[index][n - i - 1];
            } else if (dir == 2) {
                newLine[i] = board[i][index];
            } else {
                newLine[i] = board[n - i - 1][index];
            }
        }
        return newLine;
    }

    static int getMax(int[][] arr) {
        int max = arr[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max= Math.max(max, arr[i][j]);
            }
        }
        return max;
    }
}
