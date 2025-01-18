import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
       if(!isInWords(words, target)) {
           return 0;
       }
        return bfs(begin, words, target);
    }
    
    public boolean isInWords(String[] words, String target) {
        for(int i = 0; i < words.length; i++) {
           if(words[i].equals(target)) {
               return true;
           }
       }
        return false;
    }
    public int bfs(String begin, String[] words, String target) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.add(new Node(begin, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for(int i = 0; i < words.length; i++) {
                if(visited[i] || !differenceIsOne(cur.word, words[i])) {
                    continue;
                }
                if(words[i].equals(target)) {
                    return cur.dist + 1;
                }
                queue.add(new Node(words[i], cur.dist + 1));
                visited[i] = true;
            }
        }
        
        return 0;
    }
    
    public boolean differenceIsOne(String s1, String s2) {
        int count = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                if(++count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }
    
    public static class Node {
        String word;
        int dist;
        public Node(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }
}