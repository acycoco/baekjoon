import java.util.*;
class Solution {
    boolean[] used;
    int count;
    public int solution(int k, int[][] dungeons) {
        //최소 필요 피로도가 있어야 탐험
        //소모 피로도는 탐험 후 차감
        //하루에 한번씩 탐험 던전
        //최대한 많은 던전
        used = new boolean[dungeons.length];
        
        comb(k, 0, 0, dungeons);
        return count;
    }
    public void comb(int curK, int visitedCount, int depth, int[][] dungeons) {
        
        if(depth == dungeons.length) {
            count = Math.max(count, visitedCount);
            return;
        }
        
        boolean isGG = false;
        for(int i = 0; i < dungeons.length; i++) {
            if(used[i] || curK - dungeons[i][0] < 0) continue;
            isGG = true;
            used[i] = true;
            comb(curK - dungeons[i][1], visitedCount + 1, depth + 1, dungeons);
            used[i] = false;
        }
        if(!isGG) {
            count = Math.max(count, visitedCount);
            return;
        }
    }
    
    
    
    
    
    
    
    
    // public void visit(boolean[] visited, int[][] dungeons, int curK, int count) {
    //     maxCount = Math.max(count, maxCount);
    //    for(int i = 0; i < dungeons.length; i++) {
    //        if(!visited[i] && curK >= dungeons[i][0]) {
    //            visited[i] = true;
    //            visit(visited, dungeons, curK - dungeons[i][1], count + 1);
    //            visited[i] = false;
    //        }
    //    }
    // }
//     public void visitDungeons(int[][] dungeons, boolean[] visited, int currentK, int count) {
//         // 최대 방문 던전 수 갱신
//         maxCount = Math.max(maxCount, count);
        
//         for (int i = 0; i < dungeons.length; i++) {
//             if (visited[i]) continue;
            
//             // 현재 남은 피로도가 최소 필요 피로도보다 적으면 이 던전은 패스
//             if (currentK < dungeons[i][0]) continue;
            
//             visited[i] = true;
//             visitDungeons(dungeons, visited, currentK - dungeons[i][1], count + 1);
//             visited[i] = false; // 백트래킹
//         }
        
//     }
}