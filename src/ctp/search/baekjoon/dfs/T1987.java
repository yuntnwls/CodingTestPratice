package ctp.search.baekjoon.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1987
// 백트래킹 / 난이도 중
public class T1987 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    private static int[][] board = null;
    private static boolean[] visited = new boolean[26];
    private static int answer = 0;
    private static int R,C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R+1][C+1];

        for (int i=1; i<=R; i++) {
            String line = br.readLine();
            for (int j=1; j<=C; j++) {
                board[i][j] = line.charAt(j-1) - 'A';
            }
        }

        dfs(1, 1, 0);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int count) {
        if (visited[board[x][y]]) {
            // 중복된 곳을 방문했으므로 종료
            answer = Math.max(answer, count);
            return;
        } else {
            visited[board[x][y]] = true;
            for (int i=0; i<dx.length; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX > 0 && newX <= R && newY > 0 && newY <= C) {
                    dfs(newX, newY, count+1);
                }
            }
            // 방문하지 않은 상태로 초기화
            visited[board[x][y]] = false;
        }
    }
}
