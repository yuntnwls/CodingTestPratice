package ctp.programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/86051
// LV1
public class T86051 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(",");
        int[] numbers = new int[split.length];
        for (int i=0; i<split.length; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }

        int answer = solution(numbers);
        System.out.println(answer);
    }

    public static int solution(int[] numbers) {
        int answer = 0;
        Arrays.sort(numbers);
        int index = 0;
        for (int i=0; i<10; i++)
        {
            if (index >= numbers.length || numbers[index] != i) {
                answer += i;
            } else {
                index++;
            }
        }
        return answer;
    }
}

/**
 * Input
 * 1,2,3,4,6,7,8,0
 * Output
 * 14
 *
 * Input
 * 5,8,4,0,6,7,9
 * Output
 * 6
 */