package ctp.search.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1012
// DFS, BFS / 난이도 하
public class T1012 {
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    private static int M;
    private static int N;
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        for (int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());   // 배추밭 가로
            N = Integer.parseInt(st.nextToken());   // 배추밭 세로
            int K = Integer.parseInt(st.nextToken());   // 배추 위치 수

            int[][] field = new int[M][N];
            boolean[][] isVisited = new boolean[M][N];
            for (int j=0; j<K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                field[x][y] = 1;
            }
            answer = 0;

            for (int r=0; r<M; r++) {
                for (int c=0; c<N; c++) {
                    if (field[r][c] == 1 && !isVisited[r][c]) {
                        bfs(r, c, field, isVisited);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }

    private static void bfs(int r, int c, int[][] field, boolean[][] isVisited) {
        Queue<Cabbage> queue = new LinkedList<>();
        queue.add(new Cabbage(r, c));
        isVisited[r][c] = true;

        while (!queue.isEmpty()) {
            Cabbage now = queue.poll();

            for (int i=0; i<dx.length; i++) {
                int newX = now.x + dx[i];
                int newY = now.y + dy[i];
                if (isRange(newX, newY) && field[newX][newY] == 1 && !isVisited[newX][newY]) {
                    queue.add(new Cabbage(newX, newY));
                    isVisited[newX][newY] = true;
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        if (x >= 0 && x < M && y >= 0 && y < N) {
            return true;
        }
        return false;
    }

    static class Cabbage {
        int x;
        int y;

        public Cabbage(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
