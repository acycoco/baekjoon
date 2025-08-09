import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Problem[] problems;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        problems = new Problem[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int lamen = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(deadLine, lamen);
        }

        Arrays.sort(problems);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Problem problem: problems) {
            pq.offer(problem.ramen);
            
            if (pq.size() > problem.deadline) {
                pq.poll();
            }
        }
        
        int answer = 0;
        for (int r: pq) answer += r;
        System.out.println(answer);
    }

    public static class Problem implements Comparable<Problem> {
        int deadline;
        int ramen;

        public Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem other) {
            return this.deadline - other.deadline; //데드라인 기준 오름차순.
        }

    }
}
