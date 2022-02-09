package ctp.greedy.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1074
// 재귀 : 난이도 중
public class T1074 {

    private static int N, R, C;
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        solution((int)Math.pow(2, N), 0, 0);
    }

    private static void solution(int n, int r, int c) {
        if (r == R && c == C) {
            System.out.println(answer);
            System.exit(0);
        }

        // 찾기 원하는 좌표 밖에 있는 사각형은 무조건 다 탐색하므로 굳이 탐색하지 않고 그 칸만큼만 +해주면 됨
        if (r <= R && (r+n) > R && c <= C && (c+n) > C) {
            int temp = n/2;
            solution(temp, r, c);
            solution(temp, r, c+temp);
            solution(temp, r+temp, c);
            solution(temp, r+temp, c+temp);
        } else {
            answer += n*n;
        }

        // 모든 좌표를 전부 돌게되면 시간복잡도에 걸려 시간 초과 에러 발생
//        if (n == 2) {
//            // 왼쪽 위
//            if ((r == R) && (c == C)) {
//                System.out.println(answer);
//                System.exit(0);
//            }
//            answer += 1;
//            // 오른쪽 위
//            if ((r == R) && (c+1 == C)) {
//                System.out.println(answer);
//                System.exit(0);
//            }
//            answer += 1;
//            // 왼쪽 아래
//            if ((r+1 == R) && (c == C)) {
//                System.out.println(answer);
//                System.exit(0);
//            }
//            answer += 1;
//            // 오른쪽 아래
//            if ((r+1 == R) && (c+1 == C)) {
//                System.out.println(answer);
//                System.exit(0);
//            }
//            answer += 1;
//            return;
//        }
//        // 재귀를 실행할 떄도 Z 모양으로 실행
//        // 가장 큰 형태의 Z부터 이동하므로 n/2만큼 이동
//        solution(n/2, r, c);
//        solution(n/2, r, c+n/2);
//        solution(n/2, r+n/2, c);
//        solution(n/2, r+n/2, c+n/2);
    }
}

// https://girawhale.tistory.com/28