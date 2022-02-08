package ctp.greedy.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1461
// 난이도 중
public class T1461 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] split = br.readLine().split(" ");

        PriorityQueue<Integer> mQueue = new PriorityQueue<>((p1, p2) -> p2 - p1);
        PriorityQueue<Integer> pQueue = new PriorityQueue<>((p1, p2) -> p2 - p1);

        // +,- 기준으로 우선순위 큐에 담기
        for (int i=0; i<N; i++) {
            int temp = Integer.parseInt(split[i]);
            if (temp < 0) {
                mQueue.add(temp*(-1));
            } else {
                pQueue.add(temp);
            }
        }

        int answer = move(M, mQueue, pQueue);
        System.out.println(answer);
    }

    private static int move(int M, PriorityQueue<Integer> mQueue, PriorityQueue<Integer> pQueue) {
        // 가장 멀리있는 책 찾기
        int max = 0;
        if (mQueue.isEmpty()) {
            max = pQueue.peek();
        } else if (pQueue.isEmpty()) {
            max = mQueue.peek();
        } else {
            max = Math.max(mQueue.peek(), pQueue.peek());
        }

        // 모두 왕복한다고 생각하고 이동 수 구하기
        int answer = 0;
        answer += getMoveCount(M, mQueue);
        answer += getMoveCount(M, pQueue);
        answer -= max;
        return answer;
    }

    private static int getMoveCount(int M, PriorityQueue<Integer> queue) {
        int result = 0;
        while (queue.peek() != null) {
            int move = queue.poll();
            for (int i=1; i<M; i++) {
                if (queue.isEmpty()) break;
                queue.poll();
            }
            result += (move*2);
        }
        return result;
    }
}

//https://steady-coding.tistory.com/279
