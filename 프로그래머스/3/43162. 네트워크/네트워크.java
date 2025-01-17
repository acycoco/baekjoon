import java.util.*;
class Solution {
    
      public int solution(int n, int[][] computers){
        boolean[] visited = new boolean[n];
        int answer = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            dfs(i, computers, visited);
            answer++;
        }
          return answer;

     }
    
    public void dfs(int index, int[][] computers, boolean[] visited) {
        visited[index] = true;
        for(int i = 0; i < computers.length; i++) {
            if(i == index) continue;
            if(!visited[i] && computers[index][i] == 1) {
                dfs(i, computers, visited);
            }
        }
    }
}
