import java.util.*;
class Solution {
     char[] char5 = new char[]{ 'A', 'E', 'I', 'O', 'U'};
    List<String> dictionary = new ArrayList<>();
    public int solution(String word) {
        // generateWords("", 0);
        // Collections.sort(dictionary);
        // return dictionary.indexOf(word);
        
       int[] jump = {781, 156, 31, 6, 1}; // 각 자리수 점프 크기
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};

        int result = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = Arrays.binarySearch(vowels, word.charAt(i)); // 현재 문자 위치 찾기
            result += index * jump[i] + 1; // 해당 자리에서 몇 번째인지 계산
        }

        return result;
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