import java.util.*;
class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp = new Set[9];
        for(int i = 1; i < 9; i ++) {
            dp[i] = new HashSet<>();
            dp[i].add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }
        
        int answer = -1;
        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < i; j++) {
                for(int x: dp[j]) {
                    for(int y: dp[i - j]) {
                        dp[i].add(x + y);
                        dp[i].add(x - y);
                        dp[i].add(x * y);
                        if(y != 0) {
                            dp[i].add(x / y);
                        }
                    }
                }
            }
            if(dp[i].contains(number)) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}