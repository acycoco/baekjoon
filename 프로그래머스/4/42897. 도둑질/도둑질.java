import java.util.*;
class Solution {
    public int solution(int[] money) {
        
        int n = money.length;
        int[] dp = new int[n];
        
        dp[0] = money[0];
        dp[1] = money[0];
        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
          int[] dp2 = new int[n];
        
        dp2[0] = 0;
        dp2[1] = money[1];
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }
        return Math.max(dp[n - 2], dp2[n - 1]);
        
        
//         //인접한 집을 털지 않으면서 최대 돈
//         //사이클dp써서 인접한 거 안 털면서 최댓값?
//         //아님 배낭 문제? 거기다가 인접한 거 털면 안된다는 조건이 붙은 것?
//         int n = money.length;
//         int[] dp = new int[n];
//         dp[0] = 0;
//         dp[1] = money[1];
        
//         for(int i = 2; i < n; i++) {
//             dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
//         }
        
//           int[] dp2 = new int[n];
//         dp2[0] = money[0];
//         dp2[1] = money[0];
        
//         for(int i = 2; i < n; i++) {
//             dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
//         }
        
//         return Math.max(dp[n - 1], dp2[n - 2]);
        
        
        
//         int n = money.length;
       
//         int[] dp1 = new int[n];
//         //첫번째 선택 x
//         dp1[0] = 0;
//         dp1[1] = money[1];
        
//         for(int i = 2; i < n; i++) {
//             dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
//         }
//         int[] dp2 = new int[n];
//         //첫번째 선택함 o
//         dp2[0] = money[0];
//         dp2[1] = money[0];
//          for(int i = 2; i < n; i++) {
//             dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
//         }
        
//         return Math.max(dp1[n - 1], dp2[n - 2]);
        
//         int n = money.length;
//         int[][] dp = new int[n][2];
//         dp[0][0] = 0;
//         dp[0][1] = money[0];
//         dp[1][0] = dp[0][1];
//         dp[1][1] = money[1];
        
//         for(int i = 2; i < n; i++) {
//             dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
//             dp[i][1] = Math.max(dp[i - 1][0], dp[i - 2][1] + money[i]);
//         }
//         return Math.max(dp[n - 1][0], dp[n - 1][1]);
        
        
//         int[] dp = new int[n];
        
//         //첫번째 집을 안털고 & 마지막 집을 털었을 경우
//         dp[1] = money[1];
//         for(int i = 2; i < n; i++) {
//             dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
//         }
        
//         int max = dp[n - 1];
        
//         //첫번째 집을 털고 & 마지막 집을 안털었을 경우
//         dp = new int[n];
//         dp[0] = money[0];
//         dp[1] = money[0];
//         for(int i = 2; i < n - 1; i++) {
//             dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
//         }
   
//         max = Math.max(max, dp[n - 2]);
        
//         return max;
    }
    
    
    //어떤 집을 털었는지
    //완전 탐색 -> n! = 1000000!
    //어느 집을 선택했는지를 알고 있어야됨
    //해당 집을 터는 경우, 안 터는 경우 -> 2가지로
}