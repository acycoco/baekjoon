import java.util.*;
class Solution {
    int N;
    List<int[]>[] time;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        time = new ArrayList[n + 1];
        
        for (int i = 0; i < n + 1; i++) {
            time[i] = new ArrayList<>();
        }
        
        for (int[] path: paths) {
            int a = path[0];
            int b = path[1];
            int d = path[2];
            time[a].add(new int[]{b, d});
            time[b].add(new int[]{a, d});
        }
       
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 10_000_000);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        
        for (int gate : gates) {
            dist[gate] = 0;
            pq.add(new int[]{0, gate});
        }
        
        Set<Integer> summitSet = new HashSet<>();
        for (int summit: summits) {
            summitSet.add(summit);
        }
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curD = cur[0];
            int curV = cur[1];
            
            if (curD > dist[curV]) continue;
            if (summitSet.contains(curV)) continue;
            
            for (int[] next : time[curV]) {
                int nextV = next[0];
                int nextD = next[1];
                if (dist[nextV] > Math.max(nextD, curD)) {
                    
                dist[nextV] = Math.max(nextD, curD);
                pq.add(new int[]{dist[nextV], nextV});
                }
            }
        }
        int minSummit = 1;
        int min = 10_000_000;
        
        Arrays.sort(summits);
        for (int summit : summits) {
            if (min > dist[summit]) {
                min = dist[summit];
                minSummit = summit;
             }
        }
        return new int[]{minSummit, min};
    }
}