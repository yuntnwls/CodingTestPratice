package ctp.sorting.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class T10989 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] array = new int[10001];
        for (int i=0; i<N; i++) {
            int iValue = Integer.parseInt(br.readLine());
            array[iValue]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<array.length; i++) {
            if (array[i] > 0){
                for (int t=0; t<array[i]; t++)
                    sb.append(i).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
