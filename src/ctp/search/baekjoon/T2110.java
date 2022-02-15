package ctp.search.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2110
// 이진 탐색 / 난이도 중
public class T2110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] home = new int[N];
        for (int i=0; i<N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        int answer = solution(N, C, home);
        System.out.println(answer);
    }

    private static int solution(int n, int c, int[] home) {
        // 오름차순으로 정렬
        Arrays.sort(home);

        int start = 1;  // 최소 거리르 가질 수 있는 최소값
        int end = home[n-1] - home[0] + 1; //최소 거리를 가질 수 있는 최대값

        while (start < end) {   // Upper Bound 형식
            int mid = (start + end) / 2;

            // 설치 가능한 공유기 개수 찾기
            // 첫번쨰 집은 무조건 설치한다고 가정
            int count = 1;
            int lastLoc = home[0];

            for (int i=1; i<home.length; i++) {
                int currLoc = home[i];

                // 현재 탐색하는 집의 위치와 직전에 설치했던 집의 위치가 거리가
                // 최소 거리보다 크거나 같을 때 공유기 개수를 늘려주고
                // 마지막 설치 위치 갱신
                if (currLoc - lastLoc >= mid) {
                    count++;
                    lastLoc = currLoc;
                }
            }

            // mid 거리에 대해 설치 가능한 개수가 c개보다 작으면
            // 거리를 좁혀야하기 떄문에 end를 줄인다.
            if (count < c) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        // Upper Bound는 탐색 값을 초과하는 첫번쨰 값을 가리키므로
        // 1을 뺴준 값이 조건식을 만족하는 최댓값이 된다.
        return start-1;
    }
}

// https://st-lab.tistory.com/277