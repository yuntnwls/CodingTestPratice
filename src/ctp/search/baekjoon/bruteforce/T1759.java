package ctp.search.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1759
// 백트래킹 / 난이도 중
public class T1759 {

    private static int L, C;
    private static char[] alphabets;
    private static List<Character> vows;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        String line = br.readLine().replaceAll(" ", "");
        alphabets = line.toCharArray();
        Arrays.sort(alphabets);

        vows = new ArrayList<Character>();
        vows.add('a');
        vows.add('e');
        vows.add('i');
        vows.add('o');
        vows.add('u');

        boolean[] visited = new boolean[C];
        dfs(visited, 0, L);
    }

    private static void dfs(boolean[] visited, int start, int n) {
        if (n == 0) {
            print(visited);
            return;
        }

        for (int i=start; i<C; i++) {
            visited[i] = true;
            dfs(visited, i+1, n-1);
            visited[i] = false;
        }
    }

    private static void print(boolean[] visited) {
        int vowCnt = 0;
        String answer = "";
        for (int i=0; i<visited.length; i++) {
            if (visited[i]) {
                answer += alphabets[i];
                if (vows.contains(alphabets[i]))
                    vowCnt++;
            }
        }
        if (vowCnt >= 1 && vowCnt <= L-2){
            System.out.println(answer);
        }
    }
}
