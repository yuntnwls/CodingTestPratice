package ctp.programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://programmers.co.kr/learn/courses/30/lessons/62048?language=java
// LV2 멀쩡한 사각형
public class T62048 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int w = Integer.parseInt(br.readLine());
        int h = Integer.parseInt(br.readLine());

        long answer = solution(w, h);
        System.out.println(answer);
    }

    public static long solution(int w, int h) {
        long gcd = getGCD(w, h);

        return ((long)w * h) - (((w/gcd) + (h/gcd) - 1) * gcd);
    }

    // 유클리드 호제법 연산
    private static int getGCD(int n, int m) {
        while (m != 0) {
            int r = n % m;

            n = m;
            m = r;
        }
        return n;
    }
}

/**
 * Input
 * 8
 * 12
 * Output
 * 80
 */