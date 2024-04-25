import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static boolean[] deleted;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static int root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        deleted = new boolean[n];
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        for (int now = 0; now < n; now++) {
            int parent = Integer.parseInt(stringTokenizer.nextToken());
            if (parent == -1) {
                root = now;
                continue;
            }
            graph.get(parent).add(now);
        }

        int deleteRoot = Integer.parseInt(br.readLine());
        deleteSubTree(deleteRoot);
        int leaf = countLeaf();
        System.out.println(leaf);
    }

    private static void deleteSubTree(int deleteRoot) {
        deleted[deleteRoot] = true;
        queue.add(deleteRoot);
        //자식들을 다 삭제시키기
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                queue.add(next);
                deleted[next] = true;
            }
        }
    }

    public static int countLeaf() {
        int count = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (deleted[now]) { //삭제된 노드일경우 스킵
                continue;
            }
            if (graph.get(now).isEmpty()) { //자식이 비어있는 경우 리프노드
                count++;
                continue;
            }
            boolean hasChild = false;
            for (int next : graph.get(now)) {
                if (!deleted[next]) { //삭제되지 않은 노드가 있으면
                    queue.add(next);
                    hasChild = true;
                }
            }
            if (!hasChild) { //모든 자식이 다 삭제된 자식이면 리프노드
                count++;
            }
        }
        return count;
    }


}
