class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int yCnt = 0;
        int pCnt = 0;
        
        for(char x: s.toCharArray()){
            if (x == 'y' || x == 'Y') yCnt++;
            else if  (x == 'p' || x == 'P') pCnt++;
        }
        
        if(pCnt != yCnt) return false;

        return answer;
    }
}