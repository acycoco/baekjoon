import java.util.*;
class Solution { 
    private int[] dx = new int[]{-1 , 1, 0, 0};
    private int[] dy = new int[]{0 , 0, -1, 1};
    private int m;
    private int n;
    
    public int solution(int[][] maps) {
        int answer = -1;
        m = maps.length;
        n = maps[0].length;
        
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); //좌표, 여태까지 온 거리
        visited[0][0] = true;
        
        
        while(!queue.isEmpty()){
            int[] next = queue.poll();
            if (next[0] == m - 1 && next[1] == n - 1){//마지막에 도달했으면
                answer = next[2];
                break;
            }
            else{
                for(int i = 0; i < 4; i++){ //동서 남북으로
                    int x = next[0] + dx[i];
                    int y = next[1] + dy[i];
                    if(inside(x, y) && !visited[x][y] && maps[x][y] == 1){
                        
                        queue.offer(new int[]{x, y, next[2] + 1});
                        visited[x][y] = true;
                        
                    }
                }
            }
            
        }

        return answer;
    }
    public boolean inside(int x, int y){
        return -1 < x && x < m && -1 < y && y < n;
    }

    
}