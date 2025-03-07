
import java.util.*;

class Solution {
    List<Integer>[] graph;
    int ans = 0;

    public int solution(int[] info, int[][] edges) {

        int n = info.length;
        graph = new List[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        go(0, info, 1, 0, new HashSet<>());
        return ans;
    }

    void go(int idx, int[] info, int sheep, int wolv, Set<Integer> unused) {
        if(sheep == wolv) return;

        ans = Math.max(sheep, ans);

        for(int next : graph[idx]) unused.add(next);

        for(int next : unused) {
            Set<Integer> set = new HashSet<>(unused);
            set.remove(next);
            if(info[next] == 1) 
                go(next, info, sheep, wolv + 1, set);
            else go(next, info, sheep+1, wolv, set);
        }
    }
}
