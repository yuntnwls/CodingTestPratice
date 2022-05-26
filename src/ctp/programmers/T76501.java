package ctp.programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://programmers.co.kr/learn/courses/30/lessons/76501
// LV1
public class T76501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().trim().split(",");
        int[] absolutes = new int[split.length];
        for (int i=0; i<split.length; i++) {
            absolutes[i] = Integer.parseInt(split[i]);
        }

        split = br.readLine().trim().split(",");
        boolean[] signs = new boolean[split.length];
        for (int i=0; i<split.length; i++) {
            signs[i] = Boolean.parseBoolean(split[i]);
        }

        int answer = solution(absolutes, signs);
        System.out.println(answer);
    }

    public static int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i=0; i<absolutes.length; i++)
        {
            if (signs[i])
                answer += absolutes[i];
            else
                answer -= absolutes[i];
        }
        return answer;
    }
}

/**
 * Input
 * 4,7,12
 * true,false,true
 * Output
 * 9
 *
 * Input
 * 1,2,3
 * false,false,true
 * Output
 * 0
 */
