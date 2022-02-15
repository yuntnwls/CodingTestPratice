package ctp.search.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1939
// BFS + 이진 탐색 / 난이도 중상
public class T1939 {

    private static int N, M;
    private static ArrayList<ArrayList<Node>> infoList = new ArrayList<>();
    private static boolean[] isVisited;
    private static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<=N; i++) {
            infoList.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            infoList.get(s).add(new Node(d, w));
            infoList.get(d).add(new Node(s, w));

            max = Math.max(max, w);
        }

        st = new StringTokenizer(br.readLine());
        int locA = Integer.parseInt(st.nextToken());
        int locB = Integer.parseInt(st.nextToken());

        int answer = solution(locA, locB);
        System.out.println(answer);
    }

    private static int solution(int start, int end) {
        int left = 0;       // 중량제한의 최소값
        int right = max;    // 중량제한의 최대값

        while (left <= right) {
            int mid = (left+right)/2;
            isVisited = new boolean[N+1];

            if (bfs(start, end, mid)) {
                // 운반 가능하다면 중량 늘림
                left = mid+1;
            } else {
                // 운반 불가능하다면 중량 줄임
                right = mid-1;
            }
        }
        return right;
    }

    private static boolean bfs(int start, int end, int mid) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        isVisited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == end)
                return true;

            for (Node n : infoList.get(node)) {
                // 방문하지 않았고 중량이 넘지 않으면 방문 가능
                if (!isVisited[n.dest] && mid <= n.weight) {
                    isVisited[n.dest] = true;
                    queue.add(n.dest);
                }
            }
        }
        return false;
    }

    static class Node {
        int dest;
        int weight;
        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
