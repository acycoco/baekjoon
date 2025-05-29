import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>>[][] dp;
    static int n;
    static int m;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        b = new int[m];
    
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
       
        
        //두 수열 중 첫번째 수가 큰 쪽은 사전 순으로 나중
        //같다면, 첫번째 수 빼고 다시 비교
        //길이가 0인 수열은 맨앞
        //수열 A와 수열 B가 공통으로 갖는 부분 수열들 중 사전 순으로 가장 나중인 것 구하세요.

        //2^100이라서 안됨

        List<Integer> result = findLCS();

        StringBuilder sb = new StringBuilder();
          System.out.println(result.size());
        if (result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                if (i > 0) System.out.print(" ");  // 첫 번째가 아닐 때만 공백
                System.out.print(result.get(i));
            }
            System.out.println();  // 마지막에 개행
        }
    }


    public static List<Integer> findLCS() {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            int maxValue = -1;
            int nextI = -1, nextJ = -1;
            for (int x = i; x < n; x++) {
                for (int y = j; y < m; y++) {
                    if (a[x] == b[y] && maxValue < a[x]) {
                        maxValue = a[x];
                        nextI = x;
                        nextJ = y;
                    }
                }
            }

            if (maxValue == -1) break;

            result.add(maxValue);
            i = nextI + 1;
            j = nextJ + 1;
        }
        return result;
    }

}
