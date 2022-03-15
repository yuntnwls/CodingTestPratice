package ctp.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘
// 최단 경로 알고리즘
// 특정한 하나의 정점에서 다른 정점으로 가는 최단 경로를 구할 수 있음
public class Dijkstra {

    private static int V;   // 노드 수
    private static int E;   // 간선 수
    private static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1];
        for (int i=0; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, c));
        }

        dijkstra(start);
    }

    private static void dijkstra(int start) {
        int[] dist = new int[V+1];  // 최단 거리
        for (int i=1; i<=V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // 비용이 더 작은 것이 우선순위가 높도록 하기위해 compare 구현
        PriorityQueue<Node> queue = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 현재 비용보다 최소 비용이 더 작다면 바로 Continue
            // 이미 최소거리이므로 더 비교할 필요 없음
            if (dist[current.dest] < current.cost) {
                continue;
            }

            for (int i=0; i<graph[current.dest].size(); i++) {
                Node next = graph[current.dest].get(i);
                int nextDist = current.cost + next.cost;
                if (dist[next.dest] > nextDist) {
                    dist[next.dest] = nextDist;
                    queue.offer(new Node(next.dest, dist[next.dest]));
                }
            }
        }

        for (int i=1; i<=V; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    static class Node {
        int dest;   // 도착지
        int cost;   // 도착지로 가는 비용
        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
// https://blog.naver.com/ndb796/221234424646
// https://sskl660.tistory.com/59
/*
input
6 20
1
1 2 2
1 3 5
1 4 1
2 1 2
2 3 3
2 4 2
3 1 5
3 2 3
3 4 3
3 5 1
3 6 5
4 1 1
4 2 2
4 3 3
4 5 1
5 3 1
5 4 1
5 6 2
6 3 5
6 5 2

output
0 2 3 1 2 4
 */