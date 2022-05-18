package ctp.search.programmers.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://programmers.co.kr/learn/courses/30/lessons/43162
// DFS / Lv3
public class T43162 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] computers = new int[n][3];
        for (int i=0; i<n; i++) {
            String[] split = br.readLine().split(", ");
            for (int j=0; j<split.length; j++) {
                computers[i][j] = Integer.parseInt(split[j]);
            }
        }

        int answer = solution(n, computers);
        System.out.println(answer);
    }

    public static int solution(int n, int[][] computers) {
        boolean[] isVisited = new boolean[n];
        int answer = 0;

        for (int i=0; i<n; i++) {
            if (isVisited[i] == false)
            {
                dfs(computers, i, isVisited);
                answer++;
            }
        }

        return answer;
    }

    private static void dfs(int[][] computers, int index, boolean[] isVisited) {
        isVisited[index] = true;

        for (int i=0; i<computers.length; i++)
        {
            if (computers[index][i] == 1 && !isVisited[i])
            {
                dfs(computers, i, isVisited);
            }
        }
    }
}

/**
 Input
 3
 1, 1, 0
 1, 1, 0
 0, 0, 1

 Output
 2

 Input
 3
 1, 1, 0
 1, 1, 1
 0, 1, 1

 Output
 1
 */