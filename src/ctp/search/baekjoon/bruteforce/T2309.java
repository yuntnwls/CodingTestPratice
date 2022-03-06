package ctp.search.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2309
// Brute Force(브루트 포스) : 무식하게 푼다, 가능한 방법을 전부 만들어 보는 알고리즘, 완전탐색(exhaustive search)라고도 함
public class T2309 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 9;  // 총 난쟁이의 수
        int r = 2;  // 찾을 난쟁이 수
        int[] height = new int[n];
        int total = 0;
        for (int i=0; i<n; i++) {
            height[i] = Integer.parseInt(br.readLine());
            total += height[i];
        }
        solution(height, total-100);
    }

    private static void solution(int[] height, int sum) {
        Arrays.sort(height);

        int left = 0;
        int right = height.length-1;

        while (left < right) {
            int temp = height[left] + height[right];

            if (temp == sum) {
                height[left] = 0;
                height[right] = 0;
            }

            if (temp > sum) {
                right--;
            } else {
                left++;
            }
        }

        for (int i=0; i< height.length; i++) {
            if (height[i] > 0)
                System.out.println(height[i]);
        }
    }
}
