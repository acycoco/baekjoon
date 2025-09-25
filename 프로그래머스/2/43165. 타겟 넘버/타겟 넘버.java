import java.util.*;
class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, target, numbers);
        return answer;
    }
    
    public void dfs(int index, int cur, int target, int[] numbers) {
        if (index == numbers.length) {
            if(target == cur) answer++;
            return;
        }
        dfs(index + 1, cur + numbers[index], target, numbers);
        dfs(index + 1, cur - numbers[index], target, numbers);
    }
//     int answer = 0;
//     public int solution(int[] numbers, int target) {
//         dfs(0, 0, numbers, target);
//         return answer;
//     }
    
//     public void dfs(int index, int sum, int[] numbers, int target) {
//         if(index == numbers.length) {
//             if(target == sum) {
//                 answer++;
//             }
//             return;
//         }
//         dfs(index + 1, sum + numbers[index], numbers, target);
//         dfs(index + 1, sum - numbers[index], numbers, target);
//     }
}