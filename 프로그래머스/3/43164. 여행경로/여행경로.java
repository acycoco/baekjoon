import java.util.*;
class Solution {
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        Map<String, List<String>> graph = new HashMap<>();
        for(int i = 0; i < n; i++) {
            graph.computeIfAbsent(tickets[i][0], k -> new ArrayList<>()).add(tickets[i][1]);
        }
        
        for(String key: graph.keySet()) {
            Collections.sort(graph.get(key));
        }
        
       List<String> route = new ArrayList<>();
        dfs("ICN", graph, route, n);
        
        return route.toArray(new String[n + 1]);
    }  
    public void dfs(String cur, Map<String, List<String>> graph, List<String> route, int n) {
        List<String> dests = graph.get(cur);

        // 현재 공항에서 더 갈 곳이 없다면 경로에 추가
        while (dests != null && !dests.isEmpty()) {
            String next = dests.remove(0);
            dfs(next, graph, route, n);
        }

        // 경로에 추가 (후입선출 구조로 마지막부터 쌓임)
        route.add(0, cur);
    }
    

}