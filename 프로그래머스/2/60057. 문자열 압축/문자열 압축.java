import java.util.*;
class Solution {
    
    public int zip(String s, int len, int n) {
        StringBuilder sb = new StringBuilder();
        String pre = s.substring(0, len);
        int cnt = 1;
        
        for (int i = len; i < n; i+= len) {
            String cur = "";
            
            if (i + len > s.length()) {
                cur = s.substring(i);
            } else {
                cur = s.substring(i, i + len);
            }
            
            if (pre.equals(cur)) {
                cnt++;
            } else {
                if (cnt > 1) {
                    sb.append(cnt);
                }
                sb.append(pre);
                
                pre = cur;
                cnt = 1;
            }
        }
        
        if (cnt > 1) {  
            sb.append(cnt);
        }
        
        sb.append(pre);
        return sb.length();
    }
    public int solution(String s) {
        int min = s.length();
        
        if (s.length() == 1) return 1;
        
        int n = min;
        for (int i = 1; i <= n / 2; i++) {
            min = Math.min(zip(s, i, n), min);
        }
        return min;
        
        
        
        
        
        
        
        
        
        
        
//         //n ^ 2
//         for (int len = 1; len <= s.length() / 2; len++) {
//             int start = len;
//             int totalCount = 0;
//             int count = 1;
//             String preSub = s.substring(0, len);
//             while (start + len < s.length()) { 
//                 String sub = s.substring(start, start + len);
//                 if (sub.equals(preSub)) {
//                     count++;
//                 } else {
//                     totalCount += len;
//                     if (count > 1) {
//                         totalCount++;
//                     } 
//                     count = 1;
//                 }
                
//                 preSub = sub;
//                 start += len;
//             }
            
            
//             if (count > 1) {
//                 totalCount++;
//                 totalCount += len;
//             }
//             if (start < s.length()) {
//                 totalCount += (s.length() - start);
//             }
            
//             if (totalCount < min) {
//                 min = totalCount;
//             }
            
//             System.out.println(len + " " + totalCount);
            
            
           
//         }
            
            
//        return min;     
            
            
            
            
    }
//         //단위를 늘려가면서 가장 짧은 문자열 길이는?
//         //아마 반까지만 해보면 될듯 완전 탐색으로 해야할 듯함
//         int answer = s.length();
//         for(int i = 1; i <= s.length() / 2; i++) {
//             answer = Math.min(answer, compact(s, i));
//         }
        
//         return answer;
//     }
    
//     public int compact(String str, int len) {
//         StringBuilder sb = new StringBuilder();
//         int count = 1;
//         String prev = str.substring(0, len);
        
//         for(int i = len; i <= str.length() - 1; i += len) {
//             String next;
//             if (i + len <= str.length()) {
//                 next = str.substring(i, i + len);
//             } else {
//                 next = str.substring(i);
//             }
            
//             if(prev.equals(next)) {
//                 count++;
//             } else {
//                 if (count > 1) sb.append(count);
//                 sb.append(prev);
//                 prev = next;
//                 count = 1;
//             }
//         }
        
//         if(count > 1) sb.append(count);
//         sb.append(prev);
       
//         return sb.length();
//     }
}