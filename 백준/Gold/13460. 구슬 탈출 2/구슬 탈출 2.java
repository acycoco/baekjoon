import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    
    static class State {
        int rx, ry, bx, by, count;
        
        public State(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
    
    public static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];
        
        queue.offer(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            if (current.count > 10) return -1;
            
            if (board[current.bx][current.by] == 'O') continue;
            
            if (board[current.rx][current.ry] == 'O') return current.count;
            
            for (int i = 0; i < 4; i++) {
                int[] result = move(current.rx, current.ry, current.bx, current.by, i);
                
                int nrx = result[0], nry = result[1], nbx = result[2], nby = result[3];
                
                if (!visited[nrx][nry][nbx][nby]) {
                    queue.offer(new State(nrx, nry, nbx, nby, current.count + 1));
                    visited[nrx][nry][nbx][nby] = true;
                }
            }
        }
        
        return -1;
    }
    
    public static int[] move(int rx, int ry, int bx, int by, int direction) {
        int[] result = new int[4];
        boolean redFirst = false;
        
        // 방향에 따라 먼저 움직일 구슬 결정
        if ((direction == 0 && rx < bx) || 
            (direction == 1 && rx > bx) || 
            (direction == 2 && ry < by) || 
            (direction == 3 && ry > by)) {
            redFirst = true;
        }
        
        // 첫 번째 구슬 이동
        if (redFirst) {
            int[] red = moveMarble(rx, ry, direction);
            rx = red[0];
            ry = red[1];
            
            int[] blue = moveMarble(bx, by, direction);
            bx = blue[0];
            by = blue[1];
        } else {
            int[] blue = moveMarble(bx, by, direction);
            bx = blue[0];
            by = blue[1];
            
            int[] red = moveMarble(rx, ry, direction);
            rx = red[0];
            ry = red[1];
        }
        
        // 구슬이 같은 위치에 있다면 위치 조정
        if (rx == bx && ry == by && board[rx][ry] != 'O') {
            switch(direction) {
                case 0: // 상
                    if (redFirst) bx++;
                    else rx++;
                    break;
                case 1: // 하
                    if (redFirst) bx--;
                    else rx--;
                    break;
                case 2: // 좌
                    if (redFirst) by++;
                    else ry++;
                    break;
                case 3: // 우
                    if (redFirst) by--;
                    else ry--;
                    break;
            }
        }
        
        result[0] = rx;
        result[1] = ry;
        result[2] = bx;
        result[3] = by;
        
        return result;
    }
    
    public static int[] moveMarble(int x, int y, int direction) {
        while (true) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            
            if (board[nx][ny] == '#') break;
            
            if (board[nx][ny] == 'O') {
                x = nx;
                y = ny;
                break;
            }
            
            x = nx;
            y = ny;
        }
        
        return new int[]{x, y};
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new char[N][M];
        
        int rx = 0, ry = 0, bx = 0, by = 0;
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                    board[i][j] = '.';
                }
                
                if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                    board[i][j] = '.';
                }
            }
        }
        
        System.out.println(bfs(rx, ry, bx, by));
    }
}