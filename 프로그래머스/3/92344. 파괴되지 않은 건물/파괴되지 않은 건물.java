import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        //적의 공격을 받으면 내구도가 감소하고, 내구도가 0이하 되면 파괴됨
        //아군은 회복 스킬을
        //음수까지 내려갈 수 있고
        //파괴 되지 않는 건물의 수를 구해야 됨 1이상인 건물
        
        //skill type 1이면 공격 0이면 회복
        
        //우선 모든 skill을 다 회복을 쓴다고 해도 125,000,000이 최댓값? (500 x 25만) = int로 가능
        //n ^ 2 = 10 ^ 6 * 25만 = 25 * 10 ^ 10 -> 완전 탐색 불가능
        //
        
        int N = board.length;
        int M = board[0].length;
        int[][] prefix = new int[N + 1][M + 1];
        
        for(int[] s:skill) {
            int type = s[0], r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
            int effect = type == 1? -degree: degree;
            prefix[r1][c1] += effect;
            prefix[r1][c2 + 1] -= effect;
            prefix[r2 + 1][c1] -= effect;
            prefix[r2 + 1][c2 + 1] += effect;
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 1; j < M; j++) {
                prefix[i][j] += prefix[i][j - 1];
            }
        }
        
         
        for(int j = 0; j < M; j++) {
             for(int i = 1; i < N; i++) {
                prefix[i][j] += prefix[i - 1][j];
            }
        }
        
        
        
        int answer = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] += prefix[i][j];
                if(board[i][j] >= 1) answer++;
            }
        }
        return answer;
    }
    
    public void skillOne(int r1, int c1, int r2, int c2, int degree, int[][] board) {
        for(int i = r1; i <= r2; i++) {
            for(int j = c1; j <= c2; j++) {
                board[i][j] += degree;
            }
        }
    }
}