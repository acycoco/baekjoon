class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer= new int[N];
        
        int[] successCnt = new int[N + 2];
        int[] failCnt = new int[N + 2];
        double[] failPer = new double[N + 2];
        
        //성공횟수와 실패횟수세기
        for(int i=0; i<stages.length; i++){
            for(int j=1; j <= stages[i]; j++){
                successCnt[j] += 1;
            }
            failCnt[stages[i]] += 1;
        }
        
        //실패율 구하기
        for(int i=1; i<failPer.length - 1; i++){
       
            if (successCnt[i] == 0) failPer[i] = 0;
            else {
                failPer[i] = 1.0 * failCnt[i] / successCnt[i];
            }
            
        }
        
        //실패율 높은 순서부터
        for (int i = 0; i < answer.length; i++){
            int maxIdx = 1;
            for (int j = 2; j< failPer.length - 1; j++){
            
                if (failPer[maxIdx] < failPer[j]){
                    maxIdx = j;
                }
             }
             answer[i] = maxIdx;
             failPer[maxIdx] = -1;
        }
        
       
        return answer;
    }
}