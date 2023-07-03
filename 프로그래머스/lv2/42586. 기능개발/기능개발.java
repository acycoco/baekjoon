import java.util.*;
class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        //남은 날
        int[] remainingDays = new int[progresses.length];
        Stack<Integer> stack = new Stack<>();
        int count = 1;
        
        for (int i = 0; i < progresses.length; i++){
            remainingDays[i] = (100 - progresses[i]) / speeds[i];
            remainingDays[i] = (100 - progresses[i]) % speeds[i] == 0 ? remainingDays[i]: remainingDays[i] + 1;  
            
             if (stack.isEmpty()){
                stack.push(remainingDays[i]);
             }
            else {
                if (stack.peek() < remainingDays[i]){
                    stack.pop();
                    answer.add(count);
                    stack.push(remainingDays[i]);
                    count = 1;
                    
                 } else{
                    
                        count++;
                    
                } 
            }

        }
          if (!stack.isEmpty()){
                answer.add(count);
            }

        
        
        return answer;
    }
}