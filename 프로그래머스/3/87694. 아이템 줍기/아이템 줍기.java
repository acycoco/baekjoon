import java.util.*;
class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] board = new int[51 * 2][51 * 2];
         computeBorderLine(rectangle, board);
        
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2, board);
    }
    
    public int bfs(int startX, int startY, int itemX, int itemY, int[][] board) {
        int[] dx = new int[]{-1, 1, 0 ,0}; 
        int[] dy = new int[]{0, 0, -1, 1};
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0));
        board[startX][startY] = 0;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if(cur.x == itemX && cur.y == itemY) {
                return cur.count / 2;
            }
            for(int i = 0; i < dx.length; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if(Point.isValid(nextX, nextY) && board[nextX][nextY] == 1) {
                    queue.offer(new Point(nextX, nextY, cur.count + 1));
                    board[nextX][nextY] = 0;
                
                }
            }
        }
        return 0;
    }
    
    public void computeBorderLine(int[][] rects, int[][] board) {
          for(int[] rect: rects) {
              int startX = rect[0] * 2;
              int startY = rect[1] * 2;
              int endX = rect[2] * 2;
              int endY = rect[3] * 2;
              
              for(int x = startX; x <= endX; x++) {
                  board[x][startY] = 1;
                  board[x][endY] = 1;
              }
              
              for(int y = startY; y <= endY; y++) {
                  board[startX][y] = 1;
                  board[endX][y] = 1;
              }
              
          
              
          }
        
         for(int[] rect: rects) {
              int startX = rect[0] * 2;
              int startY = rect[1] * 2;
              int endX = rect[2] * 2;
              int endY = rect[3] * 2;
              
              
              for(int x = startX + 1; x < endX; x++) {
                  for(int y = startY + 1; y < endY; y++) {
                      board[x][y] = 0;
                  }
              }
              
          }
    }
    
    public static class Point {
        int x;
        int y;
        int count;
        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
        
        public static boolean isValid(int x, int y) {
            return x >= 0 && y >= 0 && x <= 50 * 2 && y <= 50 * 2;
        }
    }
}