import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        //완전탐색
       // 넉넉하게 2000으로 잡아서 OutOfBounds 에러 원천 차단
        int MAX = 2000; 
        boolean[][] visited = new boolean[MAX][MAX]; 
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0, 0}); // {화면, 클립보드, 시간}
        visited[1][0] = true; // [중요] 시작점 방문 처리!

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int screen = cur[0];
            int clip = cur[1];
            int time = cur[2];

            // 목표 도달 시 종료
            if (screen == target) {
                System.out.println(time);
                break;
            }

            // 1. 복사: 화면(screen) -> 클립보드(clip)
            // 조건: 화면에 이모티콘이 1개 이상 있어야 함
            if (screen > 0 && screen < MAX && !visited[screen][screen]) {
                visited[screen][screen] = true; // 방문 처리 필수
                queue.add(new int[]{screen, screen, time + 1});
            }

            // 2. 붙여넣기: 클립보드(clip) -> 화면(screen)
            // 조건: 클립보드가 비어있지 않아야 하고, 붙여넣었을 때 배열 범위를 넘지 않아야 함
            if (clip > 0 && screen + clip < MAX && !visited[screen + clip][clip]) {
                visited[screen + clip][clip] = true;
                queue.add(new int[]{screen + clip, clip, time + 1});
            }

            // 3. 삭제: 화면 이모티콘 1개 감소
            // 조건: 화면에 이모티콘이 1개 이상 있어야 함
            if (screen > 0 && !visited[screen - 1][clip]) {
                visited[screen - 1][clip] = true;
                queue.add(new int[]{screen - 1, clip, time + 1});
            }
        }
    }
}
