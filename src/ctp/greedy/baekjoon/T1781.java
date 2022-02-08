package ctp.greedy.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1781
// 난이도 중
public class T1781 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        List<Test> testList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            testList.add(new Test(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int answer = solution(N, testList);
        System.out.println(answer);
    }

    private static int solution(int N, List<Test> testList) {
        Collections.sort(testList);

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Test test : testList) {
            queue.add(test.cup);
            if (queue.size() > test.deadline)
                queue.poll();
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            answer += queue.poll();
        }
        return answer;
    }

    static class Test implements Comparable<Test> {
        int deadline;
        int cup;
        public Test(int deadline, int cup) {
            this.deadline = deadline;
            this.cup = cup;
        }

        @Override
        public int compareTo(Test o) {
            return this.deadline - o.deadline;
        }
    }
}
