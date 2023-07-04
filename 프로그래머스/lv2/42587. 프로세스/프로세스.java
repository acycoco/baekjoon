import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < priorities.length; i++){
                queue.offer(new int[]{priorities[i], i});
            }
                
                while (!queue.isEmpty()){
                    ///최댓값 찾기
                    int max = queue.peek()[0];
                    for (int[] x: queue){
                        if (max < x[0]) max = x[0];
                    }
                    //peek에 있는 값이 최댓값이랑 같으면
                    if (queue.peek()[0] == max) {
                        answer++;
           
                        if (queue.poll()[1] == location)
                            return answer;
                        continue;
                    }
                    queue.offer(queue.poll());
                }
        
        return answer;
    }
}