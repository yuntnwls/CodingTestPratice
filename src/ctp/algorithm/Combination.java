package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 조합 : 서로 다른 n개의 원소 중에서 순서에 상관없이 r개를 선택
// 순열과는 다르게 뽑은 r개가 순서만 다른 경우 중복으로 인지
// https://bcp0109.tistory.com/15?category=848939
public class Combination {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int R = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 조합을 뽑아낼 배열
        for (int i = 0; i < N; i++)
            arr[i] = i + 1;

        int[] output = new int[N]; // 조합에 뽑혔는지 체크하는 배열
        boolean[] visited = new boolean[N]; // 방문 체크

        // 백트래킹을 이용
        combination_backtracking(arr, visited, 0, N, R);

        Arrays.fill(visited, false);

        // 재귀를 이용
        combination_recursion(arr, visited, 0, N, R);
    }

    private static void combination_backtracking(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }
        // start 인덱스를 기준으로 start보다 작으면 뽑을 후보에서 제외, start보다 크면 뽑을 후보에 포함
        for (int i=start; i<n; i++) {
            visited[i] = true;  // 현재 인덱스를 선택하는 경우
            combination_backtracking(arr, visited, i+1, n, r-1);
            visited[i] = false; // 현재 인덱스를 선택하지 않는 경우
        }
    }

    private static void combination_recursion(int[] arr, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }
        if (depth == n) {
            return;
        }
        // 현재 인덱스를 선택하는 경우
        visited[depth] = true;
        combination_recursion(arr, visited, depth+1, n, r-1);

        // 현재 인덱스를 선택하지 않은 경우
        visited[depth] = false;
        combination_recursion(arr, visited, depth+1, n, r);
    }

    private static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}
