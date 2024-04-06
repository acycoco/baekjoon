
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] setNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        setNode = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            setNode[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int opr = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (opr == 0) {
                union(a, b);
            } else {
                boolean result = same(a, b);
                if (result) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static boolean same(int a, int b) {
        if (a == b) {
            return true;
        }
        if (find(a) == find(b)) {
            return true;
        }
        return false;
    }
    private static int find(int a) { //대표 노드를 찾는 연산
        if (a == setNode[a]) {
            return a;
        }
        setNode[a] = find(setNode[a]);
        return setNode[a];
    }

    private static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);
        if (findA == findB) return;
        else if (findA < findB) {
            setNode[findB] = findA;
        } else {
            setNode[findA] = findB;
        }
    }
}
