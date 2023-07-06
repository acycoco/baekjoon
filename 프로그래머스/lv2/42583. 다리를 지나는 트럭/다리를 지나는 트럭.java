import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();  // 다리를 표현하는 큐
        int time = 0;  // 경과 시간
        int currentWeight = 0;  // 현재 다리 위의 트럭 무게 합

        // 트럭의 인덱스를 표현하는 변수
        int truckIndex = 0;
        
        while (truckIndex < truck_weights.length) {
            time++;

            // 다리를 건너는 트럭이 다리 길이에 도달하면 큐에서 제거하고 무게를 갱신합니다.
            if (bridge.size() == bridge_length) {
                int passedTruck = bridge.poll();
                currentWeight -= passedTruck;
            }

            // 새로운 트럭이 다리에 올라갈 수 있는지 확인하고 올립니다.
            int newTruck = truck_weights[truckIndex];
            if (currentWeight + newTruck <= weight) {
                bridge.offer(newTruck);
                currentWeight += newTruck;
                truckIndex++;
            } else {
                bridge.offer(0);  // 무게를 0으로 표시하여 트럭이 올라가지 못하게 합니다.
            }
        }

        // 마지막 트럭이 다리를 지나가는 시간을 추가로 계산합니다.
        time += bridge_length;

        return time;
    }
}