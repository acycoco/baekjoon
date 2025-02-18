import java.util.*;
class Solution {
    Set<Integer> answer = new HashSet<>();
    boolean[] used;
    public int solution(String numbers) {
        char[] numbersC = numbers.toCharArray();
        used = new boolean[numbers.length()];
        
        comb(0, numbersC.length, numbersC, "");
        int count = 0;
        for(int n: answer) {
            if(isPrime(n)) {
                count++;
            }
        }
        return count;
    }
    
    public void comb(int depth, int n, char[] numbers, String now) {
        if(depth == n) {
            if( now.length() == 0) return;
            answer.add(Integer.parseInt(now));
            return;
        }
        
        for(int i = 0; i < numbers.length; i++) {
            if(now.length() == 0 && numbers[i] == '0' || used[i]) continue; 
            used[i] = true;
            comb(depth + 1, n, numbers, now  + numbers[i]);
            used[i] = false;
        }
        comb(depth + 1, n, numbers, now);
    }
    
    
    public boolean isPrime(int n) {
        if(n == 1) return false;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
   
}