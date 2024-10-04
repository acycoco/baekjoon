import java.util.*;
class Solution {
    int[] dx = {1, 0};
    int[] dy = {0, 1};
    int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];
      
        for(int[] puddle: puddles) {
            dp[puddle[0]][puddle[1]] = -1;
        }
        dp[1][1] = 1;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(dp[i][j] == -1) continue;
                if(dp[i - 1][j] != -1 && i > 1) {
                    dp[i][j] += dp[i - 1][j];
                    dp[i][j] %= MOD;
                } 
                if(dp[i][j - 1] != -1 && j > 1) {
                    dp[i][j] += dp[i][j - 1];
                    dp[i][j] %= MOD;
                } 
            }
        }
//         int answer = 0;
//         Queue<Node> queue = new LinkedList<>();
//         boolean[][] visited = new boolean[m + 1][n + 1];
        
//         for(int[] puddle: puddles) {
//             visited[puddle[0]][puddle[1]] = true;
//         }
//         queue.offer(new Node(0, 1, 1));
//         visited[1][1] = true;
    
//         int minDistance = Integer.MAX_VALUE;
//         boolean first = true;
//         while(!queue.isEmpty()) {
//             Node cur = queue.poll();
//             if(cur.x == m && cur.y == n) {
//                 if(first) {
//                     minDistance = cur.distance;
//                     answer++;
//                     first = false;
//                 }
//                 if(cur.distance == minDistance) {
//                     answer++;
//                 }
//             }
//             for(int i = 0; i < 2; i++) {
//                 int nextX = cur.x + dx[i];
//                 int nextY = cur.y + dy[i];
//                 if(nextX <= m && nextX >= 1 && nextY <= n && nextY >= 1 && !visited[nextX][nextY]) {
//                     queue.offer(new Node(cur.distance + 1, nextX, nextY));
//                     visited[nextX][nextY] = true;
//                 }
//             }
//         }
        
//         System.out.println(minDistance);
        return dp[m][n];
    }
    
//     static class Node {
//         int distance;
//         int x;
//         int y;
        
//         public Node(int distance, int x, int y) {
//             this.distance = distance;
//             this.x = x;
//             this.y = y;
//         }
//     }
}