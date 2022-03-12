package ctp.search.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1697
// BFS / 난이도 하
public class T1697 {

    private static int MAX = 100001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = solution(N, K);
        System.out.println(answer);
    }

    private static int solution(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        // 방문여부 확인 및 이동 시간 저장
        int[] visit = new int[MAX];

        queue.add(n);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == k) {
                return visit[now];
            }

            // 1초로 갈 수 있는 모든 위치 확인
            int left = now-1;
            int right = now+1;
            int jump = now*2;
            // 이전에 방문했던 곳인지 확인
            if (left >= 0 && left < MAX && visit[left] == 0) {
                queue.add(left);
                visit[left] = visit[now] + 1;
            }
            if (right >= 0 && right < MAX && visit[right] == 0) {
                queue.add(right);
                visit[right] = visit[now] + 1;
            }
            if (jump >= 0 && jump < MAX && visit[jump] == 0) {
                queue.add(jump);
                visit[jump] = visit[now] + 1;
            }
        }

        return 0;
    }
}
