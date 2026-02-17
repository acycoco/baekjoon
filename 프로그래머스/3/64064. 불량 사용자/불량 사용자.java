import java.util.*;
class Solution {
    //경우의 수 
    //각 제재 아이디에 해당하는 경우의 수의 곱을 구하면 된다.
    //결국 조합이 고유해야 한다.
    //내가 일일이 구하는 것보다 자료구조에 넣어서 개수를 구하면 좋을 것 같은데
    //그럼 각자의 banned_id마다 가능한 id목록들을 구한다. 그래서 조합을 만든다. 
    List<String>[] avaiableUser;
    Set<Set<String>> result = new HashSet<>();
    int count;
    public boolean isSame(String ban, String user) {
        if (ban.length() != user.length()) return false;
        for (int i = 0; i < user.length(); i++) {
            if (ban.charAt(i) == '*') continue;
            if (ban.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }
    public int solution(String[] user_id, String[] banned_id) {
        int banLen = banned_id.length;
        avaiableUser = new ArrayList[banLen];
             for (int i = 0; i < banLen; i++) {
                 avaiableUser[i] = new ArrayList<>();
             }
        for (int i = 0; i < banLen; i++) {
            for (String user: user_id) {
                if (isSame(banned_id[i], user)) {
                    avaiableUser[i].add(user);
                }
            }
        }
        dfs(0, banLen, new HashSet<>());
        return result.size();
    }
    public void dfs(int index, int n, Set<String> currentSet) {
        if (index == n) {
            result.add(new HashSet<>(currentSet));
            return;
        }
        for (int i = 0; i < avaiableUser[index].size(); i++) {
            String target = avaiableUser[index].get(i);
            if (currentSet.contains(target)) continue;
            currentSet.add(target);
            dfs(index + 1, n, currentSet);
            currentSet.remove(target);
         }
    }
        
    
    
    
    
    
    
    
    
    
//     Set<Integer> result = new HashSet<>();
//     public int solution(String[] user_id, String[] banned_id) {
       
//         for (String bannedId: banned_id) {
//         }
//         dfs(0, user_id, banned_id, 0);
//         return result.size();
//     }
    
//     public void dfs(int bit, String[] user_id, String[] banned_id, int depth) {
//         if (depth == banned_id.length) {
//             result.add(bit);
//             return;
//         }
        
        
//         String reg = banned_id[depth].replace("*", "[\\w\\d]");
//         for (int i = 0; i < user_id.length; i++) {
//             if (((bit >> i) & 1) == 1|| !user_id[i].matches(reg)) continue;

//             dfs((bit | 1 << i), user_id, banned_id, depth + 1);
    
//         }
//     }
//     Set<Set<String>> result = new HashSet<>();
    
//     public int solution(String[] user_id, String[] banned_id) {
      
//         dfs(new HashSet<>(), user_id, banned_id, 0);
//         return result.size();
//     }
    
//     public void dfs(Set<String> current, String[] user_id, String[] banned_id, int depth) {
//         if (depth == banned_id.length) {
//             if (current.size() == banned_id.length) {
//                 result.add(new HashSet<>(current));
//             }
//             return;
//         }
        
//         for (String userId: user_id) {
//             if (!isSame(banned_id[depth], userId) || current.contains(userId)) {
//                 continue;
//             }
//             current.add(userId);
//             dfs(current, user_id, banned_id, depth + 1);
//             current.remove(userId);
//         }
//     }
    
//     public boolean isSame(String bannedId, String userId) {
//         if (bannedId.length() != userId.length()) return false;
//         for (int i = 0; i < bannedId.length(); i++) {
//             if (bannedId.charAt(i) == '*') continue;
//             if (bannedId.charAt(i) != userId.charAt(i)) return false;
//         } 
//         return true;
//     }
}