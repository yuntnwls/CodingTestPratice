package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 순열
// 서로 다른 n개 중 r개를 골라 순서를 고려해서 나열한 경우의 수 ({1,2} != {2,1})
// n부터 시작해서 숫자를 하나씩 줄여나가 곱함
// nPr = n(n-1)(n-2)...(n-r+1)
// https://www.acmicpc.net/problem/10974
public class Permutation {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1부터 N까지의 수로 이루어진 수열을 사전순으로 출력
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 순열을 만들 배열
        for (int i=0; i<N; i++)
            arr[i] = i+1;

        int[] output = new int[N]; // 순열을 만든 후 배열
        boolean[] visited = new boolean[N]; // 방문 체크


        // 1. Swap 을 이용한 순열
        // 순서없이 n개 중에서 r개를 뽑는 경우
        permutation_swap(arr, 0, N, 3);
        /*
        1 2 3
        1 3 2
        2 1 3
        2 3 1
        3 2 1 => 순서대로 뽑지 않음
        3 1 2
        */

        // 2. Visited 배열을 이용한 순열
        // 순서를 지키면서 n개 중에서 r개를 뽑는 경우
        permutation_visit(arr, output, visited, 0, N, 3);
        /*
        1 2 3
        1 3 2
        2 1 3
        2 3 1
        3 1 2 => 순서대로 뽑음
        3 2 1
        */
    }

    private static void permutation_visit(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        // DFS를 돌면서 모든 인덱스를 방문하여 output에 값을 넣는다.
        // depth == output에 들어간 숫자의 길이
        if (depth == r) {
            print(output, r);
            return;
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                // 값이 들어가지 않았다면 output에 값을 넣기
                visited[i] = true;
                output[depth] = arr[i];
                permutation_visit(arr, output, visited, depth+1, n, r);
                visited[i] = false;
            }
        }
    }

    private static void permutation_swap(int[] arr, int depth, int n, int r) {
        if (depth == r) {
            print(arr, r);
            return;
        }
        
        for (int i=depth; i<n; i++) {
            swap(arr, depth, i);
            permutation_swap(arr, depth+1, n, r);
            swap(arr, depth, i);
        }
    }

    private static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] =temp;
    }

    private static void print(int[] arr, int r) {
        for (int i=0; i<r; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
// https://bcp0109.tistory.com/14
// https://blog.naver.com/ssang8417/222147007019