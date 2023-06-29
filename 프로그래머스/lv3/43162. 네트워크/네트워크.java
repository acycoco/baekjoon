import java.util.LinkedList;
import java.util.Queue;
class Solution {
      public int solution(int n, int[][] computers){
        int answer = 0;

        boolean[] visited = new boolean[n];
        //모든 컴퓨터 순회
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                //DFS 또는 BFS
                //아직 방문하지 않은 i부터 탐색
                network(i, n, computers, visited);
                //네트워크 하나 완성
                answer++;
            }
        }
        return answer;
    }
    public void network(
            //몇번 컴퓨터부터 확인 예정인지
            int computer,
            //컴퓨터 개수
            int n,
            // 컴퓨터 연결 정보
            int[][] computers,
            //컴퓨터 방문정보
            boolean[] visited)
    {
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.offer(computer);
        while(!toVisit.isEmpty()){
            int next = toVisit.poll();

            for (int i = 0; i < n; i++) {
                if (computers[next][i] == 1 && !visited[i]) {
                    toVisit.offer(i);
                    visited[i] = true; //연산횟수를 줄이기 위해서 다음에 방문할 것을 방문했다고 표기
                }
            }
        }

    }
    }
