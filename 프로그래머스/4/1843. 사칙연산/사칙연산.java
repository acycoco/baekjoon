import java.util.*;
class Solution {
    public int solution(String arr[]) {
        int n = arr.length / 2 + 1;
        int[][] max = new int[n][n]; int[][] min = new int[n][n];
        for(int i = 0; i < n; i++) {
            max[i][i] = Integer.parseInt(arr[i * 2]);
            min[i][i] = max[i][i];
        }
        
        for(int len = 1; len <= n - 1; len++) {
            for(int i = 0; i < n - len; i++) {
                int j = i + len;
                  max[i][j] = Integer.MIN_VALUE;
                min[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                      String oper = arr[2 * k + 1];
                    if(oper.equals("+")) {
                        min[i][j] = Math.min(min[i][j], min[i][k] + min[k + 1][j]);
                        max[i][j] = Math.max(max[i][j], max[i][k] + max[k + 1][j]);
                    }
                    else if(oper.equals("-")) {
                        min[i][j] = Math.min(min[i][j], min[i][k] - max[k + 1][j]);
                        max[i][j] = Math.max(max[i][j], max[i][k] - min[k + 1][j]);
                    }
                }
            }
        }
        
         return max[0][n - 1];
        
        
        
//         int n = arr.length / 2 + 1;
//         int[][] dp_max = new int[n][n];
//         int[][] dp_min = new int[n][n];
//         int[] nums = new int[n];
//         char[] ops = new char[n - 1];
        
//         for(int i = 0; i < arr.length; i++) {
//             if(i % 2== 0) nums[i / 2] = Integer.parseInt(arr[i]);
//             else ops[i / 2] = arr[i].charAt(0);
//         }
        
//         for(int i = 0; i < n; i++) {
//             dp_max[i][i] = nums[i];
//             dp_min[i][i] = nums[i];
//         }
        
        
//         for(int len = 1; len < n; len++) {
//             for(int i = 0; i < n - len; i++) {
//                 int j = i + len;
//                 dp_max[i][j] = Integer.MIN_VALUE;
//                 dp_min[i][j] = Integer.MAX_VALUE;
                
//                 for(int k = i; k < j; k++) {
//                     if(ops[k] == '+') {
//                         dp_max[i][j] = Math.max(dp_max[i][j], dp_max[i][k] + dp_max[k + 1][j]);
//                         dp_min[i][j] = Math.min(dp_min[i][j], dp_min[i][k] + dp_min[k + 1][j]);
//                     } else {
//                         dp_max[i][j] = Math.max(dp_max[i][j], dp_max[i][k] - dp_min[k + 1][j]);
//                         dp_min[i][j] = Math.min(dp_min[i][j], dp_min[i][k] - dp_max[k + 1][j]);
//                     }
//                 }
//             }
           
//         }
//          return dp_max[0][n - 1];
        
        
        
        
        
        
        
        
        
        
        
        
        
//         int arrLength = arr.length;
//         int n = (arrLength + 1) / 2;
//         int[][] min = new int[n][n];
//         int[][] max = new int[n][n];
        
//         for(int i = 0; i < n; i ++) {
//             min[i][i] = Integer.parseInt(arr[2 * i]);
//             max[i][i] = Integer.parseInt(arr[2 * i]);
//         }
        
//         for(int len = 1; len <= n - 1; len++) {
//             for(int i = 0; i < n - len; i++) {
//                 int j = i + len;
//                 max[i][j] = Integer.MIN_VALUE;
//                 min[i][j] = Integer.MAX_VALUE;
                
//                 for(int k = i; k < j; k++) {
//                     String oper = arr[2 * k + 1];
//                     if(oper.equals("+")) {
//                         min[i][j] = Math.min(min[i][j], min[i][k] + min[k + 1][j]);
//                         max[i][j] = Math.max(max[i][j], max[i][k] + max[k + 1][j]);
//                     }
//                     else if(oper.equals("-")) {
//                         min[i][j] = Math.min(min[i][j], min[i][k] - max[k + 1][j]);
//                         max[i][j] = Math.max(max[i][j], max[i][k] - min[k + 1][j]);
//                     }
//                 }
//             }
//         }
        // return max[0][n - 1];
    }
}