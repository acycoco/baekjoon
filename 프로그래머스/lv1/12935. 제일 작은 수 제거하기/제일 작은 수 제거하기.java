class Solution {
    public int[] solution(int[] arr) {
        int[] answer;
        if (arr.length == 1){
            answer = new int[]{-1};
        }
        else {
            answer = new int[arr.length - 1];
             int min = arr[0];
            for (int i = 1; i < arr.length; i++){
                if(min > arr[i]) {
                    min = arr[i];
                } 
              
            }
            int answerIdx = 0;
             for (int i = 0; i < arr.length; i++){
                if(min != arr[i]) {
                    answer[answerIdx++] = arr[i];
                } 
              
            }
            
        }
        
       
        return answer;
    }
}