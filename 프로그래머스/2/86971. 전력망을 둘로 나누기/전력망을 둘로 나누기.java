import java.util.*;
class Solution {
    public static class Edge {
        int start;
        int end;
        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public boolean isSame(int[] wire) {
            return (wire[0] == this.start && wire[1] == this.end) ||
                (wire[0] == this.end && wire[1] == this.start);
        }
        
    }
    public int solution(int n, int[][] wires) {
        List<Edge> edges = new ArrayList<>();
        for(int[] wire: wires) {
            edges.add(new Edge(wire[0], wire[1]));
            edges.add(new Edge(wire[1], wire[0]));
        }
  
        int min = n;
        for(int i = 0; i < n - 1; i++) {
            min = Math.min(min,  countAbsDifference(edges, new boolean[n + 1], wires[i], n));
        }
        return min;
    }
    
    public int countAbsDifference(List<Edge> edges, boolean[] visited, int[] deletedWire, int n) {
        return Math.abs(n - visitGraph(1, edges, visited, deletedWire) * 2);
    }
    public int visitGraph(int start, List<Edge> edges, boolean[] visited, int[] deletedWire) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 1;
        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(Edge edge: edges) {
                if(edge.start == current && !visited[edge.end] && !edge.isSame(deletedWire)) {
                    queue.offer(edge.end);
                    visited[edge.end] = true;
                    count++;
                }
            }
        }
       
        return count;
    }
}