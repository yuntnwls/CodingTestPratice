package ctp.programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://programmers.co.kr/learn/courses/30/lessons/12899
// LV1 124 나라의 숫자
public class T12899 {

    private static String[] numbers = {"4", "1", "2"};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String answer = solution(n);
        System.out.println(answer);
    }

    public static String solution(int n) {
        String answer = "";

        while (n > 0)
        {
            answer += numbers[n % 3];
            if (n%3 == 0) {
                // 나누어 떨어지면 몫에서 1을 빼기
                n = (n/3) - 1;
            } else {
                n = n/3;
            }
        }
        StringBuffer sb =new StringBuffer(answer);
        String reservedAnswer = sb.reverse().toString();
        return reservedAnswer;
    }
}

/**
 * Input
 * 1
 * Output
 * 1
 *
 * Input
 * 2
 * Output
 * 2
 *
 * Input
 * 3
 * Output
 * 4
 *
 * Input
 * 4
 * Output
 * 11
 */