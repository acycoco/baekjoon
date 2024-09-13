import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        //가장 먼저 들어온 애 부터
        //가장 짧은애부터?        3 + (3 - 2) + 6 + (3 + 6) - 1 + 9
        //가장 먼저 들어온 애부터? 3 + (3 - 1) + 9 + (3 + 9) - 2 + 6
        
        //request가 1에 들어온 경우 1 + 3 + (4)
        int answer = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>();
        PriorityQueue<Job> requestPq = new PriorityQueue<>(
            (q1, q2) -> {
            if(q1.request == q2.request) {
                return q1.time - q2.time;
            }
            return q1.request - q2.request;
        }); 
        for(int i = 0; i < jobs.length; i++) {
            Job job = new Job(jobs[i][0], jobs[i][1]);
            
            pq.offer(job);
            requestPq.offer(job);
        }
        
        int curTime = 0;
        while(!pq.isEmpty()) {
            Job curJob;
            if(curTime >= pq.peek().request) {
                curJob = pq.poll();
                requestPq.remove(curJob);
                answer += (curTime - curJob.request);
            } else {
                curJob = requestPq.poll();
                pq.remove(curJob);
                answer += curJob.time;
            }
            curTime += curJob.time;
            answer += curJob.time;

            
        }
        return answer / jobs.length;
    }
    
    static class Job implements Comparable<Job> {
        int request;
        int time;
        
        public Job(int request, int time) {
            this.request = request;
            this.time = time;
        }
        
        @Override
        public int compareTo(Job o) {
            if(this.time == o.time) {
                return this.request - o.request;
            }
            return this.time - o.time;
        }
    }
}