import java.util.*;
class Solution {
    public String solution(String number, int k) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i< number.length(); i++) {
            int num = number.charAt(i) - '0';
            
            while (k != 0 && !st.isEmpty() && st.peek() < num) {
                st.pop();
                k--;
            }
            st.push(num);
        }
        
        if (k > 0) {
            while (k > 0) {
                st.pop();
                
            k--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int digit : st) {
            sb.append(String.valueOf(digit));
        }
        return sb.toString();
        
    }
//         Stack<Character> st = new Stack<>();
        
//         for(int i = 0; i < number.length(); i++) {
//             char num = number.charAt(i);
//             while(k != 0 && !st.isEmpty() && st.peek() < num) {
//                 st.pop();
//                 k--;
//             }
//             st.push(num);
//         }
        
//         if(k > 0) {
//             while(k > 0) {
//                 st.pop();
//                 k--;
//             }
//         }
        
//         StringBuilder sb = new StringBuilder();
//         for(char digit : st) {
//             sb.append(digit);
//         }
//         return sb.toString();
//     }
}