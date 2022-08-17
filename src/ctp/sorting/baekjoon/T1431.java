package ctp.sorting.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class T1431 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            list.add(br.readLine());
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    int sumOfA = GetSum(o1);
                    int sumOfB = GetSum(o2);
                    if (sumOfA == sumOfB)
                        return o1.compareTo(o2);
                    else
                        return sumOfA - sumOfB;
                } else {
                    return o1.length() - o2.length();
                }
            }
        });

        for (String s : list) {
            System.out.println(s);
        }
    }

    private static int GetSum(String input) {
        int result = 0;
        String[] split = input.split("");
        for (String s : split) {
            try {
                result += Integer.parseInt(s);
            } catch (Exception e) {
            }
        }
        return result;
    }
}
