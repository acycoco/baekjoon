import java.util.*;
class Solution {
    public int maxCount = 0;
    public int solution(int k, int[][] dungeons) {
        visitDungeons(dungeons, new boolean[dungeons.length], k, 0);
        return maxCount;
    }
    
    public void visitDungeons(int[][] dungeons, boolean[] visited, int currentK, int count) {
        // 최대 방문 던전 수 갱신
        maxCount = Math.max(maxCount, count);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            
            // 현재 남은 피로도가 최소 필요 피로도보다 적으면 이 던전은 패스
            if (currentK < dungeons[i][0]) continue;
            
            visited[i] = true;
            visitDungeons(dungeons, visited, currentK - dungeons[i][1], count + 1);
            visited[i] = false; // 백트래킹
        }
        
    }
}