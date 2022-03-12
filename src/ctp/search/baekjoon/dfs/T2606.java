package ctp.search.baekjoon.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2606
// DFS / 난이도 하
// BFS도 가능하지만 PC 수가 적으므로 DFS로 빠르게 푸는 것이 유리
public class T2606 {
    
    private static int count = 0;
    private static int N;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int R = Integer.parseInt(br.readLine());
        boolean[][] network = new boolean[N+1][N+1];
        boolean[] isVisited = new boolean[N+1];

        StringTokenizer st = null;
        for (int i=1; i<=R; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            
            network[v1][v2] = true;
            network[v2][v1] = true;
        }
        
        dfs(network, isVisited, 1);
        System.out.println(count);
    }

    private static void dfs(boolean[][] network, boolean[] isVisited, int index) {
        isVisited[index] = true;

        for (int i=1; i<=N; i++) {
            if (network[index][i] && !isVisited[i]) {
                count++;
                dfs(network, isVisited, i);
                isVisited[i] = true;
            }
        }
    }
}
