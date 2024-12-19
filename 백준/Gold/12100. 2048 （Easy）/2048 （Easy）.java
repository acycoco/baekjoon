
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int answer = -1;

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

    public static void dfs(int depth, int[][] board) {
        if (depth == 5) {
            answer = Math.max(answer, getMaxNumber(board));
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] newBoard = new int[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(board[i], 0, newBoard[i], 0, n);
            }
            for (int index = 0; index < n; index++) {
                int[] line = getLine(newBoard, dir, index);
                mergeLine(line);
                copyLine(line, newBoard, dir, index);
            }
            dfs(depth + 1, newBoard);
        }
    }


    public static void copyLine(int[] line, int[][] board, int dir, int index) {
        for (int i = 0; i < n; i++) {
            if (dir == 0) board[index][i] = line[i];//상
            else if (dir == 1) board[index][n - i - 1] = line[i];//하
            else if (dir == 2) board[i][index] = line[i];//좌
            else board[n - i - 1][index] = line[i];
        }
    }

    public static void mergeLine(int[] line) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (line[i] != 0) {
                queue.offer(line[i]);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int target = queue.poll();
            if (!queue.isEmpty() && queue.peek() == target) {
                line[index++] = target * 2;
                queue.poll();
            } else {
                line[index++] = target;
            }
        }
        for (int i = index; i < n; i++) {
            line[i] = 0;
        }
    }

    public static int[] getLine(int[][] board, int dir, int index) {
        int[] newLine = new int[n];
        for (int i = 0; i < n; i++) {
            if (dir == 0) newLine[i] = board[index][i];//상
            else if (dir == 1) newLine[i] = board[index][n - i - 1];//하
            else if (dir == 2) newLine[i] = board[i][index];//하
            else newLine[i] = board[n - i - 1][index];
        }
        return newLine;
    }

    public static int getMaxNumber(int[][] board) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(board[i][j], max);
            }
        }
        return max;
    }


}
