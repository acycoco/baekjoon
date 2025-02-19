import java.util.*;
class Solution {
     char[] char5 = new char[]{ 'A', 'E', 'I', 'O', 'U'};
    List<String> dictionary = new ArrayList<>();
    public int solution(String word) {
        generateWords("", 0);
        Collections.sort(dictionary);
        return dictionary.indexOf(word);
        
//         int answer = 0;
 
//         Map<Character, Integer> map = new HashMap<>();
//         char[] chars = new char[]{' ', 'A', 'E', 'I', 'O', 'U'};
//         for(int i = 1; i <= 5; i++) { 
//             map.put(chars[i], i);
//         }
        
//         char[] wordChars = word.toCharArray();
     
//         for(int i = 0; i < wordChars.length; i++) {
//             answer += Math.pow(5, 5 - i) * map.get(wordChars[i]);
//         }
//         return answer;
    }
    
    public void generateWords(String current, int depth) {
        dictionary.add(current);
        if(depth == 5) {
            return;
        }
        
        for(char c: char5){
            generateWords(current + c, depth + 1);
        }
        
    }
}