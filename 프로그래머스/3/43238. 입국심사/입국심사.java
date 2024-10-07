import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        int minT = Integer.MAX_VALUE;
        int maxT = Integer.MIN_VALUE;
        for(int i = 0; i < times.length; i++) {
            minT = Math.min(times[i], minT);
            maxT = Math.max(times[i], maxT);
        }
        int minN;
        int maxN;
        maxN = n / times.length;
        minN = maxN;
        if(n % times.length != 0) {
            minN = maxN + 1;
        } 
        long prevMax = maxT * maxN;//30  20  10
        long prevMin = minT * minN;//21  28  35
        long curMax = prevMax;
        long curMin = prevMin;
        while(Math.abs(prevMax - prevMin) >= Math.abs(curMax - curMin)) {
            // System.out.println(prevMax +" "+ prevMin + " " + curMax + " "+curMin);
            // maxN -= 1;
            // minN += 1;
            prevMax = curMax;
            prevMin = curMin;
            curMax -= maxT; //10
            curMin += minT; //35
            // System.out.println(prevMax +" "+ prevMin + " " + curMax + " "+curMin);
        }
        long answer = Math.max(prevMax, prevMin);
        return answer;
    }
    //3  3     21  30      9    min값보다 차이가 클경우
    //4  2     28  20      -8    작아졌다가 커지는 게 정답(둘 중에는 최댓값)
    //5  1     35  10      -25
    
    
    // 6   3,  5, 10
    // 2  2  2    6  10  20    14
    // 3  2  1    18  10 10    8
    // 4  1  1    24  3  5    
}