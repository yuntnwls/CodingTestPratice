package ctp.sorting.programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://programmers.co.kr/learn/courses/30/lessons/42747
// 정렬 / Lv2 / H-Index
public class T42747 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(", ");
        int[] citations = new int[split.length];
        for (int i=0; i<citations.length; i++) {
            citations[i] = Integer.parseInt(split[i]);
        }
        int answer = solution(citations);
    }

    private static int solution(int[] citations) {
        // 오름차순으로 정렬
        Arrays.sort(citations);

        int answer = 0;
        int index = citations.length;   // 인용된 논문 수
        for (int i=0; i<citations.length; i++) {
            if (citations[i] >= index) {
                answer = index;
                break;
            }
            index--;
        }
        return answer;
    }
}
