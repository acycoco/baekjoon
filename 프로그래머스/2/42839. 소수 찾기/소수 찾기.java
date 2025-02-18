import java.util.*;
class Solution {
    Set<Integer> answer = new HashSet<>();
    boolean[] used;
    public int solution(String numbers) {
        char[] numbersC = numbers.toCharArray();
        used = new boolean[numbers.length()];
        
        comb(numbersC, "");
        int count = 0;
        for(int n: answer) {
            if(isPrime(n)) {
                count++;
            }
        }
        return count;
    }
    
    public void comb(char[] numbers, String now) {
        if(now.length() != 0) {
            answer.add(Integer.parseInt(now));
        }
        
        for(int i = 0; i < numbers.length; i++) {
            if(used[i]) continue; 
            used[i] = true;
            comb(numbers, now  + numbers[i]);
            used[i] = false;
        }
    }
    
    
    public boolean isPrime(int n) {
        if(n < 2) return false;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
   
}