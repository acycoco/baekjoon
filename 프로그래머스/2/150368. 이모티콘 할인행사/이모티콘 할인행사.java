import java.util.*;

class Solution {
    // 최종 결과를 저장할 변수 (실시간 갱신)
    // answer[0]: 가입자 수, answer[1]: 매출액
    int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        // DFS를 통해 모든 할인율 조합을 탐색 시작
        findBestCase(0, new int[emoticons.length], users, emoticons);
        return answer;
    }

    // DFS로 모든 할인율 조합을 생성하고 최적해를 찾는 함수
    private void findBestCase(int depth, int[] discounts, int[][] users, int[] emoticons) {
        // 1. 모든 이모티콘의 할인율이 결정되었을 때 (재귀 종료 조건)
        if (depth == emoticons.length) {
            calculateResult(discounts, users, emoticons);
            return;
        }

        // 2. 현재 이모티콘에 10, 20, 30, 40% 할인율을 각각 적용해본다.
        for (int rate = 10; rate <= 40; rate += 10) {
            discounts[depth] = rate;
            findBestCase(depth + 1, discounts, users, emoticons);
        }
    }

    // 결정된 할인율 조합으로 가입자 수와 판매액을 계산하고, 최적해를 갱신하는 함수
    private void calculateResult(int[] discounts, int[][] users, int[] emoticons) {
        int currentSubscribers = 0;
        int currentSales = 0;

        // 각 사용자별로 구매 결과 시뮬레이션
        for (int[] user : users) {
            int userDiscountRate = user[0];
            int userPriceThreshold = user[1];
            int totalPurchase = 0;

            // 각 이모티콘을 살지 말지 결정
            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= userDiscountRate) {
                    totalPurchase += emoticons[i] * (100 - discounts[i]) / 100;
                }
            }

            // 구매액이 기준을 넘으면 플러스 서비스 가입
            if (totalPurchase >= userPriceThreshold) {
                currentSubscribers++;
            } else {
                currentSales += totalPurchase;
            }
        }

        // 3. 현재 결과와 지금까지의 최적해를 비교하여 갱신
        if (currentSubscribers > answer[0]) {
            answer[0] = currentSubscribers;
            answer[1] = currentSales;
        } else if (currentSubscribers == answer[0] && currentSales > answer[1]) {
            answer[1] = currentSales;
        }
    }
}