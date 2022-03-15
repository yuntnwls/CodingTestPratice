package ctp.graph.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10282
// 다익스트라 최단 경로 / 난이도 중
public class T10282 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        for (int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken());   // 의존성 개수
            int c = Integer.parseInt(st.nextToken());   // 해킹당한 컴퓨터 번호

            ArrayList<Node>[] graph = new ArrayList[n+1];
            for (int j=0; j<=n; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j=0; j<d; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a,s));
            }
            
            solution(graph, c);
        }
    }

    private static void solution(ArrayList<Node>[] graph, int start) {
        int[] dist = new int[graph.length];
        for (int i=1; i<dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.sec, o2.sec)));
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (dist[curr.idx] < curr.sec) {
                continue;
            }

            for (int i=0; i<graph[curr.idx].size(); i++) {
                Node next = graph[curr.idx].get(i);
                int nextSec = curr.sec + next.sec;
                if (dist[next.idx] > nextSec) {
                    dist[next.idx] = nextSec;
                    queue.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        int count = 0;
        int max = 0;
        for (int i=1; i<dist.length; i++) {
            if (dist[i] < Integer.MAX_VALUE) {
                count++;
                max = Math.max(max, dist[i]);
            }
        }

        System.out.println(count + " " + max);
    }

    static class Node {
        int idx;    // 의존 대상 PC
        int sec;    // 감염 시간
        public Node(int idx, int sec) {
            this.idx = idx;
            this.sec = sec;
        }
    }
}
