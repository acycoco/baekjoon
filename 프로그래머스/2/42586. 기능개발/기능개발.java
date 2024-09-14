import java.util.*;
class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        //peek보다 작으면 offer가 아니라 개수 add
        //크면 있는 거 다 add 개수 -> 배포 개수 -> 그뒤로 푸시
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < progresses.length; i++) {
            int neededDay = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            if (queue.isEmpty()|| queue.peek() >= neededDay) {
                queue.offer(neededDay);
            } else {
                answer.add(queue.size());
                queue.clear();
                queue.offer(neededDay);
            }
        }
        answer.add(queue.size());
        return answer;
    }
}