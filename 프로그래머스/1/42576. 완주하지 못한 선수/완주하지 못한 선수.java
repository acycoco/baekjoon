import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for(String name: participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        
        for(String comp: completion) {
            map.put(comp, map.get(comp) - 1);
        }
        
      
        for(String name: map.keySet()) {
            if(map.get(name) > 0) {
                return name;
            }
        }
        return "";
        
//         Map<String, Integer> people = new HashMap();
//         for(int i = 0; i < participant.length; i++) {
//             people.put(participant[i], people.getOrDefault(participant[i], 0) + 1);
//         }
        
//         for(int i = 0; i < completion.length; i++) {
//             people.put(completion[i], people.get(completion[i]) - 1);
//         }
        
        
//         for(String p : people.keySet()) {
//             if(people.get(p) > 0) {
//                 return p;
//             }
//         }
        // return "";
    }
}