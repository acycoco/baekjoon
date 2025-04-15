import java.util.*;
class Solution {
    // int dx = {1, 0};
    // int dy = {0, 1};
    
    public int solution(int m, int n, int[][] puddles) {
        
        //m이랑 n이랑 반대로 생각해야됨
        //오른쪽과 아래로 움직여 최단경로 , 물이 잠긴 지역
        int[][] dp = new int[n][m];
        
        
        for(int[] pud: puddles) {
            int x = pud[0] - 1;
            int y = pud[1] - 1;
            
            dp[y][x] = -1; 
        }
        
        for(int i = 0; i < n; i++) {
            if(dp[i][0] == -1) break;
            dp[i][0] = 1;
        } 
        
        for(int i = 0; i < m; i++) {
            if(dp[0][i] == -1) break;
            dp[0][i] = 1;
        }
        
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(dp[i][j] == -1) continue;
                
                dp[i][j] += dp[i - 1][j] == -1 ? 0 : dp[i - 1][j];
                dp[i][j] += dp[i][j - 1] == -1 ? 0 : dp[i][j - 1];
                dp[i][j] %= 1_000_000_007;
            }
        }
        return dp[n - 1][m - 1];
        
        
//         int[][] dp = new int[n + 1][m + 1];
// //         for(int i = 0; i <= n; i++) {
// //             dp[0][i] = 1; 
// //         }
        
// //         for(int i = 0; i <= m; i++) {
// //             dp[i][0] = 1; 
// //         }
        
//         for(int i = 0; i < puddles.length; i++) {
//             dp[puddles[i][1]][puddles[i][0]] = -1;
//         }

//         dp[1][1] = 1;
//         for(int i = 1; i <= n; i++) {
//             for(int j = 1; j <= m; j++) {
//                 if(dp[i][j] == -1) continue;
//                 dp[i][j] += (dp[i - 1][j] == -1 ? 0 : dp[i - 1][j]);
//                 dp[i][j] += (dp[i][j - 1] == -1 ? 0 : dp[i][j - 1]);
//                 dp[i][j] %= 1_000_000_007;
//             }
//         }

        // return dp[n][m];
        
        
        
        
//         int[][] dp = new int[m + 1][n + 1];
       
//         for(int[] puddle: puddles) {
//             dp[puddle[0]][puddle[1]] = -1;
//         }
//         dp[1][1] = 1;
//         for(int i = 1; i <= m; i++) {
//             for(int j = 1; j <= n; j++) {
//                 if(dp[i][j] == -1) continue;
//                 if(dp[i - 1][j] != -1 && i > 1) {
//                     dp[i][j] += dp[i - 1][j];
//                     dp[i][j] %= MOD;
//                 } 
//                 if(dp[i][j - 1] != -1 && j > 1) {
//                     dp[i][j] += dp[i][j - 1];
//                     dp[i][j] %= MOD;
//                 } 
//             }
//         }
        
        
        
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
        // return dp[m][n];
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