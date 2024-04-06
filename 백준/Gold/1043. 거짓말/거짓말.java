
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int peopleN;
    static int partyN;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        //같이 파티를 참여한 사람끼리 union연산
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList<>();
        peopleN = Integer.parseInt(st.nextToken());
        parent = new int[peopleN + 1];
        for (int i = 0; i < peopleN; i++) {
            parent[i] = i;
        }
        partyN = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int truePeopleN = Integer.parseInt(st.nextToken());
        if (truePeopleN == 0) {
            System.out.println(partyN);
            return; //진실을 아는 사람이 0명이면 파티수를 출력하고 종료
        }
        int[] truePeople = new int[truePeopleN];
        for (int i = 0; i < truePeopleN; i++) {
            truePeople[i] = Integer.parseInt(st.nextToken());
            if (i != 0) {
                union(truePeople[i - 1], truePeople[i]); //진실을 아는 사람들을 같은 집합에 묶기
            }
        }
        for (int i = 0; i < partyN; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < partyN; i++) {
            st = new StringTokenizer(br.readLine());
            int participateN = Integer.parseInt(st.nextToken());
            for (int j = 0; j < participateN; j++) {
                int people = Integer.parseInt(st.nextToken());
                graph.get(i).add(people);
                if (j != 0) { //파티 참여자가 2명이상인 경우부터 전 참가자와 유니온 연산
                    union(graph.get(i).get(j - 1), graph.get(i).get(j));
                }
            }
        }
        int findTruePeople = find(truePeople[0]); //진실을 아는 사람 아무나 한명의 대표노드를 find
        int result = 0;
        for (int i = 0; i < partyN; i++) {
            boolean canLie = true;
            for (int people : graph.get(i)) {
                if (find(people) == findTruePeople) {
                    canLie = false;
                }
            }
            if (canLie) {
                result++;
            }
        }

        System.out.println(result);
        //연산을 완료한 후 파티마다 find연산을 실시해서 진실을 아는 사람과 같은 집합에 있으면 파티 제외
    }

    private static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA != findB) {
            parent[findB] = findA;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
