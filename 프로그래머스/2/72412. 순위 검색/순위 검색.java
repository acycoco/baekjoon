import java.util.*;
class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        //10^5 * 4 * 5 * 10 ^ 4
        for (String in: info) {
            String[] splitted = in.split(" ");
            generateKeys(0, "", splitted, Integer.parseInt(splitted[4]));
        }
        
        for(String key: map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        int[] answer = new int[query.length];
        for(int i = 0; i < query.length; i++) {
            String[] qs= query[i].replaceAll(" and ","").split(" ");
            String key = qs[0];
            int score = Integer.parseInt(qs[1]);
            answer[i] = count(key, score);
        }
        return answer;
    }
    
    public int count(String key, int score) {
        if (!map.containsKey(key)) return 0;
        List<Integer> scores = map.get(key);
        int n = scores.size();
        return n - binarySearch(scores, n, score);
    }
    
    public int binarySearch(List<Integer> scores, int n, int score) {
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            int midScore = scores.get(mid);
            if (midScore >= score) {
                right = mid;
            } else {
                left = mid + 1;  
            }
        }
        return left;
    }
    
    public void generateKeys(int index, String s, String[] infoData, int score) {
        if (index == 4) {
            map.computeIfAbsent(s, k -> new ArrayList<>()).add(score);
            return;
        }
        generateKeys(index + 1, s + infoData[index], infoData, score);
        generateKeys(index + 1, s + "-", infoData, score);
    }
}