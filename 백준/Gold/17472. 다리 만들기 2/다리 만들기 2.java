import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] graph;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;
    static int sequence = 1;
    static int result;
    static Queue<Point> queue = new LinkedList<>();
    static boolean[][] visited;
    static ArrayList<ArrayList<Point>> ilands = new ArrayList<>(); //섬들의 좌표
    static final int[] x = new int[]{-1, 0, 1, 0};
    static final int[] y = new int[]{0, -1, 0, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(st.nextToken());
                graph[i][j] = value;
            }
        }

        visited = new boolean[n][m];
        //대표노드 찾기
        //1인 경우 상하좌우를 살피고 섬이 몇개인지, 섬에 넘버링하기, 섬 리스트 반환
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    ArrayList<Point> nowIlands = bfs(new Point(i, j));
                    ilands.add(nowIlands);
                    sequence++;
                }
            }
        }

        //엣지 찾기
        findEdge();

        //대표노드배열 초기화
        parent = new int[sequence]; //1부터 매겨짐
        for (int i = 1; i < sequence; i++) {
            parent[i] = i;
        }

        //엣지리스트 정렬
        Collections.sort(edges);

        int containedEdge = 0;

        for (Edge edge : edges) {
            if (sameParent(edge.startIland, edge.endIland)) {
                continue;
            }
            result += edge.weight;
            union(edge.startIland, edge.endIland);
            containedEdge++;
            if (containedEdge == sequence - 2) {
                break;
            }
        }

        if (containedEdge == sequence - 2) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    } 

    //섬찾기, 넘버링, 섬리스트 저장
    public static ArrayList<Point> bfs(Point now) {
        ArrayList<Point> nowIlands = new ArrayList<>();
        queue = new LinkedList<>();
        queue.add(now);
        visited[now.x][now.y] = true;
        nowIlands.add(now);
        graph[now.x][now.y] = sequence;

        while(!queue.isEmpty()) {
            Point poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int goX = x[i];
                int goY = y[i];
                int newX = poll.x + goX;
                int newY = poll.y + goY;
                while (isInRange(newX, newY)) {
                    if (!visited[newX][newY]) {
                        if (graph[newX][newY] == 0) { //바다가 나타나면 끝
                            break;
                        }
                        //섬이면 방문
                        visited[newX][newY] = true;
                        graph[newX][newY] = sequence;
                        Point nextIland = new Point(newX, newY);
                        queue.add(nextIland);
                        nowIlands.add(nextIland);
                    } else { //한쪽 방향으로 다봄.
                        break;
                    }
                    newX += goX;
                    newY += goY;
                }

            }
        }
        return nowIlands;
    }

    public static void findEdge() {
        for (ArrayList<Point> iland : ilands) {
            Point first = iland.get(0);
            int nowIland = graph[first.x][first.y];
            for (Point now : iland) {
                for (int i = 0; i < 4; i++) {
                    int goX = x[i];
                    int goY = y[i];
                    int nextX = now.x + goX;
                    int nextY = now.y + goY;
                    int length = 0;
                    //범위안에서 같은 방향으로 계속가기
                    while (isInRange(nextX, nextY)) {
                        if (graph[nextX][nextY] == nowIland) { //같은 섬이면 안됨
                            break;
                        } else if (graph[nextX][nextY] != 0) {//다른 섬이면 길이의 개수 구하기
                            if (length < 2) {
                                break;
                            }
                            //길이 2이상이면 엣지리스트에 추가하고 그만
                            edges.add(new Edge(nowIland, graph[nextX][nextY], length));
                            break;
                        } else {//바다면 길이 더하기
                            length++;
                        }
                        nextX += goX;
                        nextY += goY;
                    }
                }
            }
        }
    }
    public static boolean sameParent(int a, int b) {
        return find(a) == find(b);
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA < findB) {
            parent[findB] = findA;
        }

        if (findB < findA) {
            parent[findA] = findB;
        }
    }
    public static boolean isInRange(int nowX, int nowY) {
        return nowX >= 0 && nowX < n && nowY >= 0 && nowY < m;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int startIland;
        int endIland;
        int weight;

        public Edge(int startIland, int endIland, int weight) {
            this.startIland = startIland;
            this.endIland = endIland;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
