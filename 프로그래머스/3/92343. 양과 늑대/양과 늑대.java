import java.util.*;
class Solution {
    int answer;
     List<Integer>[] graph;
    public void dfs(List<Integer> path, int cur, int n, int[] info, int sheep, int wolf) {
        if (info[cur] == 1) {
            wolf++;
        } else {
            sheep++;
            answer = Math.max(answer, sheep);
        }
        
        List<Integer> nowPath = new ArrayList<>(path);
        nowPath.remove(Integer.valueOf(cur));
        for (int child : graph[cur]) {
            nowPath.add(child);
        }
        
        for (int next: nowPath) {
             if (info[next] == 1 && sheep <= wolf + 1) {
                continue;
            } 
            dfs(nowPath, next, n, info, sheep, wolf);
        }
    }
    
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
        }
        List<Integer> path = new ArrayList<>();
        path.add(0);
        if (info[0] == 1) {
            
        dfs(path, 0, n, info, 0, 0);
        } else {
            
        dfs(path, 0, n, info, 0, 0);
        }
        return answer;
        
        //이진트리 노드에 늑대와 양이 한마리씩
        //루트노드에서 출발해서 양을 모으려고 한다.
        //늑대는 내가 모은 양의 수보다 늑대수가 같거나 많아지면 양을 잡아먹는다.
        //최대한 많은 수의 양을 모아서 다시 루트노드로 돌아오려고 한다.
        
        //아마 백트래킹 아니면 dp로 풀 수 있을 듯
        //0 양 1 늑대
        
        //우선 dfs로 탐색하면서 백트래킹을 이용한다면 시간 복잡도는 ? 모르겠음 n ! ??
//         int n = info.length;
//         graph = new ArrayList[n];
//         for(int i = 0; i < n; i++) {
//             graph[i] = new ArrayList<>();
//         }
//         for(int[] edge: edges) {
//             graph[edge[0]].add(edge[1]);
//         }
//         dfs(0, 1, 0,info, new HashSet<>());

//         return answer;
    }
    
//     public void dfs(int index, int sheep, int worf, int[] info, Set<Integer> available) {
      
//         if(sheep <= worf) {
//             return;
//         }
//         answer = Math.max(answer, sheep);
//       for (int next : graph[index]) available.add(next); 
//         for(int next: available) {
//              Set<Integer> nextAvailable = new HashSet<>(available);
//             nextAvailable.remove(next);        
//             if(info[next] == 0) dfs(next, sheep + 1, worf, info, nextAvailable);
//             else dfs(next, sheep, worf + 1, info, nextAvailable);
//         }
        //문제는 방문했던데를 또 방문해서 다른 데로 갈 수 있음 근데 그러다가 무한 루프에 걸릴 가능성이 있다는 것 방문 처리를 간 루트?로 해야될 것 같음 아 아마 여기서 dp로 해결할 수 있을 것 같은데 잘 모르겠음

    // }
}