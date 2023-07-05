import java.util.Arrays;

public class Solution {
    public boolean solution(String[] phoneBook) {
        Arrays.sort(phoneBook); // 전화번호부를 사전순으로 정렬
        
        for (int i = 0; i < phoneBook.length - 1; i++) {
            if (phoneBook[i + 1].startsWith(phoneBook[i])) {
                return false;
            }
        }
        
        return true;
    }
}