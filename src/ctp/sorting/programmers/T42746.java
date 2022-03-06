package ctp.sorting.programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://programmers.co.kr/learn/courses/30/lessons/42746
// 정렬 / Lv2 / 가장 큰 수
public class T42746 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];
        for (int i=0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        String answer = Solution(numbers);
        System.out.println(answer);
    }

    private static String Solution(int[] numbers) {
        int len = numbers.length;
        String[] strArr = new String[len];
        // 1. 숫자를 문자열로 변경
        for (int i=0; i<len; i++) {
            strArr[i] = Integer.toString(numbers[i]);
        }

        // 2. 문자열 정렬
        // 두 원소간의 결합을 보고 사전 순서상 더 뒤에있는 숫자가 앞으로 오도록 정렬
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);    // 내림차순
            }
        });

        String answer = "";
        for (int i=0; i<len; i++) {
            answer += strArr[i];
        }
        return answer;
    }
}
