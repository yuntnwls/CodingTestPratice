package ctp.graph.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1774
// 크루스칼 알고리즘 / 난이도 중
public class T1774 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 우주신들의 개수
        int M = Integer.parseInt(st.nextToken());   // 이미 연결된 신들과의 통로의 개수

        God[] gods = new God[N+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods[i] = new God(x, y);
        }

        // 각 좌표의 연결 정보를 저장
        int[] parent = new int[N+1];
        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }

        // 이미 연결된 정보 저장
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            union(parent, v1, v2);
        }
        
        solution(gods, parent);
    }

    private static void solution(God[] gods, int[] parent) {
        // 각 좌표간의 거리 구하기
        ArrayList<Node> nodeList = new ArrayList<>();
        for (int i=1; i<gods.length; i++){
            for (int j=i+1; j<gods.length; j++) {
                nodeList.add(new Node(i, j, getDistance(gods[i], gods[j])));
            }
        }

        // 거리 순으로 오름차순 정렬
        Collections.sort(nodeList);

        // 연결된 간선 정보 저장
        double answer = 0;
        for (Node node : nodeList) {
            if (!findParent(parent, node.v1, node.v2)) {
                answer += node.dist;
                union(parent, node.v1, node.v2);
            }
        }

        System.out.println(String.format("%.2f", answer));
    }

    private static double getDistance(God godA, God godB) {
        int width = Math.abs(godA.x- godB.x);
        int height = Math.abs(godA.y- godB.y);
        return Math.sqrt((double)Math.pow(width, 2) + (double)Math.pow(height, 2));
    }

    private static int getParent(int[] parent, int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = getParent(parent, parent[x]);
    }

    private static void union(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    private static boolean findParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        return a == b;
    }

    static class Node implements Comparable<Node>{
        int v1;
        int v2;
        double dist;
        public Node(int v1, int v2, double dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    static class God {
        int x;
        int y;
        public God(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
