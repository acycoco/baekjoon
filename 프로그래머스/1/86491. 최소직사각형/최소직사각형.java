import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxH = 0; //둘 중 더 큰 원소의 최댓값
        int maxW = 0; //둘 중 더 작은 원소의 최댓값
        for(int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] > sizes[i][1]) {
                maxH = Math.max(sizes[i][0], maxH);
                maxW = Math.max(sizes[i][1], maxW);
            } else {
                 maxH = Math.max(sizes[i][1], maxH);
                maxW = Math.max(sizes[i][0], maxW);
            }
        }
        return maxW * maxH;
    }
    
  
}