
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[][] arr;
    static int max = 0;
    static int[][][] blocks = {
            {{0, 1}, {0, 2}, {0, 3}},  // ㅡ
            {{1, 0}, {2, 0}, {3, 0}},  // ㅣ

            {{0, 1}, {1, 0}, {1, 1}},  // ㅁ

            {{1, 0}, {2, 0}, {2, 1}},  // ㄴ
            {{0, 1}, {0, 2}, {1, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 1}, {0, 2}, {-1, 2}},
            {{0, 1}, {-1, 1}, {-2, 1}},  // ㄱ
            {{1, 0}, {1, 1}, {1, 2}},
            {{0, 1}, {1, 0}, {2, 0}},
            {{0, 1}, {0, 2}, {1, 2}},

            {{1, 0}, {1, 1}, {2, 1}},  // ㄹ
            {{0, 1}, {-1, 1}, {-1, 2}},
            {{1, 0}, {1, -1}, {2, -1}},
            {{0, 1}, {1, 1}, {1, 2}},

            {{0, 1}, {0, 2}, {1, 1}},  // ㅜ
            {{1, 0}, {2, 0}, {1, 1}},  // ㅏ
            {{0, 1}, {0, 2}, {-1, 1}}, // ㅗ
            {{1, 0}, {2, 0}, {1, -1}}  // ㅓ
    };

    //    static int[][][] blocks = {
//            {{0, 1}, {0, 2}, {0, 3}}, {{1, 0}, {2, 0}, {3, 0}}, //1
//            {{0, 1}, {1, 0}, {1, 1}}, //2
//            {{1, 0}, {2, 0}, {2, 1}}, {{1, 0}, {2, 0}, {2, -1}}, {{-1, 0}, {1, 0}, {2, 0}}, {{1, 0}, {2, 0}, {0, 1}}, //3대칭
//            {{0, 1}, {0, 2}, {1, 0}}, {{0, 1}, {1, 1}, {2, 1}}, {{1, 0}, {1, -1}, {1, -2}}, //3회전
//            {{1, 0}, {1, 1}, {2, 1}}, {{0, 1}, {1, 0}, {1, -1}}, {{0, 1}, {1, 1}, {1, 2}}, {{0, 1}, {-1, 1}, {1, 0}}, //4 대칭, 회전
//            {{0, 1}, {1, 1}, {0, 2}}, {{0, 1}, {-1, 1}, {1, 1}}, {{1, -1}, {1, 0}, {1, 1}}, {{1, 0}, {2, 0}, {1, 1}} //5회전
//    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for(int[][] block : blocks) {
                    max = Math.max(max, calc(block, i, j));
                }
            }
        }
        System.out.println(max);
    }

    public static int calc(int[][] block, int x, int y) {
        int sum = arr[x][y];
        for (int[] point : block) {
            int i = point[0] + x;
            int j = point[1] + y;
            if (i >= 0 && i < n && j >= 0 && j < m) {
                sum += arr[i][j];
            }
            else {
                return -1;
            }
        }
        return sum;
    }
}
