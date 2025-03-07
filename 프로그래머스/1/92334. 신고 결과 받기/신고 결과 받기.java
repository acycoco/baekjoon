import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        //한번에 한명의 유저 신고
        //신고 횟수에 제한 없음
        //한 유저를 여러 번 신고할 수 있지만, 1회로 처리됨 (같은 요청은 1회로 카운트해야됨)
        //k번 이상 신고 되면 정지되고 메일 발송됨(신고한 모든 유저에게)
        //각 유저별 처리 결과 메일을 받은 횟수(내가 신고한 유저가 정지된 횟수)
        
        //report 카운트 해야됨 -> set에 넣어서 중복 없애기
        //int[][] 신고한 id -> 신고 당한 id 카운트하기 
        //정지당한 유저(k보다 큰 유저를 구해서) 신고한 id들 카운트 + 1
        int n = id_list.length;
        Map<String, Integer> idIndex = new HashMap<>();
        for(int i = 0; i < n; i++) {
            idIndex.put(id_list[i], i);
        }
        Set<String> reportSet = new HashSet<>();
        for(String rep: report)  {
            reportSet.add(rep);
        }
        
        int[][] table = new int[n][n];
        int[] count = new int[n];
        for(String rep: reportSet) {
            String[] sp = rep.split(" ");
            table[idIndex.get(sp[0])][idIndex.get(sp[1])]++;
            count[idIndex.get(sp[1])]++;
        }
        
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            if(count[i] >= k) {
                for(int j = 0; j < n; j++) {
                    if(table[j][i] >= 1) {
                        answer[j]++;
                    }
                }
            }
        }

        return answer;
    }
}