class Solution {
    public long solution(long price, long money, int count) {
        long answer = money;
        for (int i=1; i <= count; i++){
            answer -= (price * i);
        }
        
        if (answer >= 0) return 0;

        return -answer;
    }
}