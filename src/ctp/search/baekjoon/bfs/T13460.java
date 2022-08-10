package ctp.search.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 구슬탈출2
// https://www.acmicpc.net/problem/13460
public class T13460 {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int N, M;
    private static char[][] map;
    private static boolean[][][][] visited;
    private static int holeX, holeY;
    private static Marble blue, red;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로

        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        for (int i=0; i<N; i++) {
            String input = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'O') {
                    holeX = i;
                    holeY = j;
                } else if (map[i][j] == 'B') {
                    blue = new Marble(0,0, i, j, 0);
                } else if (map[i][j] == 'R') {
                    red = new Marble(i, j, 0, 0, 0);
                }
            }
        }

        int answer = solution();
        System.out.println(answer);
    }

    private static int solution() {
        Queue<Marble> queue = new LinkedList<>();
        queue.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.bx][red.by][blue.bx][blue.by] = true;

        while (!queue.isEmpty()) {
            Marble marble = queue.poll();

            int currRx = marble.rx;
            int currRy = marble.ry;
            int currBx = marble.bx;
            int currBy = marble.by;
            int currCnt = marble.cnt;

            if (currCnt > 10) { // 이동 수가 10 초과 시 실패
                return -1;
            }

            for (int i=0; i<dx.length; i++) {
                int newRx = currRx;
                int newRy = currRy;
                int newBx = currBx;
                int newBy = currBy;

                boolean isRedHole = false;  // 빨간 구슬이 빠진지 확인
                boolean isBlueHole = false; // 파란 구슬이 빠진지 확인

                // 빨간 구슬 이동 -> # 벽을 만날 때까지 이동
                while (map[newRx+dx[i]][newRy+dy[i]] != '#') {
                    newRx += dx[i];
                    newRy += dy[i];

                    // 이동 중 구멍을 만나는 경우
                    if (newRx == holeX && newRy == holeY) {
                        isRedHole = true;
                        break;
                    }
                }

                // 파란 구슬 이동 -> # 벽을 만날 때까지 이동
                while (map[newBx+dx[i]][newBy+dy[i]] != '#') {
                    newBx += dx[i];
                    newBy += dy[i];

                    if (newBx == holeX && newBy == holeY) {
                        isBlueHole = true;
                        break;
                    }
                }

                // 파란 구슬이 구멍에 빠지면 무조건 실패
                // 큐에 남은 다른 좌표도 봐야하니 Continue
                if (isBlueHole)
                    continue;

                // 빨간 구슬이 구멍에 빠지면 바로 성공
                if (isRedHole && !isBlueHole)
                    return currCnt;

                // 둘 다 구멍에 빠지지 않았는데 이동할 위치가 같은 경우 -> 위치 조정
                if (newRx == newBx && newRy == newBy) {
                    if (i == 0) {       // 위쪽으로 기울이기 -> 더 큰 x값을 가지는 구슬이 뒤로 감
                        if (currRx > currBx)
                            newRx -= dx[i];
                        else
                            newBx -= dx[i];
                    } else if (i == 1) { // 오른쪽으로 기울이기 -> 더 작은 y값을 가지는 구슬이 뒤로 감
                        if (currRy < currBy)
                            newRy -= dy[i];
                        else
                            newBy -= dy[i];
                    } else if (i == 2) { // 아래쪽 기울이기 -> 더 작은 x값을 가지는 구슬이 뒤로 감
                        if (currRx < currBx)
                            newRx -= dx[i];
                        else
                            newBx -= dx[i];
                    } else { // 왼쪽으로 기울이기 -> 더 큰 y값을 가지는 구슬이 뒤로 감
                        if (currRy > currBy)
                            newRy -= dy[i];
                        else
                            newBy -= dy[i];
                    }
                }

                // 두 구슬이 이동할 위치가 처음 방문하는 곳인 경우에만 이동 -> 큐에 추가
                if (!visited[newRx][newRy][newBx][newBy]) {
                    visited[newRx][newRy][newBx][newBy] = true;
                    queue.add(new Marble(newRx, newRy, newBx, newBy, currCnt+1));
                }
            }
        }

        return -1;
    }
}

class Marble {
    int rx,ry;  // 빨간색 구슬 x,y
    int bx,by;  // 파란색 구슬 x,y
    int cnt;    // 이동 수
    public Marble(int rx, int ry, int bx, int by, int cnt){
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.cnt = cnt;
    }
}
// https://minhamina.tistory.com/191

/**
 * Input
 * 5 5
 * #####
 * #..B#
 * #.#.#
 * #RO.#
 * #####
 * Output
 * 1
 *
 * Input
 * 7 7
 * #######
 * #...RB#
 * #.#####
 * #.....#
 * #####.#
 * #O....#
 * #######
 * Output
 * 5
 *
 * Input
 * 3 7
 * #######
 * #R.O.B#
 * #######
 * Output
 * 1
 *
 * Input
 * 10 10
 * ##########
 * #R#...##B#
 * #...#.##.#
 * #####.##.#
 * #......#.#
 * #.######.#
 * #.#....#.#
 * #.#.##...#
 * #O..#....#
 * ##########
 * Output
 * 7
 *
 * Input
 * 3 10
 * ##########
 * #.O....RB#
 * ##########
 * Output
 * -1
 */