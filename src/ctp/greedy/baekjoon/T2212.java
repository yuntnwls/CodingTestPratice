package ctp.greedy.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// https://www.acmicpc.net/problem/2212
// 난이도 하
public class T2212 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 센서 개수
        int K = Integer.parseInt(br.readLine());    // 집중국 개수
        String[] split = br.readLine().split(" ");
        int[] sensors = new int[N];
        for (int i=0; i<N; i++) {
            sensors[i] = Integer.parseInt(split[i]);
        }

        int answer = solution(N, K, sensors);
        System.out.println(answer);
    }

    private static int solution(int N, int K, int[] sensors) {
        // 센서 수 < 기지국 수의 경우 거리차는 0
        if (N <= K) return 0;

        // 오름차순 정렬
        Arrays.sort(sensors);

        // 센서 거리의 차이를 담은 배열
        Integer[] diffArr = new Integer[N-1];
        for (int i=0; i<N-1; i++) {
            diffArr[i] = sensors[i+1] - sensors[i];
        }

        // 센서 거리를 내림차순으로 정렬
        Arrays.sort(diffArr, Collections.reverseOrder());

        int answer = 0;
        // K개의 묶음으로 만들어야하므로 간격이 가장 큰 K-1개를 제외하고 합을 구함
        // K개의 기지국을 만들고 남은 센서간의 거리의 합
        for (int i=K-1; i<N-1; i++) {
            answer += diffArr[i];
        }
        return answer;
    }
}

// https://velog.io/@jkh9615/알고리즘-백준-11000-강의실-배정-Java-wskzqzk6