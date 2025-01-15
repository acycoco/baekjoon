import java.util.*;
class Solution {
    int maxScoreDifference = -1;
    int[] answer = new int[11];
    public int[] solution(int n, int[] info) {
        dfs(0, n, new int[11], info, 0, 0, 0);
        if (maxScoreDifference == -1) {
            return new int[]{-1};
        }
        
        return answer;
    }
    
    public void dfs(int index, int n, int[] lion, int[] info, int sumScore, int nowCount, int apeachScore) {
        if (index == 10) { //10개의 점수를 다 정한 경우
            int remainingCount = (n - nowCount);
            if(remainingCount > 0) { // n개를 다 선택하지 못한 경우
                lion[10] += remainingCount; //마지막 화살 몰아줌
            }
            if (apeachScore < sumScore && sumScore  - apeachScore >= maxScoreDifference ) {
                if(maxScoreDifference < sumScore - apeachScore 
                   || isAnwerAtSameScore(answer, lion)) {
                    maxScoreDifference = sumScore - apeachScore;
                    answer = lion.clone();
                } 
            }
            lion[10] = 0;
            return;
        }
   
        //해당 index를 lion이 선택하는 경우
        if(nowCount + info[index] + 1 <= n) { //어피치보다 1개를 더 쏠 여력이 있는가
            lion[index] = info[index] + 1;
        dfs(index + 1, n, lion, info, sumScore + 10 - index, nowCount + info[index] + 1, apeachScore);
            lion[index] = 0;
        }
        
        //해당 index를 lion은 선택하지 않음
        if(info[index] > 0) { //어피치가 0개를 쏘지 않으면 어피치가 해당 점수를 얻음
            apeachScore += (10 - index);
        }
        dfs(index + 1, n, lion, info, sumScore, nowCount, apeachScore);
    }
    
    public boolean isAnwerAtSameScore(int[] answer, int[] lion) {
        for(int i = 10; i >= 0; i--) {
           if(lion[i] != answer[i]){
              return lion[i] > answer[i];
           }
        }
        
        return false;
    }
}