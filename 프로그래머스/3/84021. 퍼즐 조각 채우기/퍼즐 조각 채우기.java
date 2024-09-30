// import java.util.*;

// class Solution {
//     // 네 방향 이동 (상, 하, 좌, 우)
//     int[] dx = {-1, 1, 0, 0};
//     int[] dy = {0, 0, -1, 1};
    
//     public int solution(int[][] game_board, int[][] table) {
//         int n = game_board.length;
//         List<List<int[]>> emptySpaces = new ArrayList<>(); // 빈 공간 리스트
//         List<List<int[]>> puzzlePieces = new ArrayList<>(); // 퍼즐 조각 리스트
        
//         // 1. game_board에서 빈 공간 추출
//         boolean[][] visited = new boolean[n][n];
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (game_board[i][j] == 0 && !visited[i][j]) {
//                     List<int[]> space = new ArrayList<>();
//                     dfs(game_board, visited, space, i, j, 0);
//                     emptySpaces.add(normalize(space));
//                 }
//             }
//         }
        
//         // 2. table에서 퍼즐 조각 추출
//         visited = new boolean[n][n];
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (table[i][j] == 1 && !visited[i][j]) {
//                     List<int[]> piece = new ArrayList<>();
//                     dfs(table, visited, piece, i, j, 1);
//                     puzzlePieces.add(normalize(piece));
//                 }
//             }
//         }
        
//         // 3. 퍼즐 조각을 빈 공간에 맞춰보기
//         boolean[] used = new boolean[puzzlePieces.size()];
//         int answer = 0;
        
//         for (List<int[]> emptySpace : emptySpaces) {
//             for (int i = 0; i < puzzlePieces.size(); i++) {
//                 if (used[i]) continue;
//                 List<int[]> piece = puzzlePieces.get(i);
                
//                 // 4번 회전하면서 퍼즐이 빈 공간에 맞는지 확인
//                 boolean matched = false;
//                 for (int r = 0; r < 4; r++) {
//                     if (matches(emptySpace, piece)) {
//                         used[i] = true;
//                         answer += piece.size();
//                         matched = true;
//                         break;
//                     }
//                     piece = rotate(piece);
//                 }
//                 if (matched) break;
//             }
//         }
        
//         return answer;
//     }
    
//     // DFS를 통해 빈 공간 또는 퍼즐 조각 추출
//     private void dfs(int[][] board, boolean[][] visited, List<int[]> shape, int x, int y, int target) {
//         visited[x][y] = true;
//         shape.add(new int[]{x, y});
        
//         for (int i = 0; i < 4; i++) {
//             int nx = x + dx[i];
//             int ny = y + dy[i];
//             if (nx >= 0 && nx < board.length && ny >= 0 && ny < board.length && 
//                 board[nx][ny] == target && !visited[nx][ny]) {
//                 dfs(board, visited, shape, nx, ny, target);
//             }
//         }
//     }
    
//     // 퍼즐 조각을 회전시킴 (90도 시계 방향)
//     private List<int[]> rotate(List<int[]> piece) {
//         List<int[]> rotated = new ArrayList<>();
//         for (int[] p : piece) {
//             rotated.add(new int[]{p[1], -p[0]});
//         }
//         return normalize(rotated);
//     }
    
//     // 퍼즐이나 빈 공간을 (0, 0)을 기준으로 정규화
//     private List<int[]> normalize(List<int[]> shape) {
//         List<int[]> normalized = new ArrayList<>();
//         int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
//         for (int[] p : shape) {
//             minX = Math.min(minX, p[0]);
//             minY = Math.min(minY, p[1]);
//         }
//         for (int[] p : shape) {
//             normalized.add(new int[]{p[0] - minX, p[1] - minY});
//         }
//         normalized.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
//         return normalized;
//     }
    
//     // 두 모양이 동일한지 확인
//     private boolean matches(List<int[]> space, List<int[]> piece) {
//         if (space.size() != piece.size()) return false;
//         for (int i = 0; i < space.size(); i++) {
//             if (space.get(i)[0] != piece.get(i)[0] || space.get(i)[1] != piece.get(i)[1]) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

import java.util.*;
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public static class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }
    public int solution(int[][] game_board, int[][] table) {
    
        //game_board dfs
        List<List<Point>> blocks = new ArrayList<>();
        visit(blocks, game_board, 0, 1);
        
        //table dfs
        List<List<Point>> puzzles = new ArrayList<>();
        visit(puzzles, table, 1, 0);
        
        List<List<Point>> nomalizedBlocks = new ArrayList<>();
        for(List<Point> block: blocks) {
            nomalizedBlocks.add(nomalize(block));
        }
        //빈 블럭돌면서 퍼즐 조각을 회전시키면서 매칭 시켜본다.
        //퍼즐 조각을 사용했는지에 대한 정보를 저장한다.
        //채워진 블럭 개수를 더한다.
        boolean[] used = new boolean[puzzles.size()];
        int filledCount = 0;
        //빈칸블럭을 순회한다.
        for(List<Point> block: nomalizedBlocks) {
            boolean matched = false;
            //퍼즐을 순회한다.
            for(int puzzleIdx = 0; puzzleIdx < puzzles.size(); puzzleIdx++) {
                if(used[puzzleIdx]) {
                    continue;
                }
                
                //한 퍼즐을 회전후 비교
                List<Point> rotated = puzzles.get(puzzleIdx);
                for(int i = 0; i < 4; i++) {
                    rotated = rotation(rotated);
                    if (match(block, rotated)) {
                        used[puzzleIdx] = true;
                        matched = true;
                        filledCount += block.size();
                        break;
                    }
                }
                if (matched) {
                    break;
                }
            }  
        }
        
      
        //추출한 두 리스트 모두 normalize
        
        //비교 같으면 그 빈칸은 끝
        //game_board에서 남은 개수
        return filledCount;
    }
    
    public void dfs(Point point, List<Point> points, int[][] board, int blockToFind, int blockToNotFind)  {
        points.add(point);
        board[point.x][point.y] = blockToNotFind;
        for(int i = 0; i < 4; i++) {
            int nextX = point.x + dx[i];
            int nextY = point.y + dy[i];
            if(nextX >= 0 && nextX < board.length && nextY >= 0 && nextY < board.length && board[nextX][nextY] == blockToFind) {
                dfs(new Point(nextX, nextY), points, board, blockToFind, blockToNotFind);
            }
        }
    }
    
    public void visit(List<List<Point>> findToBlocks, int[][] board, int blockToFind, int blockToNotFind) {
        for(int i = 0; i < board.length; i++) { 
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == blockToFind) {
                    List<Point> points = new ArrayList<>();
                    dfs(new Point(i, j), points, board, blockToFind, blockToNotFind);
                    findToBlocks.add(points);
                }
            }
        }
    }
    
    public List<Point> rotation(List<Point> puzzles) {
        int minX = Integer.MAX_VALUE;
         for(Point p: puzzles) { 
            minX = Math.min(minX, p.x);
        }
        List<Point> newPoints = new ArrayList<>();        
        for(Point p: puzzles) {
            newPoints.add(new Point(p.y, minX - p.x));
        }
        return nomalize(newPoints);
    }
    
     public List<Point> nomalize(List<Point> puzzles) {
        List<Point> newPoints = new ArrayList<>();
        int minX = Integer.MAX_VALUE;
         int minY = Integer.MAX_VALUE;
        for(Point p: puzzles) { 
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }
        for(Point p: puzzles) {
            newPoints.add(new Point(p.x - minX, p.y - minY));
        }
         
        Collections.sort(newPoints);
        return newPoints;
    }
    
    public boolean match(List<Point> block, List<Point> puzzle) {
        if(block.size() != puzzle.size()) {
            return false;
        }
        for(int i = 0; i < block.size(); i++) {
            Point blockP = block.get(i);
            Point puzzleP = puzzle.get(i);
            if (blockP.x != puzzleP.x || blockP.y != puzzleP.y) {
                return false;
            }
        }
        return true;
    }
} 