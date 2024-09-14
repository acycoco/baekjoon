import java.util.*;
class Solution {
    public static class Price {
        int time;
        int number;
        public Price(int time, int number) {
            this.time = time;
            this.number = number;
        }
    }
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Arrays.fill(answer, -1);
        Stack<Price> st = new Stack<>();
        for(int time = 0; time < prices.length; time++) {
            if(!st.isEmpty() && st.peek().number > prices[time]) {
                while(!st.isEmpty() && st.peek().number > prices[time]) {
                    Price falledPrice = st.pop();
                    answer[falledPrice.time] = time - falledPrice.time;
                }
            }
            st.push(new Price(time, prices[time])); 
        }
        
        for(int i = 0; i < prices.length; i++) {
            if(answer[i] == -1) {
                answer[i] = prices.length - i - 1;
            }
        }
        return answer;
    }
}