class Solution {
    public int solution(String s) {
        char x = s.charAt(0);
        int first = 1;
        int another = 0;
        int answer = 0;
        for (int i = 1; i<s.length(); i++){
    
            
            System.out.print(s.charAt(i));
            if (s.charAt(i) == x) first++;
            else another++;
            
            if(first == another){
                if(i == s.length()-1) break;
                else{
                    x = s.charAt(i+1);
                    first = 0;
                    another = 0;
                    answer++;
                }
            } 
        }
        
        return answer+1;
    }
}