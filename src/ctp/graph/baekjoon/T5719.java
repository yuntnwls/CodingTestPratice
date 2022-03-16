package ctp.graph.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5719
// 다익스트라 최단 경로 / 난이도 중
public class T5719 {

    private static ArrayList<Node>[] graph;
    private static ArrayList<Integer>[] trace;  // 최단 경로를 저장할 배열
    private static int[] dist;
    private static boolean[][] check;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 장소 수
            int M = Integer.parseInt(st.nextToken());   // 도로 수

            if (N==0 && M==0)
                break;

            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];
            trace = new ArrayList[N];
            for (int i=0; i<N; i++) {
                graph[i] = new ArrayList<>();
                trace[i] = new ArrayList<>();
            }

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                graph[U].add(new Node(V, P));
            }

            dist = new int[N];
            check = new boolean[N][N];

            // 1. 최단 경로 찾기
            dijkstra(S);
            // 2. 최단 경로 제거
            backTracking(D);
            // 3. check된 최단 경로를 제외한 다음 최단 경로 찾기
            dijkstra(S);

            int answer = (dist[D] == Integer.MAX_VALUE) ? -1: dist[D];
            System.out.println(answer);
        }
    }

    // 도착점부터 거꾸로 최단 경로 표시
    private static void backTracking(int d) {
        for (int pre : trace[d]) {
            if (check[pre][d])
                continue;
            check[pre][d] = true;
            backTracking(pre);
        }
    }

    // 최단 경로 찾기
    private static void dijkstra(int s) {
        for (int i=0; i<graph.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
        queue.offer(new Node(s, 0));
        dist[s] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (dist[curr.idx] < curr.cost)
                continue;

            for (Node next : graph[curr.idx]) {
                // 제거된 간선
                if (check[curr.idx][next.idx])
                    continue;

                int nextCost = curr.cost + next.cost;

                // 같은 최단 경로라면 trace에 저장
                if (dist[next.idx] == nextCost) {
                    trace[next.idx].add(curr.idx);
                }
                // 새로운 최단 경로라면 기존 trace를 삭제 후 다시 저장
                if (dist[next.idx] > nextCost) {
                    dist[next.idx] = nextCost;
                    trace[next.idx].clear();
                    trace[next.idx].add(curr.idx);
                    queue.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }

    static class Node {
        int idx;
        int cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
// https://sorjfkrh5078.tistory.com/11
