import java.util.Arrays;

public class Solution {
    public boolean solution(String[] phoneBook) {
        
        Arrays.sort(phoneBook);
      
        for (int i = 0; i < phoneBook.length - 1; i++) {
           String curP = phoneBook[i];
            String nextP = phoneBook[i + 1];
            
            if(nextP.startsWith(curP)) {
                return false;
            }
            
        }
        return true;
        
    }
}