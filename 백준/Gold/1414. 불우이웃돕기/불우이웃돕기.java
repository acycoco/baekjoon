import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static List<Edge> edges = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int sum = 0;
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                char weightC = input.charAt(j);
                int weight = calculateWeight(weightC);
                sum += weight;
                if (i == j || weight == 0) {
                    continue;
                }
                //엣지 리스트 저장
                edges.add(new Edge(i, j, weight));
            }
        }

        //초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        //유니온 파인드하면서 mst구하기
        //엣지리스트 정렬
        Collections.sort(edges);

        int mstEdge = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (sameParent(edge.start, edge.end)) {
                continue;
            }
            union(edge.start, edge.end);
            count++;
            mstEdge += edge.weight;
            if (count == n - 1) {
                break;
            }
        }

        if (count != n - 1) {
            System.out.println(-1);
        } else {
            System.out.println(sum - mstEdge);
        }
    }

    private static boolean sameParent(int a, int b) {
        return find(a) == find(b);
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA < findB) {
            parent[findB] = findA;
        }

        if (findB < findA) {
            parent[findA] = findB;
        }
    }
    private static int calculateWeight(char weight) {
        if ('a' <= weight && weight <= 'z') {
            return weight - 'a' + 1;
        } else if ('A' <= weight && weight <= 'Z') {
            return weight - 'A' + 27;
        }
        return 0;
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
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
