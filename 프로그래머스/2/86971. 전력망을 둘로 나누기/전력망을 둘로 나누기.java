import java.util.*;
class Solution {
    int[] parent;
    public void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);
        
        if(findA < findB) {
            parent[findB] = findA;
        } else if(findA > findB) {
            parent[findA] = findB;
        }
    }
    
    public int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    
    public int delete(int indexWire, int n, int[][] wires) {
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
         for(int i = 0 ; i < n - 1; i++) {
            if(i == indexWire) continue;
            union(wires[i][0], wires[i][1]);
        }
        return calculateDiff(n);
    }
    public int calculateDiff(int n) {
        int parentA = find(1);
        int countA = 1;
        for(int i = 2; i <= n; i++) {
            if(parentA == find(i)) countA++;
        }
        return Math.abs(n - countA * 2);
    }
    public int solution(int n, int[][] wires) {
        
        //트리를 2개로 분할
        //개수를 비슷하게
        //차이 절댓값 리턴
     
        int result = n;
        for(int i = 0 ; i < n - 1; i++) {
            result = Math.min(delete(i, n, wires), result);
        }
        
        return result;
        
    
        // int minDiff = n;
        // for(int i = 0; i < wires.length; i++) {
        //     int[] parent = new int[n + 1]; //연결되어 있는 작은 노드를 부모로
        //     int[] rank = new int[n + 1];
        //     for(int j = 1; j <= n; j++) {
        //         parent[j] = j;
        //     }
        //     for(int j = 0; j < wires.length; j++) {
        //         if(i == j) 
        //             continue;
        //         union(wires[j][0], wires[j][1], parent, rank);
        //     }
        //     int diff = Math.abs(n - countNodes(1, n, parent) * 2);
        //     minDiff = Math.min(diff, minDiff);
        // }
        // return minDiff;
    }
    
//     public int find(int a, int[] parent) {
//         if(a == parent[a]) 
//             return a;
//         return parent[a] = find(parent[a], parent);
//     }
    
//     public void union(int a, int b, int[] parent, int[] rank) {
//         int parentA = find(a, parent);
//         int parentB = find(b, parent);
        
//          if (parentA != parentB) {
//             if (rank[parentA] > rank[parentB]) {
//                 parent[parentB] = parentA;
//             } else if (rank[parentA] < rank[parentB]) {
//                 parent[parentA] = parentB;
//             } else {
//                 parent[parentB] = parentA;
//                 rank[parentA]++;
//             }
//         }
        
//     }
    
//     public int countNodes(int a, int n, int[] parent) {
//         int count = 0;
//         int parentA = find(a, parent);
//         for(int i = 1; i <= n; i++) {
//             if(find(i, parent) == parentA) {
//                 count++;
//             }
//         }
//         return count;
//     }
//     public int solution(int n, int[][] wires) {
//         List<List<Integer>> edges = new ArrayList<>();
//         for(int i = 0; i <= n; i++) {
//             edges.add(new ArrayList<>());
//         }
        
//         for(int[] wire: wires) {
//             edges.get(wire[0]).add(wire[1]);
//              edges.get(wire[1]).add(wire[0]);
//         }
  
//         int min = n;
//         for(int i = 0; i < n - 1; i++) {
//             min = Math.min(min,  countAbsDifference(edges, new boolean[n + 1], wires[i], n));
//         }
//         return min;
//     }
    
//     public int countAbsDifference(List<List<Integer>> edges, boolean[] visited, int[] deletedWire, int n) {
//         return Math.abs(n - visitGraph(1, edges, visited, deletedWire) * 2);
//     }
    
//     public int visitGraph(int start, List<List<Integer>> edges, boolean[] visited, int[] deletedWire) {
//         Queue<Integer> queue = new LinkedList<>();
//         queue.offer(start);
//         visited[start] = true;
//         int count = 1;
//         while(!queue.isEmpty()) {
//             int current = queue.poll();
//             for(int neighbor: edges.get(current)) {
//                 if(!visited[neighbor] && !(deletedWire[0] == current && deletedWire[1] == neighbor)
//                   && !(deletedWire[1] == current && deletedWire[0] == neighbor)) {
//                     queue.offer(neighbor);
//                     visited[neighbor] = true;
//                     count++;
//                 }
//             }
//         }
       
//         return count;
//     }
}