package ctp.search.baekjoon.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/4991
// BFS, 비트마스킹
public class T4991 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int h, w;
    static char[][] map;
    static boolean[][] visit;
    static int[][] distance;
    static int dirty;
    static List<Node> list;

    static boolean[] check;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0)
                break;

            map = new char[h][w];
            list = new ArrayList<>();

            for (int i=0; i<h; i++) {
                String input = br.readLine();
                for (int j=0; j<w; j++) {
                    map[i][j] = input.charAt(j);

                    if (map[i][j] == 'o') {
                        list.add(0, new Node(i, j, 0));
                    } else if (map[i][j] == '*') {
                        list.add(new Node(i, j, 0));
                    }
                }
            }

            distance = new int[list.size()][list.size()];
            dirty = 0; // 탐색 불가능한 더러운 칸이 있는지 알아보는 용도
            for (int i=0; i<list.size(); i++) {
                bfs(list.get(i), i);
                // 로봇과 각 더러운 칸들 간의 거리 구하기
            }

            if (dirty == list.size()-1) {
                // 탐색 불가능한 더러운 칸이 있다면 리스트 크기에서
                // 로봇을 제외한 크기와 다른 것이다.
                result = Integer.MAX_VALUE;
                check = new boolean[list.size()];
                check[0] = true;
                find(0,1,0);
                sb.append(result + "\n");
            } else {
                sb.append(-1 + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static void bfs(Node node, int start) {
        visit = new boolean[h][w];
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        visit[node.x][node.y] = true;

        while (!q.isEmpty()) {
            Node next = q.poll();

            if (map[next.x][next.y] == '*') {
                if (start == 0) dirty++;
                // 로봇에서 시작해서 더러운 칸을 만났을 때만 먼지 갯수 확인

                for (int i=1; i<list.size(); i++) {
                    if (next.x == list.get(i).x && next.y == list.get(i).y) {
                        distance[start][i] = next.move;
                    }
                }
            }

            for (int i=0; i<dx.length; i++) {
                int newX = next.x + dx[i];
                int newY = next.y + dy[i];

                if (newX < 0 || newY < 0 || newX >= h || newY >= w)
                    continue;
                if (map[newX][newY] == 'x' || visit[newX][newY])
                    continue;

                q.offer(new Node(newX, newY, next.move+1));
                visit[newX][newY] = true;
            }
        }
    }

    private static void find(int start, int cnt, int dist) {
        // 리스트의 모든 칸을 방문하였을 때 이동한 칸수가
        // 저장된 값보다 작으면 바꾸어줌
        if (cnt == list.size()) {
            result = Math.min(result, dist);
        }

        for (int i=1; i<list.size(); i++) {
            if (!check[i]) {
                check[i] = true;
                find(i, cnt+1, dist+distance[start][i]);
                check[i] = false;
            }
        }
    }

    static class Node{
        int x, y;
        int move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
// https://toload.tistory.com/entry/JAVA-%EB%B0%B1%EC%A4%80-4991-%EB%A1%9C%EB%B4%87-%EC%B2%AD%EC%86%8C%EA%B8%B0
// https://void2017.tistory.com/401?category=819361 (비트마스킹 사용)