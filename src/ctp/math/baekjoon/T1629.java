package ctp.math.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class T1629 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int answer = multiply(A, B, C);
        System.out.println(answer);
    }


    public static int multiply(int a, int b, int c) {
        if (b == 0)
            return 1;

        int n = multiply(a, b/2, c);
        int temp = n * n % c;

        if (b%2 == 0)
            return temp;
        else
            return temp*a%c;
    }
}
