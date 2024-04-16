import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;
    static int nodeN;
    static int edgeN;
    static int result; //최소 스패닝 트리의 가중치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeN = Integer.parseInt(st.nextToken());
        edgeN = Integer.parseInt(st.nextToken());

        for (int i = 0; i < edgeN; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, weight));
        }

        parent = new int[nodeN + 1];
        //유니온파인드 연산을 위한 초기화
        for (int i = 1; i < nodeN + 1; i++) {
            parent[i] = i;
        }

        mst();
        System.out.println(result);
    }

    public static void mst() {
        Collections.sort(edges);//오름차순 정렬

        for (Edge edge : edges) {
            if (sameParent(edge.start, edge.end)) { //같은 그룹이면 그 에지는 사용하지 않음
                continue;
            }
            union(edge.start, edge.end); //같은 그룹으로 만들어준다.
            result += edge.weight;
        }
    }

    public static boolean sameParent(int a, int b) {
        return find(a) == find(b);
    }
    public static int find(int a) {
        if (parent[a] == a) {
            return a; //대표노드가 자기 자신이면 리턴
        }
        return parent[a] = find(parent[a]); //대표노드를 업데이트하면서 같은 그룹의 제일 대표노드를 검색
    }

    public static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA < findB) {
            parent[findB] = findA; 
        } else {
            parent[findA] = findB;
        }
    }
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) { //오름차순 정렬
            return (this.weight - o.weight);
        }
    }
}
