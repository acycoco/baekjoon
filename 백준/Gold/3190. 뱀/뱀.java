import java.io.*;
import java.util.*;

public class Main {
    static int N, K, L;
    static int[][] board;
    static Map<Integer, Character> directions = new HashMap<>();
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        
        board = new int[N + 1][N + 1];
        
        // 사과 위치 저장
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1; // 사과는 1로 표시
        }
        
        // 방향 변환 정보 저장
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            directions.put(time, direction);
        }
        
        System.out.println(simulate());
    }
    
    public static int simulate() {
        int time = 0;
        int direction = 0; // 처음에는 오른쪽을 향함
        
        // 뱀의 위치를 저장하는 덱 (머리가 앞, 꼬리가 뒤)
        Deque<int[]> snake = new ArrayDeque<>();
        snake.offer(new int[]{1, 1});
        board[1][1] = 2; // 뱀의 위치는 2로 표시
        
        while (true) {
            time++;
            
            // 다음 위치 계산
            int[] head = snake.peekFirst();
            int nx = head[0] + dx[direction];
            int ny = head[1] + dy[direction];
            
            // 벽이나 자기 자신과 부딪히는지 확인
            if (nx < 1 || nx > N || ny < 1 || ny > N || board[nx][ny] == 2) {
                break;
            }
            
            // 사과가 없다면 꼬리 제거
            if (board[nx][ny] != 1) {
                int[] tail = snake.pollLast();
                board[tail[0]][tail[1]] = 0;
            }
            
            // 새로운 머리 위치 추가
            snake.offerFirst(new int[]{nx, ny});
            board[nx][ny] = 2;
            
            // 방향 전환 확인
            if (directions.containsKey(time)) {
                if (directions.get(time) == 'L') {
                    direction = (direction + 3) % 4;
                } else {
                    direction = (direction + 1) % 4;
                }
            }
        }
        
        return time;
    }
}