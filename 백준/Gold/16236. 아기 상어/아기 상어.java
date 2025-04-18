import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[][] arr;
    static int x, y;       // 상어 위치
    static int size = 2;   // 상어 초기 크기
    static int eat = 0;    // 먹은 물고기 수
    static int time = 0;   // 총 시간
    static int[] dx = {-1, 0, 0, 1}; // ↑ ← → ↓
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    x = i;
                    y = j;
                    arr[i][j] = 0;
                }
            }
        }
        while (true) {
            int[] target = bfs();
            if (target == null) break;
            // 이동 후 물고기 먹기
            x = target[0];
            y = target[1];
            int dist = target[2];
            time += dist;
            eat++;
            arr[x][y] = 0;
            if (eat == size) {
                size++;
                eat = 0;
            }
        }
        System.out.println(time);
    }
    public static int[] bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        List<int[]> candidates = new ArrayList<>();
        q.offer(new int[]{x, y, 0});
        visited[x][y] = true;
        int minDist = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1], dist = cur[2];
            
            // 이미 더 가까운 물고기를 찾았다면 더 먼 거리는 탐색할 필요 없음
            if (dist > minDist) continue;
            
            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (arr[nx][ny] > size) continue;
                
                visited[nx][ny] = true;
                // 먹을 수 있는 물고기 발견 시
                if (arr[nx][ny] > 0 && arr[nx][ny] < size) {
                    // 더 가까운 물고기를 찾은 경우 목록 초기화
                    if (dist + 1 < minDist) {
                        minDist = dist + 1;
                        candidates.clear();
                    }
                    // 같은 거리의 물고기는 목록에 추가
                    if (dist + 1 == minDist) {
                        candidates.add(new int[]{nx, ny, dist + 1});
                    }
                }
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }
        if (candidates.isEmpty()) return null;
        
        // 거리가 같은 물고기들 중에서 가장 위쪽(x값이 작은), 가장 왼쪽(y값이 작은) 순으로 정렬
        candidates.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]); // 행(위쪽) 우선
            return Integer.compare(a[1], b[1]); // 같은 행이면 열(왼쪽) 우선
        });
        
        return candidates.get(0);
    }
}