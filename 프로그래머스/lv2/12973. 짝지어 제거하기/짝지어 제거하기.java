import java.util.*;
class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        
        
        for(char input : s.toCharArray()){
            if (stack.isEmpty()){
                stack.push(input);
                continue;
            }
            if(stack.peek() == input){
                stack.pop();
                continue; //다음문자로
            } else stack.push(input);
            
        }
        
        if (stack.isEmpty())
            return 1;
        return 0;
        
    }
}
//         if (s.length() % 2 != 0)
//             return 0;
        
//         int answer = 0;

//         while (s.length() >= 2){
     
//             answer = 0;
//             for (int i = 1; i < s.length(); i++){
//                 if (s.charAt(i-1) == s.charAt(i)){ //
//                     String twoChar = ("" + s.charAt(i)).repeat(2);
//                     s = s.replace(twoChar, "");
//                     answer = 1;
//                     break;
              
//                 }
//             }
//             //한번 다 돌았는데 answer이 0이면 같은 숫자가 없음을 의미함.
//             if (answer == 0){
//                 return 0;
//             }
        
        // }
       
        
//         // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
//         System.out.println("Hello Java");

//         return 1;
// //     }
// }