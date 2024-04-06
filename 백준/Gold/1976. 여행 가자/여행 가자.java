
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int cityN;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityN = Integer.parseInt(br.readLine());
        parent = new int[cityN];
        int planN = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < cityN; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < cityN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cityN; j++) {
                int connect = Integer.parseInt(st.nextToken());
                if (i < j) { //양방향이므로 자기자신의 연결 말고만 확인하면 됨 ex) 1,2  1,3  2,3
                    if (connect == 1) {
                        union(i, j);
                    }
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] result = new int[planN];
        boolean canGo = true;
        for (int i = 0; i < planN; i++) {
            int planCity = Integer.parseInt(st.nextToken()) - 1; //idx에 맞춰서 1 빼기
            result[i] = find(planCity);
            if (i != 0) {
                if (result[i] != result[i - 1]) {
                    canGo = false;
                    break;
                }
            }
        }
        if (canGo) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA != findB) {
            parent[findB] = findA;
        }
    }
    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
