package ctp.programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Lv2 튜플
// https://school.programmers.co.kr/learn/courses/30/lessons/64065
public class T64065 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] answer = solution(s);
        Arrays.stream(answer).forEach(x -> System.out.print(x + ","));
    }

    private static int[] solution(String s) {
        List<String[]> list = new ArrayList<>();
        String[] split = s.substring(1,s.length()-1).split("},");

        // 문자열을 Array로 변환
        for (String str : split) {
            str = str.replaceAll("\\{", "").replaceAll("}","");
            String[] strSplit = str.split(",");
            list.add(strSplit);
        }

        // 원소 수가 작은 목록대로 정렬
        Collections.sort(list, (s1, s2) -> s1.length - s2.length);

        List<String> temp = new ArrayList<>();
        for (String[] strArr : list) {
            for (String str : strArr) {
                if (!temp.contains(str))
                    temp.add(str);
            }
        }

        int[] answer = new int[temp.size()];
        for (int i=0; i<temp.size(); i++) {
            answer[i] = Integer.parseInt(temp.get(i));
        }
        return answer;
    }
}

/**
 * Input
 * {{2},{2,1},{2,1,3},{2,1,3,4}}
 * Output
 * 2,1,3,4
 *
 * Input
 * {{1,2,3},{2,1},{1,2,4,3},{2}}
 * Output
 * 2,1,3,4
 *
 * Input
 * {{20,111},{111}}
 * Output
 * 111,20
 *
 * Input
 * {{123}}
 * Output
 * 123
 *
 * Input
 * {{4,2,3},{3},{2,3,4,1},{2,3}}
 * Output
 * 3,2,4,1
 */