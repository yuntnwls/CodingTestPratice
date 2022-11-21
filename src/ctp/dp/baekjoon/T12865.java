package ctp.dp.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12865
// DP, Knapsack 알고리즘(짐을 쪼갤 수 없는 배낭 문제)
public class T12865 {

    private static int N, K;
    private static Integer[][] dp;
    private static int[] W;
    private static int[] V;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new Integer[N+1][K+1];
        W = new int[N+1];
        V = new int[N+1];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
        
        int answer = knapsack(N-1, K);
        System.out.println(answer);
    }

    private static int knapsack(int i, int k) {
        // i가 0미만, 범위 밖이 된다면 return
        if (i < 0)
            return 0;

        if (dp[i][k] == null) {
            if (W[i] > k) {
                // 현재 물건 i를 추가로 담지 못하는 경우 (이전 i값 탐색)
                dp[i][k] = knapsack(i-1, k);
            }
            else {
                // 현재 물건을 담을 수 있는 경우
                // 이전 i값과 이전 i값에 대한 k-W[i]의 값 + 현재 가치(V[i]) 중 큰 값을 저장
                dp[i][k] = Math.max(knapsack(i-1, k), knapsack(i-1, k-W[i]) + V[i]);
            }
        }

        return dp[i][k];
    }
}

// https://st-lab.tistory.com/141