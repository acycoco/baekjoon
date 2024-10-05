import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Point> map = new HashMap<>();
        StringTokenizer st;
        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                int n = Integer.parseInt(st.nextToken());
                map.put(n, new Point(i, j));
            }
        }
        
        int answer = 0;
        int[] bingo = new int[12];
        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                int n = Integer.parseInt(st.nextToken());
                Point p = map.get(n);
                bingo[p.x]++;
                if(bingo[p.x] == 5) {
                    answer++;
                }
                bingo[5 + p.y]++;
                if(bingo[5 + p.y] == 5) {
                    answer++;
                }
                if(p.x == p.y) {
                    bingo[10]++;
                    if(bingo[10] == 5) {
                        answer++;
                    }
                }
                
                if(p.x + p.y == 4) {
                    bingo[11]++;
                     if(bingo[11] == 5) {
                        answer++;
                    }
                }
                
                if(answer >= 3) {
                    System.out.println(5 * i + j + 1);
                    return;
                }
                
            }
        }
        
    }
    
    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}