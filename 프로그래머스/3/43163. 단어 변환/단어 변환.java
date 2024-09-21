import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        
        return bfs(begin, target, words);
    }
    
    public int bfs(String begin, String target, String[] words) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(begin, 0));
        boolean[] visited = new boolean[words.length];
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for(int i = 0; i < words.length; i++) {
                if(!visited[i] && isNeighbor(cur.word, words[i])) {
                    queue.offer(new Node(words[i], cur.count + 1));
                    visited[i] = true;
                    if (words[i].equals(target)) {
                        return cur.count + 1;
                    }
                }
            }
        }
        return 0;
    }
    
    public boolean isNeighbor(String cur, String word) {
        int differentCount = 0;
        for(int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) != word.charAt(i)) {
                differentCount++;
                if(differentCount > 1) {
                    return false;
                } 
            }
        }
        return true;
    }
    
    public static class Node {
        String word;
        int count;
        
        public Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}