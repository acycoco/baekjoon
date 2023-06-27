import java.util.*;
class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[] next = new int[]{0, 0};//합, 지금 계산할 값
        queue.offer(next);//처음 값 넣기
        
        while (!queue.isEmpty()){
           next = queue.poll();// 빼면서 계산
           
            if (next[1] == numbers.length){ //numbers 배열을 다 순회하면
                if (next[0] == target) { //합이 target이랑 같으면 
                answer++;
                }
                continue;
            }
            
             
   
            queue.offer(new int[]{(next[0] + numbers[next[1]]), (next[1] + 1)}); //더하기
            queue.offer(new int[]{(next[0] - numbers[next[1]]), (next[1] + 1)}); //빼기
            
            
        }
        return answer;
    }
}