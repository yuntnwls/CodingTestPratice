package ctp.search.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9376
// BFS
public class T9376 {

    static int[] dx = {0, 0 ,1, -1};
    static int[] dy = {1, -1 ,0, 0};

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char DOOR = '#';
    static final char PRISONER = '$';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int h, w, prisonerIdx, minOpenDoor;
        char[][] map;
        int[][] prisonerOne, prisonerTwo, sanggeun;

        StringBuilder sb = new StringBuilder();
        for (int tc=0; tc<t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // map의 외부를 이동해야하므로 상하좌우 한줄씩 추가
            map = new char[h+2][w+2];
            Prisoner[] prisoners = new Prisoner[2];
            prisonerIdx = 0;

            String input = null;
            for (int i=0; i<h; i++) {
                input = br.readLine();
                for (int j=0; j<w; j++) {
                    map[i+1][j+1] = input.charAt(j);
                    if (input.charAt(j) == PRISONER)
                        prisoners[prisonerIdx++] = new Prisoner(i+1, j+1);
                }
            }

            // 각각 bfs를 구하기
            prisonerOne = bfs(map, prisoners[0], h, w);
            prisonerTwo = bfs(map, prisoners[1], h, w);
            sanggeun = bfs(map, new Prisoner(0,0), h, w);

            minOpenDoor = getMinSum(prisonerOne, prisonerTwo, sanggeun, map);
            sb.append(minOpenDoor);

            if (tc < t-1)
                sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int getMinSum(int[][] prisonerOne, int[][] prisonerTwo, int[][] sanggeun, char[][] map) {
        int result = Integer.MAX_VALUE;

        for (int i=0; i<prisonerOne.length; i++) {
            for (int j=0; j<prisonerOne[i].length; j++) {
                if (map[i][j] == WALL)
                    continue;

                int sum = prisonerOne[i][j] + prisonerTwo[i][j] + sanggeun[i][j];
                // 문 한 개를 3번 열었으므로 1로 간주하기 위해 -2
                if (map[i][j] == DOOR)
                    sum -= 2;

                result = Math.min(result, sum);
            }
        }
        return result;
    }

    private static int[][] bfs(char[][] map, Prisoner prisoner, int h, int w) {
        PriorityQueue<Prisoner> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[h+2][w+2];
        int[][] doorHistory = new int[h+2][w+2];

        queue.add(prisoner);
        visited[prisoner.x][prisoner.y] = true;

        while (!queue.isEmpty()) {
            Prisoner cur = queue.poll();
            doorHistory[cur.x][cur.y] = cur.openDoor;

            for (int i=0; i<dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= h+2 || ny < 0 || ny >= w+2)
                    continue;
                if (!visited[nx][ny] && map[nx][ny] != WALL) {
                    visited[nx][ny] = true;

                    Prisoner newPrisoner = new Prisoner(nx, ny);
                    if (map[nx][ny] == DOOR)
                        newPrisoner.openDoor = cur.openDoor+1;
                    else
                        newPrisoner.openDoor = cur.openDoor;

                    queue.add(newPrisoner);
                }
            }
        }

        return doorHistory;
    }

    public static class Prisoner implements Comparable<Prisoner> {
        int x,y,openDoor;

        public Prisoner(int x, int y){
            this.x = x;
            this.y = y;
            openDoor = 0;
        }

        public Prisoner(int x, int y, int openDoor){
            this.x = x;
            this.y = y;
            this.openDoor = openDoor;
        }

        @Override
        public int compareTo(Prisoner o) {
            return Integer.compare(this.openDoor, o.openDoor);
        }
    }
}
// https://guy-who-writes-sourcecode.tistory.com/34