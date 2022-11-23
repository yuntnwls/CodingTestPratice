package ctp.search.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3197
// BFS
public class T3197 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static int R,C;
    private static char[][] map;

    private static boolean[][] vitSwan;
    // 다음 턴에 백조가 갈 수 있는 좌표
    private static Queue<Pos> swanQ, swanNextQ;
    private static boolean[][] vitWater;
    // 다음 턴에 물이 될 좌표
    private static Queue<Pos> waterQ, waterNextQ;

    private static Pos startPos, endPos;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        vitSwan = new boolean[R][C];
        swanQ = new LinkedList<>();
        swanNextQ = new LinkedList<>();

        vitWater = new boolean[R][C];
        waterQ = new LinkedList<>();
        waterNextQ = new LinkedList<>();

        int resultDays = -1;

        List<Pos> startEnd = new ArrayList<>();

        for (int i=0; i<R; i++) {
            String input = br.readLine();
            for (int j=0; j<C; j++) {
                if (input.charAt(j) == 'X') {
                    map[i][j] = 'X';
                } else {
                    // 백조의 좌표는 물이다. 물에 대한 BFS를 돌릴 때 해당 좌표를 큐에 넣기
                    if (input.charAt(j) == 'L') {
                        startEnd.add(new Pos(j, i));
                    }
                    waterQ.add(new Pos(j, i));
                    vitWater[i][j] = true;
                    map[i][j] = '.';
                }
            }
        }

        startPos = startEnd.get(0);
        endPos = startEnd.get(1);

        vitSwan[startPos.y][startPos.x] = true;
        swanQ.add(new Pos(startPos.x, startPos.y));

        while (!vitSwan[endPos.y][endPos.x]) {
            waterBFS(); // 빙하 녹이기
            swanBFS();  // 백조 이동
            resultDays++;
        }

        System.out.println(resultDays);
    }

    private static void waterBFS() {
        // 이전 턴에서 넣어둔 데이터
        while (!waterNextQ.isEmpty()) {
            Pos cur = waterNextQ.poll();
            // 물로 변경
            map[cur.y][cur.x] = '.';
            waterQ.add(cur);
        }

        while (!waterQ.isEmpty()) {
            Pos cur = waterQ.poll();

            for (int k=0; k<dx.length; k++) {
                int newX = cur.x + dx[k];
                int newY = cur.y + dy[k];

                if (newX < 0 || newX >= C || newY < 0 || newY >= R)
                    continue;
                if (vitWater[newY][newX])
                    continue;

                // 빙하 상태라면 다음 턴의 큐에 넣기
                if (map[newY][newX] == 'X') {
                    waterNextQ.add(new Pos(newX, newY));
                    vitWater[newY][newX] = true;
                }
            }
        }
    }

    private static void swanBFS() {
        while (!swanNextQ.isEmpty()) {
            swanQ.add(swanNextQ.poll());
        }

        while (!swanQ.isEmpty()) {
            Pos cur = swanQ.poll();

            for (int k=0; k<dx.length; k++) {
                int newX = cur.x + dx[k];
                int newY = cur.y + dy[k];

                if (newX < 0 || newX >= C || newY < 0 || newY >= R)
                    continue;
                if (vitSwan[newY][newX])
                    continue;

                if (map[newY][newX] == 'X') {
                    // 빙하를 만나는 경우 다음 턴에 이동
                    swanNextQ.add(new Pos(newX, newY));
                }
                else {
                    // 물을 만나는 경우 이번턴에 이동
                    swanQ.add(new Pos(newX, newY));
                }

                vitSwan[newY][newX] = true;
            }
        }
    }

    public static class Pos {
        int x;
        int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
// https://zangzangs.tistory.com/161